package lbrands.com.data.database;

import android.arch.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.model.RepoResponseModel;

public interface DBManager {
    Observable<Boolean> saveLoginDetails(LoginResponseModel loginResponseModel);

    Observable<LoginResponseModel> getLoginDetails();

    Observable<Boolean> saveRepoList(ArrayList<RepoResponseModel> listRepo);

    LiveData<List<RepoResponseModel>> getRepoList(String userName, int pageNo, int perPageSize);

    Observable<Boolean> clearDB();
}
