package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStudent;
    Button btnTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents()
    {
        btnStudent = findViewById(R.id.main_btn_student);
        btnStudent.setOnClickListener(goToLoginStudent());

        btnTeacher = findViewById(R.id.main_btn_teacher);
        btnTeacher.setOnClickListener(goToLoginTeacher());
    }

    private View.OnClickListener goToLoginStudent()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginStudentActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener goToLoginTeacher()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginTeacherActivity.class);
                startActivity(intent);
            }
        };
    }
}
