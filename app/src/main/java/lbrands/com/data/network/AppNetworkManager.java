package lbrands.com.data.network;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import lbrands.com.data.AppRepository;
import lbrands.com.data.model.ErrorResponseModel;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.model.RepoResponseModel;
import lbrands.com.lbrandsassignment.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppNetworkManager implements NetworkManager {
    private APIService apiService;
    private final Context mContext;

    public AppNetworkManager(Context mContext, APIService apiService) {
        this.apiService = apiService;
        this.mContext = mContext;
    }

    @Override
    public MutableLiveData<LoginResponseModel> getUserDetails(String userName) {
        MutableLiveData<LoginResponseModel> liveLoginResponse = new MutableLiveData<>();
        apiService.loginUser(userName).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveLoginResponse.setValue(response.body());
                } else {
                    String errorMsg =APIErrorHandler.getErrorMsg(mContext,response);
                    liveLoginResponse.setValue(getErrorModel(errorMsg));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                String errorMsg = mContext.getString(R.string.error_network_not_responding);
                liveLoginResponse.setValue(getErrorModel(errorMsg));
            }
        });
        return liveLoginResponse;
    }

    @Override
    public Call<ArrayList<RepoResponseModel>> fetchUserRepo(String userName, int pageNo, int perPageSize) {
        return apiService.getRepoList(userName,pageNo,perPageSize);
    }

    private LoginResponseModel getErrorModel(String errorMsg) {
        LoginResponseModel loginResponseModel = new LoginResponseModel();
        ErrorResponseModel errorResponseModel = new ErrorResponseModel();
        errorResponseModel.setMessage(errorMsg);
        loginResponseModel.setErrorResponseModel(errorResponseModel);
        return loginResponseModel;
    }
}
