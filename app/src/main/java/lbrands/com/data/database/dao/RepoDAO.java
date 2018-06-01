package lbrands.com.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import lbrands.com.data.model.RepoResponseModel;

@Dao
public interface RepoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<RepoResponseModel> listRepo);

    @Query("SELECT * FROM repo")
    LiveData<List<RepoResponseModel>> getRepoList();
}
