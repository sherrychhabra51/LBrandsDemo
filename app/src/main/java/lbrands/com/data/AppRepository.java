package lbrands.com.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import lbrands.com.data.database.DBManager;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.model.RepoResponseModel;
import lbrands.com.data.network.NetworkManager;
import retrofit2.Call;

public class AppRepository implements Repository {

    private NetworkManager networkManager;
    private DBManager dbManager;

    public AppRepository(NetworkManager networkManager, DBManager dbManager) {
        this.networkManager = networkManager;
        this.dbManager = dbManager;
    }

    @Override
    public MutableLiveData<LoginResponseModel> getUserDetails(String userName) {
        return networkManager.getUserDetails(userName);
    }

    @Override
    public Observable<Boolean> saveLoginDetails(LoginResponseModel loginResponseModel) {
        return dbManager.saveLoginDetails(loginResponseModel);
    }

    @Override
    public Observable<LoginResponseModel>  getLoginDetails() {
        return dbManager.getLoginDetails();
    }

    @Override
    public Observable<Boolean> saveRepoList(ArrayList<RepoResponseModel> listRepo) {
        return dbManager.saveRepoList(listRepo);
    }

    @Override
    public LiveData<List<RepoResponseModel>> getRepoList(String userName, int pageNo, int perPageSize) {
        return dbManager.getRepoList(userName, pageNo, perPageSize);
    }

    @Override
    public Observable<Boolean> clearDB() {
        return dbManager.clearDB();
    }

    @Override
    public Call<ArrayList<RepoResponseModel>> fetchUserRepo(String userName, int pageNo, int perPageSize) {
        return networkManager.fetchUserRepo(userName, pageNo, perPageSize);
    }

}
