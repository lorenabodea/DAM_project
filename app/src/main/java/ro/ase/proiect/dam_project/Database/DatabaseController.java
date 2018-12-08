package ro.ase.proiect.dam_project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseController extends SQLiteOpenHelper
        implements DatabaseConstant {


    private static DatabaseController controller;

    private DatabaseController(@Nullable Context context) {

        super(context, DATABASE_NAME,
                null,
                DATABASE_VERSION);
    }

    public static DatabaseController getInstance(Context context) {
        if (controller == null) {
            synchronized (DatabaseController.class) {
                if (controller == null) {
                    controller = new DatabaseController(context);
                }
            }
        }
        return controller;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_CONTEST);
        sqLiteDatabase.execSQL(CREATE_TABLE_QUESTION);
        sqLiteDatabase.execSQL(CREATE_TABLE_ANSWER);
    }


    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1){
        //preluare date existente
        sqLiteDatabase.execSQL(DROP_TABLE_CONTEST);
        sqLiteDatabase.execSQL(DROP_TABLE_QUESTION);
        sqLiteDatabase.execSQL(DROP_TABLE_ANSWER);
        onCreate(sqLiteDatabase);
    }




}
