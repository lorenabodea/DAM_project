package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginTeacherActivity extends AppCompatActivity {

    Button SignInBtn;
    Button SignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        init();
    }

    private void init(){

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

                    Intent intent = new Intent(getApplicationContext(), HomeTeacherActivity.class);
                    startActivity(intent);

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


}
