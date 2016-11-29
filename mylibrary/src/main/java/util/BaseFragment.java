package util;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sunyubo on 2016/11/11.
 */
abstract public class BaseFragment extends Fragment implements  MyOnCreate {

    private View view;

    @Nullable
    @Override
    public View getView() {
        return view;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), bindLayout(), null);
        initView(savedInstanceState);
        initData();
        loadData();
        return view;
    }

    public String getAssets(String fileName) {
        try {
            InputStream in=getActivity().getAssets().open(fileName);
            ByteArrayOutputStream o=new ByteArrayOutputStream();
            byte[] b=new byte[1024];
            int lenth=0;
            while((lenth=in.read(b))!=-1){
                o.write(b,0,lenth);
            }
            return o.toString("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends View> T findview(int viewid) {
        return (T)view.findViewById(viewid);
    }


}
