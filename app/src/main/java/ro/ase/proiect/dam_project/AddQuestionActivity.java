package ro.ase.proiect.dam_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.Database.AnswersRepository;
import ro.ase.proiect.dam_project.Database.ContestsRepository;
import ro.ase.proiect.dam_project.util.Answer;
import ro.ase.proiect.dam_project.util.AnswerAdapter;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.ContestAdapter;
import ro.ase.proiect.dam_project.util.Question;
import ro.ase.proiect.dam_project.util.QuestionAdapter;

public class AddQuestionActivity extends AppCompatActivity {
    Spinner spnTimeLimit;
    TextInputEditText tieWriteQuestion;
    Button btnSaveQuestion;
    Button btnAddAnswer;
    private ListView lvAnswers;
    List<Answer> answers = new ArrayList<>();
    Intent intent;

    private AnswersRepository repository;
    private int selectedPoz;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        intent = getIntent();
        initComponents();
    }

    private void initComponents(){
        tieWriteQuestion = findViewById(R.id.add_question_tie_question);
        spnTimeLimit = findViewById(R.id.add_question_spn_time);

        lvAnswers=findViewById(R.id.add_question_lv_answers);
        btnSaveQuestion = findViewById(R.id.add_question_btn_save);
        btnAddAnswer=findViewById(R.id.add_question_btn_add_ans);

        repository = new AnswersRepository(getApplicationContext());

        repository.open();
        answers = repository.findAnswer();
        repository.close();

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.add_question_time_values, R.layout.support_simple_spinner_dropdown_item);
        spnTimeLimit.setAdapter(adapter1);

        AnswerAdapter adapter = new AnswerAdapter(getApplicationContext(),
                R.layout.lv_answers_row, answers, getLayoutInflater());

        lvAnswers.setAdapter(adapter);

        lvAnswers.setOnItemClickListener(onListItemClick());

        lvAnswers.setOnItemLongClickListener(deleteContest());

        btnSaveQuestion.setOnClickListener(saveEvent());
        btnAddAnswer.setOnClickListener(addAnswerEvent());
    }


//    private View.OnClickListener goToAddAnswer(){
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AddAnswerActivity.class);
//                startActivityForResult(intent, Constants.ADD_EXPENSE_REQUEST_CODE);
//            }
//        };
//    }

    private View.OnClickListener saveEvent(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(isValid()){
                    String questionName = tieWriteQuestion.getText().toString();
                    String time = spnTimeLimit.getSelectedItem().toString();

                    Question question = new Question(questionName, time);


                    intent.putExtra(Constants.KEY, question);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }


    private boolean isValid(){
        if (tieWriteQuestion.getText() == null || tieWriteQuestion.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_question_write_question_valid_error, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Answer answer = data.getParcelableExtra(Constants.KEY);

        if(answer != null){
            Toast.makeText(getApplicationContext(), answer.toString(), Toast.LENGTH_LONG).show();

            if (requestCode == Constants.REQUEST_CODE
                    && resultCode == RESULT_OK && data != null) {
                insertAnswer(answer);
            } else if (requestCode == Constants.UPDATE_CONTEST_REQUEST_CODE) {
                updateAnswer(answer);
            }
            else {
                Toast.makeText(getApplicationContext(), getString(R.string.activity_contest_error),
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    private AdapterView.OnItemClickListener onListItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(), AddAnswerActivity.class);
                intent.putExtra(Constants.KEY, answers.get(position));

                selectedPoz = position;

                startActivityForResult(intent, Constants.UPDATE_ANSWER_REQUEST_CODE);
            }
        };
    }

    private View.OnClickListener addAnswerEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddAnswerActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        };
    }


    private void insertAnswer(Answer answer) {
        repository.open();
        Long id = repository.insertAnswer(answer);
        repository.close();
        if (id != -1) {
            answer.setId(id);
            answers.add(answer);
            AnswerAdapter adapter = (AnswerAdapter) lvAnswers.getAdapter();
            adapter.notifyDataSetChanged();
        }

    }

    private void updateAnswer(Answer answer){

        answers.get(selectedPoz).setAnswer(answer.getAnswer());

        repository.open();
        int result = repository.updateAnswer(answers.get(selectedPoz));
        repository.close();

        if(result == 1){
            Toast.makeText(getApplicationContext(), "Actualizare realizata cu succes", Toast.LENGTH_SHORT).show();
            AnswerAdapter adapter = (AnswerAdapter) lvAnswers.getAdapter();
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Eroare la actualizare", Toast.LENGTH_SHORT).show();
        }
    }

    private AdapterView.OnItemLongClickListener deleteContest() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddQuestionActivity.this);

                //refactor constants
                builder.setTitle(Constants.DELETE_ANSWER)
                        .setMessage(R.string.message_alert3)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                repository.open();
                                int result = repository.deleteAnswer(answers.get(position));
                                repository.close();

                                if(result == 1){
                                    Toast.makeText(getApplicationContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Delete unsuccessful", Toast.LENGTH_SHORT).show();
                                }

                                answers.remove(answers.get(position));
                                AnswerAdapter adapter = (AnswerAdapter) lvAnswers.getAdapter();
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "The deletion has been canceled", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create().show();
                return true;
            }
        };
    }




}
