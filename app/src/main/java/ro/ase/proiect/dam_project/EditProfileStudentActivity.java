package ro.ase.proiect.dam_project;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditProfileStudentActivity extends AppCompatActivity {

    Button btnSave;
    TextInputEditText tieFirstName;
    TextInputEditText tieLastName;
    TextInputEditText tieEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_student);

        initComponents();
    }

    private void initComponents()
    {
        btnSave = findViewById(R.id.edit_profile_student_btn_save);
        btnSave.setOnClickListener(goBackToHomeStudent());
    }

    private View.OnClickListener goBackToHomeStudent()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.edit_profile_student_save_message, Toast.LENGTH_LONG).show();
                finish();

            }
        };
    };

}
