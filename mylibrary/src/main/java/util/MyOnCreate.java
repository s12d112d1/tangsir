package util;

import android.os.Bundle;

/**
 * Created by Sunyubo on 2016/11/9.
 */
public interface MyOnCreate {
    //绑定布局文件
    int bindLayout();
    //初始化数据
    void initData();
    //初始化控件
    void initView(Bundle savedInstanceState);
    //加载网络数据
    void loadData();
}
