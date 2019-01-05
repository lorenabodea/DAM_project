package ro.ase.proiect.dam_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import ro.ase.proiect.dam_project.util.Contest;

public class ContestsRepository implements DatabaseConstant {

    private SQLiteDatabase database;
    private DatabaseController controller;

    public ContestsRepository(Context context) {
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

    public long insertContest(Contest contest) {
        if (contest == null) {
            return -1;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTEST_COLUMN_NAME,
                contest.getName());
        contentValues.put(CONTEST_COLUMN_DESCRIPTION,
                contest.getDescription());
        contentValues.put(CONTEST_COLUMN_STUDENTS,
                contest.getStudentsNumber());
        contentValues.put(CONTEST_COLUMN_CODE,
                contest.getCode());


        return database.insert(CONTEST_TABLE_NAME,
                null, contentValues); // returneaza id-ul inregistrarii sau -1 daca apare eroare
    }

    public List<Contest> findContest() {

        List<Contest> results = new ArrayList<>();

        Cursor cursor = database.query(CONTEST_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {

            Long id = cursor.getLong(cursor.getColumnIndex(CONTEST_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(CONTEST_COLUMN_NAME));
            String description = cursor.getString(cursor.getColumnIndex(CONTEST_COLUMN_DESCRIPTION));
            Long students = cursor.getLong(cursor.getColumnIndex(CONTEST_COLUMN_STUDENTS));
            Long code = cursor.getLong(cursor.getColumnIndex(CONTEST_COLUMN_CODE));
//            Boolean priv = cursor.getString(cursor.isClosed(CONTEST_COLUMN_PRIVATE));

            results.add(new Contest(id, name, description, code, students));
        }

        cursor.close();

        return results;
    }


    public int updateContest(Contest contest){
        if(contest == null || contest.getId() == null){
            return -1;
        }

        return database.update(CONTEST_TABLE_NAME, createContentValuesFromContest(contest),
                CONTEST_COLUMN_ID + "=?", new String[]{contest.getId().toString()});
    }

    public int deleteContest(Contest contest){
        if(contest == null || contest.getId() == null){
            return -1;
        }

        return database.delete(CONTEST_TABLE_NAME, CONTEST_COLUMN_ID + "=?", new String[]{contest.getId().toString()});
    }

    private ContentValues createContentValuesFromContest(Contest contest){

        if(contest == null){
            return null;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(CONTEST_COLUMN_NAME, contest.getName());
        contentValues.put(CONTEST_COLUMN_DESCRIPTION, contest.getDescription());
        contentValues.put(CONTEST_COLUMN_STUDENTS, contest.getStudentsNumber());
        contentValues.put(CONTEST_COLUMN_CODE, contest.getCode());

        return contentValues;
    }


}
