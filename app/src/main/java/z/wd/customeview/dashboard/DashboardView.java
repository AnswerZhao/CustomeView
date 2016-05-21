package z.wd.customeview.dashboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import z.wd.customeview.R;

/**
 * Created by wenda on 16/5/16.
 * Email:wenda@artand.cn
 */
public class DashboardView extends View {


    private Paint mPaint;
    private Paint mTextPaint;
    private int cx;
    private int cy;
    private int radius;
    private int screenWidth;
    private int screenHeight;
    private int mDensityDpi;
    private int textRidus;

    public DashboardView(Context context) {
        this(context,null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode()){
            init(context,attrs,defStyleAttr);
        }
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Dashboard,defStyleAttr,0);
        try {
            cx = typedArray.getDimensionPixelSize(R.styleable.Dashboard_cx,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,180,getResources().getDisplayMetrics()));

            cy = typedArray.getDimensionPixelSize(R.styleable.Dashboard_cx,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,300,getResources().getDisplayMetrics()));

            radius = typedArray.getDimensionPixelSize(R.styleable.Dashboard_cx,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));


        }finally {
            typedArray.recycle();
        }

        mPaint = new Paint();
        mTextPaint = new Paint();

        //获取屏幕宽高 和 屏幕密度dpi
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        mDensityDpi = displayMetrics.densityDpi / 320;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInEditMode()){
           customeDraw(canvas);
        }
    }

    private void customeDraw(Canvas canvas) {

        canvas.drawColor(Color.BLACK);

        drawCircle(canvas);

        drawDial(canvas);// 绘制刻度盘

        // 绘制文字
        mTextPaint.setTextSize(24*mDensityDpi);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.WHITE);
        // 文字靠外圆的圆半径
        textRidus = radius - 50 * mDensityDpi;
        drawText(mTextPaint,textRidus);
    }

    // 沿四周绘制文字
    private void drawText(Paint textPaint, int textRidus) {

    }

    private void drawCircle(Canvas canvas) {
        // 画的背景
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF343434);
        canvas.drawCircle(cx,cy,radius,mPaint);

        // 外圆1
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xBF3F6AB5);
        mPaint.setStrokeWidth(4*mDensityDpi);
        canvas.drawCircle(cx,cy,radius,mPaint);
        // 外圆2
        mPaint.setStrokeWidth(4*mDensityDpi);
        canvas.drawCircle(cx,cy,radius-10*mDensityDpi,mPaint);


        //内圈2个圆
        mPaint.setStrokeWidth(3 * mDensityDpi);
        mPaint.setColor(0xE73F51B5);
        canvas.drawCircle(cx, cy, radius / 2, mPaint);
        canvas.drawCircle(cx, cy, radius / 2 + 10 * mDensityDpi, mPaint);
    }

    private void drawDial(Canvas canvas) {
        mPaint.setColor(0xBF3F6AB5);
        for (int i = 0; i < 60; i++) {
            if(i % 6 == 0){
                canvas.drawLine(cx-radius+10*mDensityDpi,cy,cx-radius+50*mDensityDpi,cy,mPaint);
            }else{
                canvas.drawLine(cx-radius+10*mDensityDpi,cy,cx-radius+30*mDensityDpi,cy,mPaint);
            }
            // 刻度是斜线，怎么实现呢，简单的方法，选择画布
            canvas.rotate(6,cx,cy);
        }
    }
}

