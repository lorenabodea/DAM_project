package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ro.ase.proiect.dam_project.Database.UsersRepository;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.User;
import ro.ase.proiect.dam_project.util.UserAdapter;

public class CreateProfileStudentActivity extends AppCompatActivity {

    Button btnSave;
    TextInputEditText tieFirstName;
    TextInputEditText tieLastName;
    TextInputEditText tieEmail;
    TextInputEditText tiePassword;
    TextInputEditText tieUser;
    RadioGroup rgSex;
    RadioButton rbSex;
    DatePicker dpDate;

    ListView lvUsers;

    private UsersRepository repository;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile_student);

        initComponents();
    }

    private void initComponents()
    {
        btnSave = findViewById(R.id.create_profile_student_btn_save);
        btnSave.setOnClickListener(goToHomeStudent());

        tieFirstName = findViewById(R.id.create_profile_student_tie_first_name);
        tieLastName = findViewById(R.id.create_profile_student_tie_last_name);
        tieEmail = findViewById(R.id.create_profile_student_tie_email);
        tiePassword = findViewById(R.id.create_profile_student_tie_password);
        tieUser = findViewById(R.id.create_profile_student_tie_user);

        rgSex = findViewById(R.id.create_profile_student_rg_sex);

        dpDate = findViewById(R.id.create_profile_student_dp);

        lvUsers = findViewById(R.id.created_users_lv);

        repository = new UsersRepository(getApplicationContext());
    }

    public static Date getDateFromDatePicker(DatePicker datePicker)
    {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }



    private void insertUserinDB(User user) {

        repository.open();
        Long id = repository.insertUser(user);
        repository.close();
        if (id != -1) {
            user.setId(id);
            users.add(user);
            UserAdapter adapter = (UserAdapter) lvUsers.getAdapter();
            adapter.notifyDataSetChanged();
        } else {
            Log.i("USER_INSERT_ERROR", "User id -1");
            Toast.makeText(getApplicationContext(),
                    "NOT INSERTED",
                    Toast.LENGTH_LONG).show();
        }
    }


    private View.OnClickListener goToHomeStudent() //+ insert in BD
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValid())
                {
                    Intent intent = new Intent(getApplicationContext(), HomeStudentActivity.class);
                    startActivityForResult(intent, Constants.INSERT_USER_REQUEST_CODE);
                }

            }
        };
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Date date = getDateFromDatePicker(dpDate);
        int selectedId = rgSex.getCheckedRadioButtonId();
        rbSex = findViewById(selectedId);
        User user = new User(tieUser.getText().toString(), tiePassword.getText().toString(), tieFirstName.getText().toString(),
                tieLastName.getText().toString(), tieEmail.getText().toString(), rbSex.getText().toString(), date);

        if(user != null){
            Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_LONG).show();
            Log.i("USER_NOT_NULL", "User created");

            if (requestCode == Constants.INSERT_USER_REQUEST_CODE
                    && resultCode == RESULT_OK && data != null) {
                users.add(user);
                insertUserinDB(user);
                data.putExtra("USERS_LIST", (Serializable)users);

                Log.i("USER_INSERTED", "User inserted in table");
                Toast.makeText(getApplicationContext(), "User inserted", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "User not created",
                    Toast.LENGTH_LONG).show();
            Log.i("USER_NULL", "User not created");
        }

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
