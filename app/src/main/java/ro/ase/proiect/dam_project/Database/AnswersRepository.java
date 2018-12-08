package ro.ase.proiect.dam_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.util.Answer;
import ro.ase.proiect.dam_project.util.Contest;

public class AnswersRepository implements DatabaseConstant {

    private SQLiteDatabase database;
    private DatabaseController controller;

    public AnswersRepository(Context context) {
        controller = DatabaseController.getInstance(context);
    }

    public void open() {
        try {
            database = controller.getWritableDatabase();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            database.close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }



    public long insertAnswer(Answer answer) {
        if (answer == null) {
            return -1;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(ANSWER_COLUMN_QUESTION_ID,
                answer.getQuestionId());
        contentValues.put(ANSWER_COLUMN_ANS1,
                answer.getAnswer());

        return database.insert(ANSWER_TABLE_NAME,
                null, contentValues);
    }


    public List<Answer> findAnswer() {

        List<Answer> results = new ArrayList<>();

        Cursor cursor = database.query(ANSWER_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {

            Long id = cursor.getLong(cursor.getColumnIndex(ANSWER_COLUMN_ID));
            Long questionId = cursor.getLong(cursor.getColumnIndex(ANSWER_COLUMN_QUESTION_ID));
            String ans = cursor.getString(cursor.getColumnIndex(ANSWER_COLUMN_ANS1));



            results.add(new Answer(id, questionId, ans));
        }

        cursor.close();

        return results;
    }

    public int updateAnswer(Answer answer){
        if(answer == null || answer.getId() == null){
            return -1;
        }

        return database.update(ANSWER_TABLE_NAME, createContentValuesFromAnswer(answer),
                ANSWER_COLUMN_ID + "=?", new String[]{answer.getId().toString()});
    }

    public int deleteAnswer(Answer answer){
        if(answer == null || answer.getId() == null){
            return -1;
        }

        return database.delete(ANSWER_TABLE_NAME, ANSWER_COLUMN_ID + "=?", new String[]{answer.getId().toString()});
    }

    private ContentValues createContentValuesFromAnswer(Answer answer){

        if(answer == null){
            return null;
        }

        ContentValues contentValues = new ContentValues();


        contentValues.put(ANSWER_COLUMN_QUESTION_ID, answer.getQuestionId());
        contentValues.put(ANSWER_COLUMN_ANS1, answer.getAnswer());

        return contentValues;
    }



}
