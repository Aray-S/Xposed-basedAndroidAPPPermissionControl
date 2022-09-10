package com.app.example;

import android.app.Application;

import com.app.example.project.MyObjectBox;

import io.objectbox.BoxStore;

public class App extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        getBoxStore();

    }

    public BoxStore getBoxStore() {
        if (boxStore == null)
            boxStore = MyObjectBox.builder().androidContext(App.this).build();
        return boxStore;
    }
}
