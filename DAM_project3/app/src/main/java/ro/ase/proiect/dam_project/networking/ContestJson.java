package ro.ase.proiect.dam_project.networking;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class ContestJson implements Parcelable, Serializable {
    private String name;
    private String description;
    private List<QuestionJson> questions;

    public ContestJson(String name, String description, List<QuestionJson> questions) {
        this.name = name;
        this.description = description;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionJson> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionJson> questions) {
        this.questions = questions;
    }

    public static Creator<ContestJson> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "ContestJson{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", questions=" + questions +
                '}';
    }

    protected ContestJson(Parcel in) {
    }

    public static final Creator<ContestJson> CREATOR = new Creator<ContestJson>() {
        @Override
        public ContestJson createFromParcel(Parcel in) {
            return new ContestJson(in);
        }

        @Override
        public ContestJson[] newArray(int size) {
            return new ContestJson[size];
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
