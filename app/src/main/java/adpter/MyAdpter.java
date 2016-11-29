package adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import bawei.sunyubo20161124.Bean;
import bawei.sunyubo20161124.R;

/**
 * 1.类的用途
 * 2.@author:Sunyubo
 * 3.@ 2016/11/24.
 */
public class MyAdpter extends BaseAdapter{
    List<Bean.ResultBean.DataBean> list;
    Context context;

    public MyAdpter(List<Bean.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Bean.ResultBean.DataBean s=list.get(i);
        Vh v;
        if(view==null){
          view=View.inflate(context, R.layout.item,null);
            v=new Vh();
            v.t1=(TextView)view.findViewById(R.id.text1);
            v.t2=(TextView)view.findViewById(R.id.text2);
            view.setTag(v);
        }else{
            v= (Vh) view.getTag();
        }
        v.t1.setText(s.content);
        v.t2.setText(s.updatetime);
        return view;
    }
    class Vh{
        TextView t1;
        TextView t2;
    }

}
