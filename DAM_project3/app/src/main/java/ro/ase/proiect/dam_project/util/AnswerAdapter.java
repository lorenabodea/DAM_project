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

public class AnswerAdapter extends ArrayAdapter {

    private Context context;
    private int resource;
    private List<Answer> answers;
    private LayoutInflater inflater;



    public AnswerAdapter(@NonNull Context context, int resource, @NonNull List<Answer> objects, LayoutInflater inflater) {
        super(context, resource, objects);

        this.context = context;
        this.resource=resource;
        this.answers=objects;
        this.inflater=inflater;
    }



    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(resource, parent, false);

        TextView tvAnswer = row.findViewById(R.id.tv_lv_answers_row);

        Answer answer = answers.get(position);

        tvAnswer.setText(answer.getAnswer() != null ? answer.getAnswer()
                : context.getString(R.string.contest_adapter_no_name_msg)
        );
        return row;
    }




}
