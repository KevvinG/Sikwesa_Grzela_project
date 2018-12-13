package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BgJob extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
            return false;

        }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}