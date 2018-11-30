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
import java.util.Random;

import ro.ase.proiect.dam_project.R;

public class ContestAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private List<Contest> contests;
    private LayoutInflater inflater;


    public ContestAdapter(@NonNull Context context, int resource,  @NonNull List<Contest> objects, LayoutInflater inflater) {
        super(context, resource, objects);

        this.context = context;
        this.resource=resource;
        this.contests=objects;
        this.inflater=inflater;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(resource, parent, false);

        TextView tvName = row.findViewById(R.id.tv_lv_contests_row_name);
        TextView tvDescription = row.findViewById(R.id.tv_lv_contests_row_description);
        TextView tvCode = row.findViewById(R.id.tv_lv_contests_row_code);

        Contest contest = contests.get(position);

        tvName.setText(contest.getName() != null ? contest.getName()
                : context.getString(R.string.contest_adapter_no_name_msg)
        );
        tvDescription.setText(contest.getDescription() != null ? contest.getDescription()
                : context.getString(R.string.contest_adapter_no_description_msg)
        );

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        tvCode.setText(String.format("%06d", number));


        return row;
    }
}
