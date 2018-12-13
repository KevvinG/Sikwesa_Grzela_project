package com.example.kevingrzela.sikwesa_grzela_project.Model;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Message implements Comparable<Message> {
        String message;
        long time;
        String sender, receiver;
        int userId;

        public Message(String m, long t, String sender, String receiver, int userId) {
            message = m;
            time = t;
            this.sender = sender;
            this.receiver = receiver;
            this.userId = userId;
        }

         public Map<String, Object> toMap() {
             HashMap<String, Object> result = new HashMap<>();
             result.put("sender", sender);
             result.put("receiver", receiver);
             result.put("message", message);
             result.put("time", time);

             return result;
         }

        public String getMessage() {
            return message;
        }

        public long getTime() {
            return time;
        }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public int compareTo(@NonNull Message o) {
        return Long.compare(this.getTime(), o.getTime());

    }
}

