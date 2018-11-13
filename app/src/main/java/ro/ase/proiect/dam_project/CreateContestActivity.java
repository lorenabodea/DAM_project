package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateContestActivity extends AppCompatActivity {

    //Button btnSaveContest;
    Button btnAddQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contest);

        initComponents();
    }

    private void initComponents()
    {
        btnAddQuestion = findViewById(R.id.create_contest_btn_add_question);
        btnAddQuestion.setOnClickListener(goToAddQuestion());
    }

    private View.OnClickListener goToAddQuestion()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddQuestionActivity.class);
                startActivity(intent);
            }
        };
    }

}
