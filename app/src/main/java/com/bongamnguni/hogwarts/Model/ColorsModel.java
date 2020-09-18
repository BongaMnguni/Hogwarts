package com.bongamnguni.hogwarts.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ColorsModel implements Parcelable {
    private String color;

    public ColorsModel(){}

    protected ColorsModel(Parcel in) {
        color = in.readString();
    }

    public static final Creator<ColorsModel> CREATOR = new Creator<ColorsModel>() {
        @Override
        public ColorsModel createFromParcel(Parcel in) {
            return new ColorsModel(in);
        }

        @Override
        public ColorsModel[] newArray(int size) {
            return new ColorsModel[size];
        }
    };

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(color);
    }
}
