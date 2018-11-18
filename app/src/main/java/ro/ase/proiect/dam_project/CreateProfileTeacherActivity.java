package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CreateProfileTeacherActivity extends AppCompatActivity {

    Button btnSave;
    TextInputEditText tieFirstName;
    TextInputEditText tieLastName;
    TextInputEditText tieEmail;
    TextInputEditText tiePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_teacher);

        initComponents();
    }

    private void initComponents()
    {
        btnSave = findViewById(R.id.create_profile_teacher_btn_save);
        btnSave.setOnClickListener(goToHomeStudent());

        tieFirstName = findViewById(R.id.create_profile_teacher_tie_first_name);
        tieLastName = findViewById(R.id.create_profile_teacher_tie_last_name);
        tieEmail = findViewById(R.id.create_profile_teacher_tie_email);
        tiePassword = findViewById(R.id.create_profile_teacher_tie_password);
    }

    private View.OnClickListener goToHomeStudent()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                {
                    Intent intent = new Intent(getApplicationContext(), HomeTeacherActivity.class);
                    startActivity(intent);
                }

            }
        };
    }

    private boolean isValid() {

        if (tieFirstName.getText() == null || tieFirstName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_profile_student_first_name_error, Toast.LENGTH_LONG).show();
            return false;
        } else if (tieLastName.getText() == null || tieLastName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_profile_student_last_name_error, Toast.LENGTH_LONG).show();
            return false;
        } else if (tieEmail.getText() == null || tieEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_profile_student_email_error, Toast.LENGTH_LONG).show();
            return false;
        } else if (tiePassword.getText() == null || tiePassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_profile_student_password_error, Toast.LENGTH_LONG).show();
            return false;
        } else if(tiePassword.length() < 8)
        {
            Toast.makeText(getApplicationContext(), R.string.create_profile_student_password_error1, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
