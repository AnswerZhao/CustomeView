package z.wd.customeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wenda on 16/4/21.
 * Email:wenda@artand.cn
 */
public class PaintView extends View {

    private Path mPath = new Path();

    // 存储上一次的落点
    private float mPreX,mPreY;

    public PaintView(Context context) {
        super(context);
    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 不用贝塞尔曲线时，只是简单的点点连接
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                mPath.moveTo(event.getX(),event.getY());
//                return true;// 返回true代表拦截
//            case MotionEvent.ACTION_MOVE:
//                mPath.lineTo(event.getX(),event.getY());
//                postInvalidate();
//                break;
//        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPreX+event.getX())/2;
                float endY = (mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                // 每次移动完毕后都要重新更新上一次的坐标
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInEditMode()){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(6.0f);
            paint.setStyle(Paint.Style.STROKE);

            canvas.drawPath(mPath,paint);
        }

    }

    public void reset(){
        mPath.reset();
        invalidate();
    }
}
