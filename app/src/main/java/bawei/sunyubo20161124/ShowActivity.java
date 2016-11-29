package bawei.sunyubo20161124;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adpter.MyAdpter;
import bawei.mylibrary.OkHttpUtils;
import bawei.mylibrary.XListView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ShowActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show);
        init();
    }
    private XListView lv;
    int index=1;
    List<Bean.ResultBean.DataBean> listall=new ArrayList<>();




    public void init() {
        lv = (XListView) findViewById(R.id.xlv);
       //允许它加载更多
		lv.setPullLoadEnable(true);
		//允许刷新
        lv.setPullRefreshEnable(true);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowActivity.this);
				//设置标题图片
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("你确定要删除吗？");
               //设置确定按钮
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 这里添加点击确定后的逻辑
                       /* new AlertDialog.Builder(ShowActivity.this).setMessage("你选择了确定")
                                .show();*/
                        listall.remove(i-1);
                        ad.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 这里添加点击确定后的逻辑
                       /* new AlertDialog.Builder(ShowActivity.this).setMessage("你选择了取消")
                                .show();*/
                    }
                });
                builder.create().show();

                //Toast.makeText(ShowActivity.this,i+"",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        lv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                index++;
                getdata("http://japi.juhe.cn/joke/content/list.from?key= 874ed931559ba07aade103eee279bb37 &page="+index+"&pagesize=10&sort=asc&time=1418745237",2);

            }

            @Override
            public void onLoadMore() {
                index++;
                getdata("http://japi.juhe.cn/joke/content/list.from?key= 874ed931559ba07aade103eee279bb37 &page="+index+"&pagesize=10&sort=asc&time=1418745237",3);

            }
        });
        getdata("http://japi.juhe.cn/joke/content/list.from?key= 874ed931559ba07aade103eee279bb37 &page="+index+"&pagesize=10&sort=asc&time=1418745237",1);
    }

    private void getdata(String s, final int i) {
        OkHttpUtils.get(s, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                            String s=response.body().string();
                    Message m=Message.obtain();
                m.obj=s;
                m.arg1=i;
                h.sendMessage(m);
            }
        });
    }

    private MyAdpter ad;
    Handler h=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        String s= (String) msg.obj;
        Gson g=new Gson();
        List<Bean.ResultBean.DataBean> list=g.fromJson(s,Bean.class).result.data;
        int tag=msg.arg1;
        switch (tag) {
            case 1:
                listall.addAll(list);
                 ad = new MyAdpter(listall,ShowActivity.this);
                lv.setAdapter(ad);
                break;
            case 2:
                listall.clear();
                listall.addAll(list);
                ad.notifyDataSetChanged();
                //停止刷新
				lv.stopRefresh();
                break;
            case 3:
                listall.addAll(list);
                ad.notifyDataSetChanged();
                //停止加载更多
				lv.stopLoadMore();
                break;

            default:
                break;
        }

    }
};
    long firstTime=0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {//如果两次按键时间间隔大于800毫秒，则不退出
                Toast.makeText(ShowActivity.this, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                firstTime = secondTime;//更新firstTime
                return true;
            } else {
                System.exit(0);//否则退出程序
            }
        }
        return super.onKeyUp(keyCode, event);
    }

}
