package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileTeacherActivity extends AppCompatActivity {

    Button btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_teacher);

        initComponents();
    }

    private void initComponents()
    {
        btnEditProfile = findViewById(R.id.profile_teacher_btn_edit_profile);
        btnEditProfile.setOnClickListener(goToEditProfileTeacher());
    }

    private View.OnClickListener goToEditProfileTeacher()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfileTeacherActivity.class);
                startActivity(intent);
            }
        };
    }
}
