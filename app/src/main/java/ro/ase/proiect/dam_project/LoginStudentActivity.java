package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginStudentActivity extends AppCompatActivity {

    TextInputEditText textUser;
    TextInputEditText textPsswd;
    Button SignInBtn;
    Button SignUpBtn;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
        init();
    }

    private void init(){
        textUser=findViewById(R.id.login_student_username_d);
        textPsswd=findViewById(R.id.login_student_psswd_d);
        SignInBtn=findViewById(R.id.login_student_btn_signIn);
        SignUpBtn=findViewById(R.id.login_student_btn_signUp);

        SignInBtn.setOnClickListener(signIn());
        SignUpBtn.setOnClickListener(signUp());
    }

    private View.OnClickListener signIn(){
        //after signing-in, it goes to the Home page
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    Intent intent = new Intent(getApplicationContext(), HomeStudentActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    private View.OnClickListener signUp(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CreateProfileStudentActivity.class);
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
