package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.User;

public class LoginStudentActivity extends AppCompatActivity {

    TextInputEditText textUser;
    TextInputEditText textPsswd;
    Button SignInBtn;
    Button SignUpBtn;
    List<User>users = new ArrayList<>();

    private SharedPreferences shpref;
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

        shpref=getSharedPreferences(Constants.LOGSTUD_PREF_FILE_NAME, MODE_PRIVATE);

        SignInBtn.setOnClickListener(signIn());
        SignUpBtn.setOnClickListener(signUp());

        backupSharedPref();
    }

    private View.OnClickListener signIn(){
        //after signing-in, it goes to the Home page
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    Intent intent = new Intent(getApplicationContext(), HomeStudentActivity.class);
                    startActivity(intent);

                    SharedPreferences.Editor editor=shpref.edit();

                    editor.putString(Constants.LOGSTUD_PREF_USERNAME, String.valueOf(textUser));
                    editor.putString(Constants.LOGSTUD_PREF_PASSWORD, String.valueOf(textPsswd));

                    boolean result=editor.commit();
                    if (result){
                        Toast.makeText(getApplicationContext(),"Fisier de preferinta realizat cu succes!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Eroare la crearea fisierelor preferentiale!",Toast.LENGTH_LONG).show();
                    }

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

    private void backupSharedPref(){
        String username=shpref.getString(Constants.LOGSTUD_PREF_USERNAME,null);
        String password=shpref.getString(Constants.LOGSTUD_PREF_PASSWORD,null);

        textUser.setText(username);
        textPsswd.setText(password);
    }
}
