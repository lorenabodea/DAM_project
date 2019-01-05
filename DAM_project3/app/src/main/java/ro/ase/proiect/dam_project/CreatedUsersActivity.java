package ro.ase.proiect.dam_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.util.User;
import ro.ase.proiect.dam_project.util.UserAdapter;

public class CreatedUsersActivity extends AppCompatActivity {

    private ListView lvUsers;
    List<User> users = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_users);
        initComponents();
    }

    private void initComponents()
    {
        lvUsers = findViewById(R.id.created_users_lv);

        UserAdapter adapter = new UserAdapter(getApplicationContext(), R.layout.lv_users_row, users, getLayoutInflater());
        lvUsers.setAdapter(adapter);
    }
}
