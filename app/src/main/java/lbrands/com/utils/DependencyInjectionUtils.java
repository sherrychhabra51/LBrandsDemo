package lbrands.com.utils;

import android.content.Context;

import lbrands.com.data.AppRepository;
import lbrands.com.data.Repository;
import lbrands.com.data.database.AppDBManager;
import lbrands.com.data.database.AppDatabase;
import lbrands.com.data.database.DBManager;
import lbrands.com.data.network.APIClient;
import lbrands.com.data.network.APIService;
import lbrands.com.data.network.AppNetworkManager;
import lbrands.com.data.network.NetworkManager;

public class DependencyInjectionUtils {

    public static Repository provideRepository(Context mContext) {
        NetworkManager networkManager = provideNetworkManager(mContext);
        DBManager dbManager = provideDatabaseManager(mContext);
        Repository appRepository = new AppRepository(networkManager, dbManager);
        return appRepository;
    }

    private static DBManager provideDatabaseManager(Context mContext) {
        AppDatabase appDB = AppDatabase.getInstance(mContext);
        DBManager dbManager = new AppDBManager(appDB);
        return dbManager;
    }

    private static NetworkManager provideNetworkManager(Context mContext) {
        APIService retrofitService = APIClient.getClient(mContext).create(APIService.class);
        NetworkManager networkManager = new AppNetworkManager(mContext, retrofitService);
        return networkManager;
    }

}
