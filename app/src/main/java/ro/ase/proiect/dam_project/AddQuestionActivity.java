package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Question;

public class AddQuestionActivity extends AppCompatActivity {
    Spinner spnTimeLimit;
    TextInputEditText tieWriteQuestion;
    Button btnSaveQuestion;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        intent = getIntent();
        initComponents();
    }

    private void initComponents(){
        tieWriteQuestion = findViewById(R.id.add_question_tie_question);
        spnTimeLimit = findViewById(R.id.add_question_spn_time);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.add_question_time_values, R.layout.support_simple_spinner_dropdown_item);
        spnTimeLimit.setAdapter(adapter);
        btnSaveQuestion = findViewById(R.id.add_question_btn_save);
        btnSaveQuestion.setOnClickListener(saveEvent());
    }


    private View.OnClickListener saveEvent(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(isValid()){
                    String questionName = tieWriteQuestion.getText().toString();
                    String time = spnTimeLimit.getSelectedItem().toString();

                    Question question = new Question(questionName, time);


                    intent.putExtra(Constants.ADD_EXPENSE_KEY, question);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }


    private boolean isValid(){
        if (tieWriteQuestion.getText() == null || tieWriteQuestion.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_question_write_question_valid_error, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }




}
