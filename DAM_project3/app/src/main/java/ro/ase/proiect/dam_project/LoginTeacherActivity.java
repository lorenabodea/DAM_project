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

import ro.ase.proiect.dam_project.util.User;


public class LoginTeacherActivity extends AppCompatActivity {

    Button SignInBtn;
    Button SignUpBtn;
    TextInputEditText textUser;
    TextInputEditText textPsswd;

    List<User> users = new ArrayList<>();
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        init();
    }

    private void init(){

        textUser=findViewById(R.id.login_teacher_username_d);
        textPsswd=findViewById(R.id.login_teacher_psswd_d);
        SignInBtn=findViewById(R.id.login_teacher_btn_signIn);
        SignUpBtn=findViewById(R.id.login_teacher_btn_signUp);

        SignInBtn.setOnClickListener(signIn());
        SignUpBtn.setOnClickListener(signUp());
    }

    private View.OnClickListener signIn(){
        //after signing-in, it goes to the Home page
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation())
                {
                    users = (List<User>)intent.getSerializableExtra("USERS_LIST");
                    String login_userName = textUser.getText().toString();

                    for (User user : users) {
                        String created_userName = user.getUser();
                        if (created_userName.equals(login_userName)) {
                            intent.putExtra("LOGIN_USER", user);
                        } else {
                            Toast.makeText(getApplicationContext(), "This user does not exist!", Toast.LENGTH_LONG).show();
                            Log.i("USER_NOT_FOUND", "User does not exist");
                        }
                    }
                    Intent intent = new Intent(getApplicationContext(), HomeTeacherActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    private View.OnClickListener signUp(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CreateProfileTeacherActivity.class);
                startActivity(intent);
            }
        };
    }

    private boolean validation(){
        if( textUser.getText().toString().trim().isEmpty() || textUser.getText()==null) {
            textUser.setError(getText(R.string.login_student_user_error));
            return false;
        }else if(textPsswd.getText()==null || textPsswd.getText().toString().trim().isEmpty()){
            textPsswd.setError(getText(R.string.login_student_passwd_error));
            return false;
        }
        return true;
    }


}
