package com.example.projectphase1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableprofile")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    int profile_username;
    @ColumnInfo(name="profile_name")
    String profile_name;
    @ColumnInfo(name="profile_email")
    String profile_email;
    @ColumnInfo(name="profile_phone")
    String profile_phone;
    @ColumnInfo(name="profile_adress")
    String profile_adress;

    public UserEntity( String profile_name, String profile_email, String profile_phone, String profile_adress) {
        this.profile_username = profile_username;
        this.profile_name = profile_name;
        this.profile_email = profile_email;
        this.profile_phone = profile_phone;
        this.profile_adress = profile_adress;
    }
}
