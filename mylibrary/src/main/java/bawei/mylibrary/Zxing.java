package bawei.mylibrary;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 1.类的用途
 * 2.@author:Sunyubo
 * 3.@ 2016/11/23.
 */
public class Zxing extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
