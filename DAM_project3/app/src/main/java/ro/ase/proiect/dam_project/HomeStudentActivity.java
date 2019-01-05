package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeStudentActivity extends AppCompatActivity {

    Button btnProfile;
    Button btnStartContest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);

        initComponents();
    }

    private void initComponents()
    {
        btnProfile = findViewById(R.id.home_teacher_btn_profile);
        btnStartContest = findViewById(R.id.home_student_btn_start_contest);
        //btnProfile.setOnClickListener(goToProfile());
        btnStartContest.setOnClickListener(startContest());
    }

   /* private View.OnClickListener goToProfile(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileStudentActivity.class);
                startActivity(intent);
            }
        };
    }*/

    public void goToProfile(View v)
    {
        Intent intent = new Intent(getApplicationContext(), ProfileStudentActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener startContest(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseCategoryActivity.class);
                startActivity(intent);
            }
        };
    }
}

