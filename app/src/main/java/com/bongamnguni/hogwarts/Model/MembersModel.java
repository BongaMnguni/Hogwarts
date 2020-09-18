package com.bongamnguni.hogwarts.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MembersModel implements Parcelable {
    private String member;

    public MembersModel(){}

    protected MembersModel(Parcel in) {
        member = in.readString();
    }

    public static final Creator<MembersModel> CREATOR = new Creator<MembersModel>() {
        @Override
        public MembersModel createFromParcel(Parcel in) {
            return new MembersModel(in);
        }

        @Override
        public MembersModel[] newArray(int size) {
            return new MembersModel[size];
        }
    };

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(member);
    }
}
