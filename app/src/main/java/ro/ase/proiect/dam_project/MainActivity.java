package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnHomeStudent;
    Button btnCreateProfileStudent;
    Button btnHomeTeacher;
    Button btnCreateProfileTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents()
    {
        btnHomeStudent = findViewById(R.id.main_btn_home_student);
        btnHomeStudent.setOnClickListener(goToStudentHome());

        btnCreateProfileStudent = findViewById(R.id.main_btn_create_profile_student);
        btnCreateProfileStudent.setOnClickListener(goToCreateProfileStudent());

        btnHomeTeacher = findViewById(R.id.main_btn_home_teacher);
        btnHomeTeacher.setOnClickListener(goToTeacherHome());

        btnCreateProfileTeacher = findViewById(R.id.main_btn_create_profile_teacher);
        btnCreateProfileTeacher.setOnClickListener(goToCreateProfileTeacher());

    }

    private View.OnClickListener goToStudentHome()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeStudentActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener goToCreateProfileStudent()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateProfileStudentActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener goToTeacherHome()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeTeacherActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener goToCreateProfileTeacher()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateProfileTeacherActivity.class);
                startActivity(intent);
            }
        };
    }
}
