package ro.ase.proiect.dam_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ro.ase.proiect.dam_project.util.Answer;
import ro.ase.proiect.dam_project.util.Contest;
import ro.ase.proiect.dam_project.util.Question;

public class QuestionsRepository implements DatabaseConstant {

    private SQLiteDatabase database;
    private DatabaseController controller;

    public QuestionsRepository(Context context) {
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


    public long insertQuestion(Question question) {
        if (question == null) {
            return -1;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(QUESTION_COLUMN_CONTEST_ID,
                question.getContestId());
        contentValues.put(QUESTION_COLUMN_QUESTION,
                question.getQuestionName());
        contentValues.put(QUESTION_COLUMN_TIME,
                question.getTimeLimit());


        return database.insert(QUESTION_TABLE_NAME,
                null, contentValues); // returneaza id-ul inregistrarii sau -1 daca apare eroare
    }


    public List<Question> findQuestion(Long contestID) { //(String contest_id)

        List<Question> results = new ArrayList<>();

        if (contestID != 0) {
            String select = "SELECT * FROM Question WHERE(contest_id = " + contestID.toString() + ");";
//        String selectQuery = "SELECT * FROM " + QUESTION_TABLE_NAME;
            String[] columns = new String[1];
            columns[0] = "columns";

            String[] selectionArgs = new String[1];
            selectionArgs[0] = contestID.toString();


            Cursor cursor = database.query(QUESTION_TABLE_NAME,
                    columns,
                    select,
                    //QUESTION_COLUMN_ID + "=?",
                    selectionArgs,
                    //new String[] { contest_id },
                    null,
                    null,
                    null);

            while (cursor.moveToNext()) {

                Long id = cursor.getLong(cursor.getColumnIndex(QUESTION_COLUMN_ID));
                String questionName = cursor.getString(cursor.getColumnIndex(QUESTION_COLUMN_QUESTION));
                String time = cursor.getString(cursor.getColumnIndex(QUESTION_COLUMN_TIME));

                results.add(new Question(id, contestID, questionName, time));
            }


            cursor.close();
        }
        return results;
    }


    public List<Question> findQuestionsByContest(long receivedContestId) {

        List<Question> results = new ArrayList<>();

        String whereClause = "contest_id = ?";
        String whereArgs[] = new String[]{
                String.valueOf(receivedContestId)
        };

        Cursor cursor = database.query(QUESTION_TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        while (cursor.moveToNext()) {

            Long id = cursor.getLong(cursor.getColumnIndex(QUESTION_COLUMN_ID));
            Long contestId = cursor.getLong(cursor.getColumnIndex(QUESTION_COLUMN_CONTEST_ID));
            String quest = cursor.getString(cursor.getColumnIndex(QUESTION_COLUMN_QUESTION));
            String time = cursor.getString(cursor.getColumnIndex(QUESTION_COLUMN_TIME));

            results.add(new Question(id, contestId, quest, time));
        }

        cursor.close();

        return results;
    }




    public int updateQuestion(Question question){
        if(question == null || question.getId() == null){
            return -1;
        }

        return database.update(QUESTION_TABLE_NAME, createContentValuesFromQuestion(question),
                QUESTION_COLUMN_ID + "=?", new String[]{question.getId().toString()});
    }

    public int deleteQuestion(Question question){
        if(question == null || question.getId() == null){
            return -1;
        }

        return database.delete(QUESTION_TABLE_NAME, QUESTION_COLUMN_ID + "=?", new String[]{question.getId().toString()});
    }

    private ContentValues createContentValuesFromQuestion(Question question){

        if(question == null){
            return null;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(QUESTION_COLUMN_CONTEST_ID, question.getContestId());
        contentValues.put(QUESTION_COLUMN_QUESTION, question.getQuestionName());
        contentValues.put(QUESTION_COLUMN_TIME, question.getTimeLimit());

        return contentValues;
    }



}
