package lbrands.com.data.network;

import android.content.Context;

import java.io.IOException;
import java.lang.annotation.Annotation;

import lbrands.com.data.model.ErrorResponseModel;
import lbrands.com.lbrandsassignment.R;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class APIErrorHandler {

    public static String getErrorMsg(Context mContext,Response response) {
        String errorMessage = "";
        try {
            Converter<ResponseBody, ErrorResponseModel> converter = APIClient.getClient(mContext).responseBodyConverter(ErrorResponseModel.class, new Annotation[0]);
            errorMessage = converter.convert(response.errorBody()).getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessage = mContext.getString(R.string.error_network_not_responding);
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = mContext.getString(R.string.error_exception);
        }
        return errorMessage;
    }
}
