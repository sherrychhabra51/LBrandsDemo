package lbrands.com.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import lbrands.com.data.model.LoginResponseModel;

@Dao
public interface LoginDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserDetail(LoginResponseModel loginResponseModel);

    @Query("SELECT * FROM user")
    LoginResponseModel getUserDetail();
}
