package kr.co.team.LKLH.ufit;

import android.app.Application;
import android.content.Context;

/**
 * Created by Admin on 2016-07-26.
 */
public class UFitApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static Context getUFitContext(){
        return mContext;
    }
}
