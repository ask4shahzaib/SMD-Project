package com.example.projectphase1;

import androidx.room.Insert;
import androidx.room.Query;

@androidx.room.Dao
public interface myDao {
    @Insert
    public void addProfileEntity(UserEntity userEntity);
    @Query("select* from tableprofile where profile_username=:username")
    public UserEntity getprofile(String username);
}
