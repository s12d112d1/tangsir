package util.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Sunyubo on 2016/11/8.
 */
public class Circle extends View{

    private Paint p;
    private Rect rect;
    private float r;
    private int width;
    private int height;

    public float getR() {
        return r;
    }

    public Circle(Context context) {
        this(context,null);
    }

    public Circle(Context context, AttributeSet attrs) {
       this(context, attrs,0);
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }
//测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize= MeasureSpec.getSize(heightMeasureSpec);

        int width,height;

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
            r = width/2;
        }else {
            width =100;
            r =width/2;
        }
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
          p= new Paint();
         rect= new Rect();
        width =getWidth();
        height =getHeight();
        p.setColor(Color.YELLOW);
        canvas.drawCircle(width/2,height/2,r,p);
        p.setColor(Color.BLACK);
        p.setTextSize(20);
        String str="下一步";
        p.getTextBounds(str,0,str.length(),rect);
        canvas.drawText(str,width/2-rect.width()/2,height/2+rect.height()/2,p);
    }
}
