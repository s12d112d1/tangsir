package bawei.sunyubo20161124;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    int i=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        tv =(TextView)findViewById(R.id.tv);
        h.sendEmptyMessageDelayed(0,0);
    }
    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText("还有"+i+"秒进入界面");
            i=i-1;
            if(i==0){
                finish();
                h.removeCallbacksAndMessages(null);
                Intent intent=new Intent(MainActivity.this,ShowActivity.class);
                startActivity(intent);

            }
            h.sendEmptyMessageDelayed(0,1000);
        }
    };
}
