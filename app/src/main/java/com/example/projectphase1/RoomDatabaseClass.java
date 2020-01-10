package com.example.projectphase1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class},version = 1)
public abstract class RoomDatabaseClass extends RoomDatabase {
    public  abstract myDao myDao1();
}
