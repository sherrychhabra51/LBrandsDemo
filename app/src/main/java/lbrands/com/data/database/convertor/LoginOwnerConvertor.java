package lbrands.com.data.database.convertor;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import lbrands.com.data.model.LoginResponseModel;

public class LoginOwnerConvertor {

    @TypeConverter
    public String fromLoginOwner(LoginResponseModel loginResponseModel) {
        if (loginResponseModel == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LoginResponseModel>() {}.getType();
        return gson.toJson(loginResponseModel, type);
    }

    @TypeConverter
    public LoginResponseModel toLoginOwner(String loginResponseStr) {
        if (loginResponseStr == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<LoginResponseModel>() {}.getType();
        return gson.fromJson(loginResponseStr, type);
    }
}
