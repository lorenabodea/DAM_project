package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseAnswerActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    RadioGroup rgAnswers;

    Button answerBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_answer);
        initComponents();
    }

    private void initComponents(){
        textView=findViewById(R.id.answer_title);
        textView2=findViewById(R.id.answer_quest_d);
        answerBtn=findViewById(R.id.answer_btn);
        rgAnswers = findViewById(R.id.answer_radio_group);
        answerBtn.setOnClickListener(goBackToAnswer());
    }
    private View.OnClickListener goBackToAnswer(){
       return new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(rgAnswers.getCheckedRadioButtonId() == -1)
               {
                   Toast.makeText(getApplicationContext(), R.string.choose_answer_error, Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(getApplicationContext(), "Answer saved!", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(getApplicationContext(),ChooseQuestionActivity.class);
                   startActivity(intent);
               }


           }
       };
    }
}
