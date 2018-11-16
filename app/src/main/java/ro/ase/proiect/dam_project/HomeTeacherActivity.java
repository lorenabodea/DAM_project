package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeTeacherActivity extends AppCompatActivity {

    Button btnContests;
    Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_teacher);

        initComponents();
    }

    private void initComponents()
    {
        btnContests = findViewById(R.id.home_teacher_btn_contests);
        btnContests.setOnClickListener(goToContests());

        btnProfile = findViewById(R.id.home_teacher_btn_profile);
        btnProfile.setOnClickListener(goToTeacherProfile());


    }

    private View.OnClickListener goToContests()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContestsActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener goToTeacherProfile()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileTeacherActivity.class);
                startActivity(intent);
            }
        };
    }


}
