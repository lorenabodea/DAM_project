package ro.ase.proiect.dam_project.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable{

    private Long id;
    private Long questionId;
    private String answer;

    public Answer(Long id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Answer(Long id, Long questionId, String answer) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }

    private Answer(Parcel in) {
        answer=in.readString();

    }


    public static final Creator<Answer> CREATOR =
            new Creator<Answer>() {
                @Override
                public Answer createFromParcel(Parcel parcel) {
                    return new Answer(parcel);
                }

                @Override
                public Answer[] newArray(int i) {
                    return new Answer[i];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(answer);
    }
}
