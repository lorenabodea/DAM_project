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


public class QuestionAdapter extends ArrayAdapter<Question> {
    private Context context;
    private int resource;
    private List<Question> questions;
    private LayoutInflater inflater;


    public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.questions=objects;
        this.inflater=inflater;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = inflater.inflate(resource, parent, false);
        TextView tvQuestionName = row.findViewById(R.id.tv_lv_questions_row_question);
        TextView tvTime = row.findViewById(R.id.tv_lv_questions_row_time);

        Question question = questions.get(position);

        tvQuestionName.setText(question.getQuestionName() != null ? question.getQuestionName()
                : context.getString(R.string.question_adapter_no_question_msg));
        tvTime.setText(question.getTimeLimit());

        return row;
    }
}
