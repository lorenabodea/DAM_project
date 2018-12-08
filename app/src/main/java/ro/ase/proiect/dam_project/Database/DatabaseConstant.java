package ro.ase.proiect.dam_project.Database;

public interface DatabaseConstant {

    String DATABASE_NAME = "budget.db";
    int DATABASE_VERSION = 1;

    String CONTEST_TABLE_NAME = "Contest";
    String QUESTION_TABLE_NAME="Question";
    String ANSWER_TABLE_NAME="Answer";

    // Sqlite tipuri de date: TEXT, REAL, INTEGER, BOOL, BLOB


    //CONTEST
    String CONTEST_COLUMN_ID = "_id"; // id ul default din Sqlite
    String CONTEST_COLUMN_NAME = "name";
    String CONTEST_COLUMN_DESCRIPTION = "description";
    String CONTEST_COLUMN_CODE = "code";
    String CONTEST_COLUMN_PRIVATE = "private";


    //QUESTION
    String QUESTION_COLUMN_ID="_id";
    String QUESTION_COLUMN_CONTEST_ID="contest_id";
    String QUESTION_COLUMN_QUESTION="question";
    String QUESTION_COLUMN_TIME="time";


    //ANSWER
    String ANSWER_COLUMN_ID="_id";
    String ANSWER_COLUMN_ANS1="answer1";
    //String ANSWER_COLUMN_ANS2="answer2";
    //String ANSWER_COLUMN_ANS3="answer3";
    //String ANSWER_COLUMN_ANS4="answer4";
    String ANSWER_COLUMN_QUESTION_ID="question_id";



    //atentie la scrierea scriptului de creare
    String CREATE_TABLE_CONTEST = "CREATE TABLE " + CONTEST_TABLE_NAME
            + " ( " + CONTEST_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  //PRIMARY KEY
            CONTEST_COLUMN_NAME + " TEXT NOT NULL, " +
            CONTEST_COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            CONTEST_COLUMN_CODE + " INTEGER);";

    String CREATE_TABLE_QUESTION = "CREATE TABLE " + QUESTION_TABLE_NAME
            + " ( " + QUESTION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //PRIMARY KEY
                                //FOREIGN KEY
            QUESTION_COLUMN_QUESTION + " TEXT, " +
            QUESTION_COLUMN_TIME + " TEXT, "+
            QUESTION_COLUMN_CONTEST_ID + " INTEGER NOT NULL, " +
            " FOREIGN KEY ("+QUESTION_COLUMN_CONTEST_ID+") REFERENCES "+CONTEST_TABLE_NAME+"("+CONTEST_COLUMN_ID+"));";

    String CREATE_TABLE_ANSWER = "CREATE TABLE " + ANSWER_TABLE_NAME
            + " ( " + ANSWER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //PRIMARY KEY
                               //FOREIGN KEY
            ANSWER_COLUMN_ANS1 + " TEXT, " +
            //ANSWER_COLUMN_ANS2 + " TEXT, " +
            //ANSWER_COLUMN_ANS3 + " TEXT, " +
            //ANSWER_COLUMN_ANS4 + " TEXT " +
            ANSWER_COLUMN_QUESTION_ID + " INTEGER NOT NULL, "+
            " FOREIGN KEY ("+ANSWER_COLUMN_QUESTION_ID+") REFERENCES "+QUESTION_TABLE_NAME+"("+QUESTION_COLUMN_ID+"));";


    // CREATE TABLE EXPENSE (_id PRIMARY KEY INTEGER AUTOINCREMENT,


    String DROP_TABLE_CONTEST = "DROP TABLE IF EXISTS "
            + CONTEST_TABLE_NAME + ";";

    String DROP_TABLE_QUESTION = "DROP TABLE IF EXISTS "
            + QUESTION_TABLE_NAME + ";";

    String DROP_TABLE_ANSWER = "DROP TABLE IF EXISTS "
            + ANSWER_TABLE_NAME + ";";

}
