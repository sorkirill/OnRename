package com.example.onrename;


import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.onrename.ROOM.DataBase;

public class App extends Application {

    public static App instance;

    private DataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "database")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}