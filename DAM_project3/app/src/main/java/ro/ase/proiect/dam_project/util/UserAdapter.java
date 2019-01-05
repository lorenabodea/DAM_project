package ro.ase.proiect.dam_project.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ro.ase.proiect.dam_project.R;

public class UserAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<User> users;
    private LayoutInflater inflater;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects, LayoutInflater inflater) {
        super(context, resource, objects);

        this.context = context;
        this.resource=resource;
        this.users=objects;
        this.inflater=inflater;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(resource, parent, false);

        TextView tvUser = row.findViewById(R.id.tv_lv_users_row_user);
        TextView tvPassword = row.findViewById(R.id.tv_lv_users_row_password);
        TextView tvFirstName = row.findViewById(R.id.tv_lv_users_row_first_name);
        TextView tvLastName = row.findViewById(R.id.tv_lv_users_row_last_name);
        TextView tvEmail = row.findViewById(R.id.tv_lv_users_row_email);
        TextView tvBirthdate = row.findViewById(R.id.tv_lv_users_row_birthdate);
        TextView tvSex = row.findViewById(R.id.tv_lv_users_row_sex);

        User user = users.get(position);

        tvUser.setText(user.getUser() != null ? user.getUser() : "No user");
        tvPassword.setText(user.getPassword() != null ? user.getPassword() : "No password");
        tvFirstName.setText(user.getFirstName() != null ? user.getFirstName() : "No first name");
        tvLastName.setText(user.getLastName() != null ? user.getLastName() : "No last name");
        tvEmail.setText(user.getEmail() != null ? user.getEmail() : "No email");
        tvBirthdate.setText(user.getBirthdate() != null ? user.getBirthdate().toString() : "No birthdate");
        tvSex.setText(user.getSex() != null ? user.getSex() : "No sex");

        return row;
    }

}
