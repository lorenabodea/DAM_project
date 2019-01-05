package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import ro.ase.proiect.dam_project.util.Answer;
import ro.ase.proiect.dam_project.util.Constants;



public class AddAnswerActivity extends AppCompatActivity {

    TextInputEditText tieAnswer;
    Button btnSaveAnswer;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);
        intent = getIntent();
        initComponents();
    }

    private void initComponents(){
        tieAnswer = findViewById(R.id.add_answer_tie_answer);
        btnSaveAnswer=findViewById(R.id.add_answer_btn_add_answer);
        btnSaveAnswer.setOnClickListener(saveEvent());
    }



    private View.OnClickListener saveEvent(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(isValid()){
                    String ans = tieAnswer.getText().toString();

                    Answer answer = new Answer(ans);

                    intent.putExtra(Constants.KEY, answer);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }


    private boolean isValid(){
        if (tieAnswer.getText() == null || tieAnswer.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_question_write_question_valid_error, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    //fara asta, crapa cand dai back din editarea unei intrebari
    //sa scoateti hardcodarea de pe resultCode si sa faceti ceva gen result not ok
    @Override
    public void onBackPressed(){
        intent.putExtra(Constants.KEY, "");
        setResult(5, intent);
        finish();
    }



}
