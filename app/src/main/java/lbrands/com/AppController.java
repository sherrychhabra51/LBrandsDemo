package lbrands.com;

import android.app.Application;

import com.facebook.stetho.Stetho;

import lbrands.com.lbrandsassignment.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/avenirnext_regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
