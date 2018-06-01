package lbrands.com.data.network;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;

import lbrands.com.data.AppRepository;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.model.RepoResponseModel;
import retrofit2.Call;

public interface NetworkManager {

    MutableLiveData<LoginResponseModel> getUserDetails(String userName);

    Call<ArrayList<RepoResponseModel>> fetchUserRepo(String userName, int pageNo, int perPageSize);
}
