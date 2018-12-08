package ro.ase.proiect.dam_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.Database.ContestsRepository;
import ro.ase.proiect.dam_project.networking.CategoryJson;
import ro.ase.proiect.dam_project.networking.CategoryParser;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.ContestAdapter;

public class ContestsActivity extends AppCompatActivity {

    private Button btnCreateContest;
    private ListView lvContests;
    private CategoryJson category;
    private Button btnFun;

    private Handler handler;

    private String result = "";


    List<Contest> contests = new ArrayList<>();

    private ContestsRepository repository;
    private int selectedPoz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);

        initComponents();

    }




    private void initComponents()
    {
        handler = new Handler();

        btnCreateContest = findViewById(R.id.contests_btn_create_contest);
        lvContests = findViewById(R.id.contests_lv);

        btnFun=findViewById(R.id.contests_btn_categ1);


        repository = new ContestsRepository(getApplicationContext());

        repository.open();
        contests = repository.findContest();
        repository.close();

        ContestAdapter adapter = new ContestAdapter(getApplicationContext(), R.layout.lv_contests_row, contests, getLayoutInflater());
        lvContests.setAdapter(adapter);

        btnFun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                NetworkThread networkThread = new NetworkThread();
                networkThread.start();
            }
        });

        lvContests.setOnItemClickListener(onListItemClick());

        lvContests.setOnItemLongClickListener(deleteContest());

        btnCreateContest.setOnClickListener(addContestEvent());

    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Contest contest = data.getParcelableExtra(Constants.KEY);

        if(contest != null){
                Toast.makeText(getApplicationContext(), contest.toString(), Toast.LENGTH_LONG).show();
                //contests.add(contest);
                //ContestAdapter adapter = (ContestAdapter) lvContests.getAdapter();
                //adapter.notifyDataSetChanged();
            if (requestCode == Constants.REQUEST_CODE
                        && resultCode == RESULT_OK && data != null) {
                    insertContest(contest);
            } else if (requestCode == Constants.UPDATE_CONTEST_REQUEST_CODE) {
                    updateContest(contest);
                }
                else {
                    Toast.makeText(getApplicationContext(), getString(R.string.activity_contest_error),
                            Toast.LENGTH_LONG).show();
            }

        }

    }

    private class NetworkThread extends Thread {

        private java.net.URL url;
        private HttpURLConnection connection;
        private InputStream inputStream;
        private InputStreamReader inputStreamReader;
        private BufferedReader bufferedReader;

        @Override
        public void run() {

            try {

                url = new URL("https://api.myjson.com/bins/1380fu");
                connection = (HttpURLConnection)url.openConnection();
                inputStream = connection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                String line;

                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(inputStreamReader!=null){
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(connection!=null){
                    connection.disconnect();
                }
            }



            handler.post(new Runnable(){
                @Override
                public void run(){
                    try {
                        category = CategoryParser.fromJson(result);
                        Toast.makeText(getApplicationContext(), category.getFun().toString(), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    private AdapterView.OnItemClickListener onListItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(), CreateContestActivity.class);
                intent.putExtra(Constants.KEY, contests.get(position));

                selectedPoz = position;

                startActivityForResult(intent, Constants.UPDATE_CONTEST_REQUEST_CODE);
            }
        };
    }

    private View.OnClickListener addContestEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateContestActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        };
    }


    private void insertContest(Contest contest) {
        repository.open();
        Long id = repository.insertContest(contest);
        repository.close();
        if (id != -1) {
            contest.setId(id);
            contests.add(contest);
            ContestAdapter adapter = (ContestAdapter) lvContests.getAdapter();
            adapter.notifyDataSetChanged();
        }

    }

    private void updateContest(Contest contest){
        contests.get(selectedPoz).setName(contest.getName());
        contests.get(selectedPoz).setDescription(contest.getDescription());
        //contests.get(selectedPoz).setCode(contest.getCode());


        repository.open();
        int result = repository.updateContest(contests.get(selectedPoz));
        repository.close();

        if(result == 1){
            Toast.makeText(getApplicationContext(), "Actualizare realizata cu succes", Toast.LENGTH_SHORT).show();
            ContestAdapter adapter = (ContestAdapter) lvContests.getAdapter();
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Eroare la actualizare", Toast.LENGTH_SHORT).show();
        }
    }


    private AdapterView.OnItemLongClickListener deleteContest() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                //nu se foloseste getApplication context, ci contextul activitatii
                AlertDialog.Builder builder = new AlertDialog.Builder(ContestsActivity.this);

                //refactor constants
                builder.setTitle(Constants.DELETE_CONTEST)
                        .setMessage(R.string.message_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                repository.open();
                                int result = repository.deleteContest(contests.get(position));
                                repository.close();

                                if(result == 1){
                                    Toast.makeText(getApplicationContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Delete unsuccessful", Toast.LENGTH_SHORT).show();
                                }

                                contests.remove(contests.get(position));
                                ContestAdapter adapter = (ContestAdapter) lvContests.getAdapter();
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "The deletion has been canceled", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
                return true;
            }
        };
    }




}