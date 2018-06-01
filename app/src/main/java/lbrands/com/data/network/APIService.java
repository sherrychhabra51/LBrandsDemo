package lbrands.com.data.network;

import java.util.ArrayList;
import java.util.List;

import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.model.RepoResponseModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    String API_BASE_URL = "https://api.github.com/";

    @GET("users/{username}")
    Call<LoginResponseModel> loginUser(@Path("username") String userName);


    @GET("users/{username}/repos")
    Call<ArrayList<RepoResponseModel>> getRepoList(@Path("username") String user, @Query("page")int pageNo,@Query("per_page")int perPage);
}
