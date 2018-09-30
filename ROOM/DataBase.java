package com.example.onrename.ROOM;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import java.lang.annotation.Annotation;

@Database(entities = {Employee.class}, version = 1)
public abstract class DataBase extends RoomDatabase implements Database {
    public abstract EmployeeDao employeeDao();

    @Override
    public Class[] entities() {
        return new Class[0];
    }

    @Override
    public int version() {
        return 0;
    }

    @Override
    public boolean exportSchema() {
        return false;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}