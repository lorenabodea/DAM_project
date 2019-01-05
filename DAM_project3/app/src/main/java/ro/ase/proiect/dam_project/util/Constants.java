package ro.ase.proiect.dam_project.util;

import java.text.SimpleDateFormat;

import static android.provider.Settings.System.DATE_FORMAT;

public interface Constants {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    int REQUEST_CODE = 101;
    int REQ_CODE=102;
    String KEY = "KEY";

    int UPDATE_CONTEST_REQUEST_CODE = 102;
    String DELETE_CONTEST = "Delete a contest";

    int UPDATE_QUESTION_REQUEST_CODE = 103;
    String DELETE_QUESTION = "Delete a contest";

    int UPDATE_ANSWER_REQUEST_CODE = 104;
    String DELETE_ANSWER = "Delete a contest";

    String CONTEST_ID = "CONTEST_ID";
    String CONTEST_NAME = "CONTEST_NAME";
    String CONTEST_DESCRIPTION = "CONTEST_DESCRIPTION";
    String CONTEST_CODE = "CONTEST_CODE";
    String CONTEST_STUDENTS = "CONTEST_STUDENTS";


    String QUESTION_ID = "QUESTION_ID";
    String QUESTION_NAME = "QUESTION_NAME";
    String QUESTION_TIME = "QUESTION_TIME";

    String CHART_CONTESTS_KEY = "ChartContestsKey";

    int INSERT_USER_REQUEST_CODE = 105;
    int UPDATE_USER_REQUEST_CODE = 106;
    String ADD_USER_KEY = "Add user KEY";

    String LOGSTUD_PREF_FILE_NAME="logUSerPref";
    String LOGSTUD_PREF_USERNAME="usernamePref";
    String LOGSTUD_PREF_PASSWORD="passwwordPref";
}
