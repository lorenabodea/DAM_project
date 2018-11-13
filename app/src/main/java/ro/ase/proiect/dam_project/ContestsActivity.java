package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContestsActivity extends AppCompatActivity {

    Button btnCreateContest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);

        initComponents();
    }

    private void initComponents()
    {
        btnCreateContest = findViewById(R.id.activity_contests_btn_create_contest);
        btnCreateContest.setOnClickListener(goToCreateContest());
    }


    private View.OnClickListener goToCreateContest()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateContestActivity.class);
                startActivity(intent);
            }
        };
    }
}
