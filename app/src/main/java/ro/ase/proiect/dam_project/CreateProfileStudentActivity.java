package ro.ase.proiect.dam_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class CreateProfileStudentActivity extends AppCompatActivity {

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_student);

        initComponents();
    }

    public void initComponents()
    {
        btnSave = findViewById(R.id.create_profile_student_btn_save);
    }
}
