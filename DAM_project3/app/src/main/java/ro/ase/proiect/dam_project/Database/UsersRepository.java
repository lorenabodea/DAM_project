package ro.ase.proiect.dam_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.User;

public class UsersRepository implements DatabaseConstant {

    private SQLiteDatabase database;
    private DatabaseController controller;

    public UsersRepository(Context context) {
        controller = DatabaseController.getInstance(context);
    }

    public void open() {
        try {
            database = controller.getWritableDatabase();
            Log.i("OPEN_DB", "DB opened");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            database.close();
            Log.i("CLOSE_DB", "DB closed");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    //User table
    //INSERT
    public long insertUser(User user) {
        if (user == null) {
            return -1;
        }

        ContentValues contentValues = new ContentValues();


        contentValues.put(USER_COLUMN_USER,
                user.getUser());
        contentValues.put(USER_COLUMN_PASSWORD,
                user.getPassword());
        contentValues.put(USER_COLUMN_FIRST_NAME,
                user.getFirstName());
        contentValues.put(USER_COLUMN_LAST_NAME,
                user.getLastName());
        contentValues.put(USER_COLUMN_EMAIL,
                user.getEmail());
        contentValues.put(USER_COLUMN_SEX,
                user.getSex());
        contentValues.put(USER_COLUMN_BIRTHDATE,
                user.getBirthdate() != null ?
                        Constants.simpleDateFormat.format(user.getBirthdate())
                        : null);


        return database.insert(USER_TABLE_NAME, null, contentValues);
    }

    //SELECT
    public List<User> findUser() {

        List<User> userResults = new ArrayList<>();

        Cursor cursor = database.query(USER_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {

            Long id = cursor.getLong(cursor.getColumnIndex(USER_COLUMN_ID));
            String user = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER));
            String password = cursor.getString(cursor.getColumnIndex(USER_COLUMN_PASSWORD));
            String firstName = cursor.getString(cursor.getColumnIndex(USER_COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(USER_COLUMN_LAST_NAME));
            String email = cursor.getString(cursor.getColumnIndex(USER_COLUMN_EMAIL));
            String sex = cursor.getString(cursor.getColumnIndex(USER_COLUMN_SEX));
            Date birthdate = null;
            try {
                birthdate = Constants.simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex(USER_COLUMN_BIRTHDATE)));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            userResults.add(new User(id, user, password, firstName, lastName, email, sex, birthdate));
        }
        cursor.close();
        return userResults;
    }

    //UPDATE
    public int updateUser(User user){
        if(user == null || user.getId() == null){
            return -1;
        }

        return database.update(USER_TABLE_NAME, createContentValuesFromUser(user),
                USER_COLUMN_ID + "=?", new String[]{user.getId().toString()});
    }

    //DELETE
    public int deleteUser(User user){
        if(user == null || user.getId() == null){
            return -1;
        }

        return database.delete(USER_TABLE_NAME, USER_COLUMN_ID + "=?", new String[]{user.getId().toString()});
    }

    private ContentValues createContentValuesFromUser(User user){

        if(user == null){
            return null;
        }

        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_COLUMN_USER, user.getUser());
        contentValues.put(USER_COLUMN_PASSWORD, user.getPassword());
        contentValues.put(USER_COLUMN_FIRST_NAME, user.getFirstName());
        contentValues.put(USER_COLUMN_LAST_NAME, user.getLastName());
        contentValues.put(USER_COLUMN_EMAIL, user.getEmail());
        contentValues.put(USER_COLUMN_SEX, user.getSex());
        contentValues.put(USER_COLUMN_BIRTHDATE, user.getBirthdate() != null ? Constants.simpleDateFormat.format(user.getBirthdate()) : null);


        return contentValues;
    }


}
