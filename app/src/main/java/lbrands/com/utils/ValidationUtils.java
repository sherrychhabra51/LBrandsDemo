package lbrands.com.utils;

import android.text.TextUtils;

public class ValidationUtils {

    public static boolean isInputValid(String input){
        return ! TextUtils.isEmpty(input);
    }
}
