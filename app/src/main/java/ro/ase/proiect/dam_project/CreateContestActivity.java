package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.Question;
import ro.ase.proiect.dam_project.util.QuestionAdapter;

public class CreateContestActivity extends AppCompatActivity {


    TextInputEditText tieName;
    TextInputEditText tieDescription;
    Button btnAddQuestion;
    Button btnSaveContest;
    private ListView lvQuestions;
    List<Question> questions = new ArrayList<>();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contest);

        intent = getIntent();

        initComponents();

    }

    private void initComponents()
    {
        tieName = findViewById(R.id.create_contest_tie_name);
        tieDescription=findViewById(R.id.create_contest_tie_description);
        btnAddQuestion = findViewById(R.id.create_contest_btn_add_question);
        lvQuestions = findViewById(R.id.create_contest_lv_questions);

        QuestionAdapter adapter = new QuestionAdapter(getApplicationContext(),
                R.layout.lv_questions_row, questions, getLayoutInflater());

        lvQuestions.setAdapter(adapter);


        btnAddQuestion.setOnClickListener(goToAddQuestion());

        btnSaveContest = findViewById(R.id.create_contest_btn_save);
        btnSaveContest.setOnClickListener(saveEvent());
    }

    private View.OnClickListener goToAddQuestion()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddQuestionActivity.class);
                startActivityForResult(intent, Constants.ADD_EXPENSE_REQUEST_CODE);
            }
        };

    }

    private View.OnClickListener saveEvent(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(isValid()){
                    String name = tieName.getText().toString();
                    String description = tieDescription.getText().toString();

                    Contest contest = new Contest(name, description);

                    intent.putExtra(Constants.ADD_EXPENSE_KEY, contest);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }



    private boolean isValid(){
        if (tieName.getText() == null || tieName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_contest_name_valid_error, Toast.LENGTH_LONG).show();
            return false;
        }

        else if (tieDescription.getText() == null || tieDescription.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_contest_description_valid_error, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.ADD_EXPENSE_REQUEST_CODE
                && resultCode == RESULT_OK && data != null) {


            Question question = data.getParcelableExtra(Constants.ADD_EXPENSE_KEY);


            if(question!=null){
                Toast.makeText(getApplicationContext(), question.toString(), Toast.LENGTH_LONG).show();
                questions.add(question);
                QuestionAdapter adapter= (QuestionAdapter) lvQuestions.getAdapter();
                adapter.notifyDataSetChanged();
            }

        } else {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.create_contest_activity_error),
                    Toast.LENGTH_LONG).show();
        }



    }
}
