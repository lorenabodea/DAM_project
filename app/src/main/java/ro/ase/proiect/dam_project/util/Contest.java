package ro.ase.proiect.dam_project.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Contest implements Parcelable {
    private String name;
    private String description;


    public Contest(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "Contest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    private Contest(Parcel in) {
        name=in.readString();
        description=in.readString();
    }


    public static final Creator<Contest> CREATOR =
            new Creator<Contest>() {
                @Override
                public Contest createFromParcel(Parcel parcel) {
                    return new Contest(parcel);
                }

                @Override
                public Contest[] newArray(int i) {
                    return new Contest[i];
                }
            };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);

    }
}
