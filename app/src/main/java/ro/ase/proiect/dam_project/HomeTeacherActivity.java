package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeTeacherActivity extends AppCompatActivity {

    Button btnProfile;
    Button btnContests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);

        initComponents();
    }

    private void initComponents()
    {
        btnProfile = findViewById(R.id.home_teacher_btn_profile);
        btnContests = findViewById(R.id.home_teacher_btn_contests);
        btnProfile.setOnClickListener(goToProfile());
        btnContests.setOnClickListener(myContests());
    }

    private View.OnClickListener goToProfile(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileTeacherActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener myContests(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContestsActivity.class);
                startActivity(intent);
            }
        };
    }



}
