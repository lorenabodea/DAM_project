package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ChooseCategoryActivity extends AppCompatActivity {


    Spinner categorySpinner;
    Button categoryBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        initComponents();
    }

    private void initComponents(){
    categorySpinner=findViewById(R.id.category_spn);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.choose_category_spn_values,
                R.layout.support_simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

    categoryBtn=findViewById(R.id.category_button);
    categoryBtn.setOnClickListener(gotoQuestions());
    }

   private View.OnClickListener gotoQuestions(){
       return new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),ChooseQuestionActivity.class);
               startActivity(intent);
           }
       };
   }
}
