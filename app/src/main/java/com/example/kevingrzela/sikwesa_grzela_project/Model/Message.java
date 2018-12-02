package com.example.kevingrzela.sikwesa_grzela_project.Model;

public class Message {
        String message, time, name;
        int userId;

        public Message(String m, String t, String n, int user) {
            message = m;
            time = t;
            name = n;
            userId = user;
        }

        String getMessage() {
            return message;
        }

        String getTime() {
            return time;
        }

        String getName() {
            return name;
        }

        int getUserId() {
            return userId;
        }
    }

