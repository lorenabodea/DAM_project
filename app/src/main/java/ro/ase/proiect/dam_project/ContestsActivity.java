package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ro.ase.proiect.dam_project.networking.CategoryJson;
import ro.ase.proiect.dam_project.networking.CategoryParser;
import ro.ase.proiect.dam_project.networking.HttpManager;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.ContestAdapter;

public class ContestsActivity extends AppCompatActivity {

    Button btnCreateContest;
    private ListView lvContests;
    private CategoryJson category;
    private static final String URL="https://api.myjson.com/bins/11raai";
    Button btnTechnologies;
    Button btnFun;
    Button btnSoftSkills;

    List<Contest> contests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);

        final HttpManager httpManager = new HttpManager(){
            @Override
            protected void onPostExecute(String s) {
                try {
                    category = CategoryParser.fromJson(s);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Nu merge", Toast.LENGTH_SHORT).show();
                }
            }
        };

        httpManager.execute(URL);
        initComponents();
    }




    private void initComponents()
    {
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

}
