package lbrands.com.data;

import android.arch.lifecycle.MutableLiveData;

import lbrands.com.data.database.DBManager;
import lbrands.com.data.network.NetworkManager;

public interface Repository extends NetworkManager,DBManager{


}
