package com.example.kevingrzela.sikwesa_grzela_project.Model;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Message implements Comparable<Message> {
        String message, name;
        long time;
        int userId;

        public Message(String m, String n, long t, int u) {
            message = m;
            time = t;
            name = n;
            userId = u;
        }

         public Map<String, Object> toMap() {
             HashMap<String, Object> result = new HashMap<>();
             result.put("uid", userId);
             result.put("message", message);
             result.put("name", name);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
            return name;
        }

        public int getUserId() {
            return userId;
        }

    @Override
    public int compareTo(@NonNull Message o) {
        return Long.compare(this.getTime(), o.getTime());

    }
}

