package ro.ase.proiect.dam_project.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private Long id;
    private Long contestId;
    private String questionName;
    private String timeLimit;

    public Question(String question, String timeLimit) {
        this.questionName = question;
        this.timeLimit = timeLimit;
    }

    public Question(Long id, String questionName, String timeLimit) {
        this.id = id;
        this.questionName = questionName;
        this.timeLimit = timeLimit;
    }

    public Question(Long id, Long contestId, String questionName, String timeLimit) {
        this.id = id;
        this.contestId = contestId;
        this.questionName = questionName;
        this.timeLimit = timeLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", contestId=" + contestId +
                ", questionName='" + questionName + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                '}';
    }

    private Question(Parcel in) {
        questionName=in.readString();
        timeLimit=in.readString();
    }


    public static final Creator<Question> CREATOR =
            new Creator<Question>() {
                @Override
                public Question createFromParcel(Parcel parcel) {
                    return new Question(parcel);
                }

                @Override
                public Question[] newArray(int i) {
                    return new Question[i];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(questionName);
        parcel.writeString(timeLimit);

    }
}
