package z.wd.customeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wenda on 16/4/19.
 * Email:wenda@artand.cn
 */
public class CustomeView extends View {
    public CustomeView(Context context) {
        super(context);
    }

    public CustomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        basicCanvasAndPaint(canvas);
        usePathAndWords(canvas);
    }

    /**
     * 熟悉路径和文字
     * @param canvas
     */
    private void usePathAndWords(Canvas canvas) {
        // 准备画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);// 抗锯齿
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        // 路径相当于设定的路线，但是还是需要画笔和画布来实现的
        Path linePath = new Path();
        linePath.lineTo(100,100);
        linePath.moveTo(100,200);
        linePath.lineTo(300,600);
        linePath.lineTo(800,800);
        linePath.close();
        canvas.drawPath(linePath,paint);

        paint.setColor(Color.RED);
        Path path = new Path();
        RectF rectF = new RectF(100,300,500,800);
        path.addRect(rectF, Path.Direction.CW);
        canvas.drawPath(path,paint);

        String text = "君不见黄河之水天上来";
        paint.setColor(Color.BLUE);
        paint.setTextSize(30);
        canvas.drawTextOnPath(text,path,0,0,paint);

        RectF rectF1 = new RectF(400,600,900,1000);
        path.addRoundRect(rectF1,10,30, Path.Direction.CW);
        canvas.drawPath(path,paint);

        canvas.drawCircle(600,600,200,paint);

        path.addArc(rectF,30,60);
        paint.setColor(Color.RED);
        canvas.drawPath(path,paint);

    }

    /**
     * 熟悉画板和画笔
     * @param canvas
     */
    private void basicCanvasAndPaint(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5.0f);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRGB(125,255,188);

        canvas.drawCircle(200,200,50,paint);

        canvas.drawCircle(250,200,50,paint);

        canvas.drawRect(200,200,800,800,paint);

        Rect rect = new Rect(400,400,800,800);
        canvas.drawRect(rect,paint);
        RectF rectF = new RectF(500,500,1000,1000);
        canvas.drawRoundRect(rectF,50,50,paint);

        RectF rectF1 = new RectF(800,800,1080,1600);
        canvas.drawOval(rectF1,paint);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF1,30,30,true,paint);
    }


}
