package ro.ase.proiect.dam_project.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Contest implements Parcelable {
    private Long id;
    private String name;
    private String description;
    private Long code;
    //private boolean priv;


    public Contest(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Contest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Contest(Long id, String name, String description, Long code) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    //    public boolean isPriv() {
//        return priv;
//    }
//
//    public void setPriv(boolean priv) {
//        this.priv = priv;
//    }


    @Override
    public String toString() {
        return "Contest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code=" + code +
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
