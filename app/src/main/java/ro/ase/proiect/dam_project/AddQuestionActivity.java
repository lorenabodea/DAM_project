package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddQuestionActivity extends AppCompatActivity {
    //Spinner spnTimeLimit;
    //TextInputEditText tieWriteQuestion;
    //Button btnSaveQuestion;
    //Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
    }

//    private void initComponents(){
//        tieWriteQuestion = findViewById(R.id.add_question_tie_question);
//        spnTimeLimit = findViewById(R.id.add_question_spn_time);
//        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.add_question_time_values, R.layout.support_simple_spinner_dropdown_item);
//    }
}
