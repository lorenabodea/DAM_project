package ro.ase.proiect.dam_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.Database.ContestsRepository;
import ro.ase.proiect.dam_project.Database.QuestionsRepository;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.ContestAdapter;
import ro.ase.proiect.dam_project.util.Question;
import ro.ase.proiect.dam_project.util.QuestionAdapter;

public class CreateContestActivity extends AppCompatActivity {


    TextInputEditText tieName;
    TextInputEditText tieDescription;
    Button btnAddQuestion;
    Button btnSaveContest;
    private ListView lvQuestions;
    List<Question> questions = new ArrayList<>();
    Intent intent;

    private QuestionsRepository repository;
    private int selectedPoz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contest);

        intent = getIntent();

        initComponents();

    }

    private void initComponents() {
        tieName = findViewById(R.id.create_contest_tie_name);
        tieDescription = findViewById(R.id.create_contest_tie_description);
        btnAddQuestion = findViewById(R.id.create_contest_btn_add_question);
        lvQuestions = findViewById(R.id.create_contest_lv_questions);

        repository = new QuestionsRepository(getApplicationContext());

        repository.open();
        questions = repository.findQuestion();
        repository.close();

        QuestionAdapter adapter = new QuestionAdapter(getApplicationContext(),
                R.layout.lv_questions_row, questions, getLayoutInflater());

        lvQuestions.setAdapter(adapter);

        lvQuestions.setOnItemClickListener(onListItemClick());
        lvQuestions.setOnItemLongClickListener(deleteQuestion());

        btnAddQuestion.setOnClickListener(addQuestionEvent());

        btnSaveContest = findViewById(R.id.create_contest_btn_save);
        btnSaveContest.setOnClickListener(saveEvent());

    }

    private View.OnClickListener goToAddQuestion() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddQuestionActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        };

    }

    private View.OnClickListener saveEvent() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isValid()) {
                    String name = tieName.getText().toString();
                    String description = tieDescription.getText().toString();

                    Contest contest = new Contest(name, description);

                    intent.putExtra(Constants.KEY, contest);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }


    private boolean isValid() {
        if (tieName.getText() == null || tieName.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_contest_name_valid_error, Toast.LENGTH_LONG).show();
            return false;
        } else if (tieDescription.getText() == null || tieDescription.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.create_contest_description_valid_error, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Question question = data.getParcelableExtra(Constants.KEY);


        if (question != null) {
            Toast.makeText(getApplicationContext(), question.toString(), Toast.LENGTH_LONG).show();
//                questions.add(question);
//                QuestionAdapter adapter= (QuestionAdapter) lvQuestions.getAdapter();
//                adapter.notifyDataSetChanged();
            if (requestCode == Constants.REQUEST_CODE
                    && resultCode == RESULT_OK && data != null) {
                insertQuestion(question);
            } else if (requestCode == Constants.UPDATE_CONTEST_REQUEST_CODE) {
                updateQuestion(question);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.activity_contest_error),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private AdapterView.OnItemClickListener onListItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(), AddQuestionActivity.class);
                intent.putExtra(Constants.KEY, questions.get(position));

                selectedPoz = position;

                startActivityForResult(intent, Constants.UPDATE_QUESTION_REQUEST_CODE);
            }
        };
    }


    private View.OnClickListener addQuestionEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddAnswerActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE);
            }
        };
    }



    private void insertQuestion(Question question) {
        repository.open();
        Long id = repository.insertQuestion(question);
        repository.close();
        if (id != -1) {
            question.setId(id);
            questions.add(question);
            ContestAdapter adapter = (ContestAdapter) lvQuestions.getAdapter();
            adapter.notifyDataSetChanged();
        }

    }

    private void updateQuestion(Question question){

        questions.get(selectedPoz).setQuestionName(question.getQuestionName());
        questions.get(selectedPoz).setTimeLimit(question.getTimeLimit());


        repository.open();
        int result = repository.updateQuestion(questions.get(selectedPoz));
        repository.close();

        if(result == 1){
            Toast.makeText(getApplicationContext(), "Actualizare realizata cu succes", Toast.LENGTH_SHORT).show();
            ContestAdapter adapter = (ContestAdapter) lvQuestions.getAdapter();
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "Eroare la actualizare", Toast.LENGTH_SHORT).show();
        }
    }


    private AdapterView.OnItemLongClickListener deleteQuestion() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CreateContestActivity.this);

                //refactor constants
                builder.setTitle(Constants.DELETE_QUESTION)
                        .setMessage(R.string.message_alert2)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                repository.open();
                                int result = repository.deleteQuestion(questions.get(position));
                                repository.close();

                                if(result == 1){
                                    Toast.makeText(getApplicationContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Delete unsuccessful", Toast.LENGTH_SHORT).show();
                                }

                                questions.remove(questions.get(position));
                                QuestionAdapter adapter = (QuestionAdapter) lvQuestions.getAdapter();
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
