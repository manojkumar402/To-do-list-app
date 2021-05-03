package com.example.doit.Model;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class TaskId {
    @Exclude
    public String TaskId;

    public <T extends TaskId> T withId(@NonNull final String Id){
        this.TaskId = Id;
        return (T) this;
    }
}


