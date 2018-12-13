package com.example.kevingrzela.sikwesa_grzela_project.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Conversation implements Parcelable {
    String sender, recipient, latestMsg;

    public Conversation(){
        //Default constructor required for Firebase (Much like the modified constructor for ROOM)
    }

    public Conversation(String s, String r, String m) {
        sender = s;
        recipient = r;
        latestMsg = m;
    }

    protected Conversation(Parcel in) {
        sender = in.readString();
        recipient = in.readString();
        latestMsg = in.readString();
    }

    public static final Creator<Conversation> CREATOR = new Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel in) {
            return new Conversation(in);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getLatestMsg() {
        return latestMsg;
    }

    public void setLatestMsg(String latestMsg) {
        this.latestMsg = latestMsg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sender);
        dest.writeString(recipient);
        dest.writeString(latestMsg);
    }
}
