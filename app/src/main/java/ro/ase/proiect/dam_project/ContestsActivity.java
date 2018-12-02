package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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

import ro.ase.proiect.dam_project.networking.CategoryJson;
import ro.ase.proiect.dam_project.networking.CategoryParser;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.ContestAdapter;

public class ContestsActivity extends AppCompatActivity {

    private Button btnCreateContest;
    private ListView lvContests;
    private CategoryJson category;
    private Button btnTechnologies;
    private Button btnFun;
    private Button btnSoftSkills;

    private Handler handler;

    private String result = "";


    List<Contest> contests = new ArrayList<>();

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

        btnTechnologies=findViewById(R.id.contests_btn_categ1);
        btnFun=findViewById(R.id.contests_btn_categ3);
        btnSoftSkills=findViewById(R.id.contests_btn_categ2);

        ContestAdapter adapter = new ContestAdapter(getApplicationContext(), R.layout.lv_contests_row, contests, getLayoutInflater());
        lvContests.setAdapter(adapter);

        btnCreateContest.setOnClickListener(goToCreateContest());

        btnTechnologies.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NetworkThread networkThread = new NetworkThread();
                networkThread.start();
                if(category != null && category.getTechnoligies() != null){
                    Toast.makeText(getApplicationContext(), category.getTechnoligies().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NetworkThread networkThread = new NetworkThread();
                networkThread.start();
                if(category != null && category.getFun() != null){
                    Toast.makeText(getApplicationContext(), category.getFun().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSoftSkills.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NetworkThread networkThread = new NetworkThread();
                networkThread.start();
                if(category != null && category.getSoftSkills() != null){
                    Toast.makeText(getApplicationContext(), category.getSoftSkills().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private View.OnClickListener goToCreateContest()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateContestActivity.class);
                startActivityForResult(intent, Constants.ADD_EXPENSE_REQUEST_CODE);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.ADD_EXPENSE_REQUEST_CODE
                && resultCode == RESULT_OK && data != null) {


            Contest contest = data.getParcelableExtra(Constants.ADD_EXPENSE_KEY);

            if(contest != null){
                Toast.makeText(getApplicationContext(), contest.toString(), Toast.LENGTH_LONG).show();
                contests.add(contest);
                ContestAdapter adapter = (ContestAdapter) lvContests.getAdapter();
                adapter.notifyDataSetChanged();
            }

        } else {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.activity_contest_error),
                    Toast.LENGTH_LONG).show();
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

                url = new URL("https://api.myjson.com/bins/d0q3u");
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

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        category = CategoryParser.fromJson(result);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Nu merge", Toast.LENGTH_SHORT).show();
                    }

                }
            });



        }

    }
}
