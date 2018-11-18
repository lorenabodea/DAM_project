package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.ContestAdapter;

public class ContestsActivity extends AppCompatActivity {

    Button btnCreateContest;
    private ListView lvContests;

    List<Contest> contests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);

        initComponents();
    }




    private void initComponents()
    {
        btnCreateContest = findViewById(R.id.activity_contests_btn_create_contest);
        lvContests = findViewById(R.id.activity_contests_lv_contests);

        ContestAdapter adapter = new ContestAdapter(getApplicationContext(), R.layout.lv_contests_row, contests, getLayoutInflater());
        lvContests.setAdapter(adapter);

        btnCreateContest.setOnClickListener(goToCreateContest());
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
