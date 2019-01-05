package ro.ase.proiect.dam_project.networking;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class QuestionJson implements Parcelable, Serializable {
    private String questionName;
    private String timeLimit;
    private List<AnswerJson> answers;


    public QuestionJson(String questionName, String timeLimit, List<AnswerJson> answers) {
        this.questionName = questionName;
        this.timeLimit = timeLimit;
        this.answers = answers;
    }


    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public List<AnswerJson> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerJson> answers) {
        this.answers = answers;
    }

    public static Creator<QuestionJson> getCREATOR() {
        return CREATOR;
    }


    @Override
    public String toString() {
        return "QuestionJson{" +
                "questionName='" + questionName + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                ", answers=" + answers +
                '}';
    }

    protected QuestionJson(Parcel in) {
    }

    public static final Creator<QuestionJson> CREATOR = new Creator<QuestionJson>() {
        @Override
        public QuestionJson createFromParcel(Parcel in) {
            return new QuestionJson(in);
        }

        @Override
        public QuestionJson[] newArray(int size) {
            return new QuestionJson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
