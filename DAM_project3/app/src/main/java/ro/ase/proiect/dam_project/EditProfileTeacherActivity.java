package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.Database.UsersRepository;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.User;

public class EditProfileTeacherActivity extends AppCompatActivity {

    Button btnSave;
    TextInputEditText tieFirstName;
    TextInputEditText tieLastName;
    TextInputEditText tieEmail;

    List<User> users = new ArrayList<>();
    UsersRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_teacher);

        initComponents();
    }

    private void initComponents()
    {
        btnSave = findViewById(R.id.edit_profile_teacher_btn_save);
        btnSave.setOnClickListener(goBackToHomeStudent());

        repository = new UsersRepository(getApplicationContext());

        repository.open();
        users = repository.findUser();
        repository.close();
    }

    private View.OnClickListener goBackToHomeStudent()
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeStudentActivity.class);
                startActivityForResult(intent, Constants.UPDATE_USER_REQUEST_CODE);
                Toast.makeText(getApplicationContext(), R.string.edit_profile_student_save_message, Toast.LENGTH_LONG).show();


            }
        };
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {

            User user = (User)data.getSerializableExtra("LOGIN_USER");
            if (user != null) {

                if (requestCode == Constants.UPDATE_USER_REQUEST_CODE) {
                    updateUser(user);
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "User null", Toast.LENGTH_LONG).show();
                Log.i("NULL", "User object is null");
            }
        }

        else {
            Toast.makeText(getApplicationContext(), "Result not good", Toast.LENGTH_LONG).show();
        }

    }

    private void updateUser(User user) {

        repository.open();
        int result = repository.updateUser(user);
        repository.close();

        if (result == 1) {
            Toast.makeText(getApplicationContext(), "User updated", Toast.LENGTH_LONG).show();
            Log.i("UPDATE", "User updated");
        } else {
            Toast.makeText(getApplicationContext(), "User not updated", Toast.LENGTH_LONG).show();
            Log.i("ERROR UPDATE", "User not updated");
        }

    }

}
