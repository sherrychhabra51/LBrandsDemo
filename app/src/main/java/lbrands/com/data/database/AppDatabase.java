package lbrands.com.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import lbrands.com.data.database.convertor.DateConverter;
import lbrands.com.data.database.convertor.LoginOwnerConvertor;
import lbrands.com.data.database.dao.LoginDAO;
import lbrands.com.data.database.dao.RepoDAO;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.model.RepoResponseModel;

@Database(entities = {LoginResponseModel.class, RepoResponseModel.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class, LoginOwnerConvertor.class})

public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "lBrandsDB";
    private static AppDatabase dbInstance;
    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        if (dbInstance == null) {
            synchronized (LOCK) {
                dbInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).build();
            }
        }
        return dbInstance;
    }

    public void clear(){
        dbInstance.clearAllTables();
    }

    public abstract RepoDAO repoDAO();

    public abstract LoginDAO loginDAO();
}
