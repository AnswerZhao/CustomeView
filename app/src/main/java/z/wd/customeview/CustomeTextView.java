package z.wd.customeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by wenda on 16/4/21.
 * Email:wenda@artand.cn
 */
public class CustomeTextView extends View{

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;

    private Paint mPaint;
    private Rect mRectBounds;

    public CustomeTextView(Context context) {
        this(context,null);
    }

    public CustomeTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode()){
            init(context,attrs,defStyleAttr);
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  生成内容
                    mTitleText = getRandomText();
                    postInvalidate();
                }
            });
        }
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomeTextView,defStyleAttr,0);

        try {
            mTitleText = typedArray.getString(R.styleable.CustomeTextView_zText);
            mTitleTextColor = typedArray.getColor(R.styleable.CustomeTextView_zTextColor, Color.CYAN);
            mTitleTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomeTextView_zTextSize,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
        } finally {
            typedArray.recycle();
        }

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);

        mRectBounds=new Rect();
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(), mRectBounds);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInEditMode()){ // 非编辑模式才有下面的绘制操作，如果不加入!isInEditMode()的判断，那么预览界面就会有警告
            mPaint.setColor(Color.GREEN);
            canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

            mPaint.setColor(mTitleTextColor);
            canvas.drawText(mTitleText,
                    (getWidth()-mRectBounds.width())/2, // 绘制文字的起始坐标
                    getHeight()/2+mRectBounds.height()/2,// 绘制文字的起始Y坐标
                    mPaint);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(!isInEditMode()){
            int width;
            int height;
            if(widthMode == MeasureSpec.EXACTLY){ // 一般是设置了明确的值或者是MATCH_PARENT
                width = widthSize;
            }else{ // 一般为WARP_CONTENT
                mPaint.setTextSize(mTitleTextSize);
                mPaint.getTextBounds(mTitleText,0,mTitleText.length(), mRectBounds);
                float textWidth = mRectBounds.width();
                int targetSize = (int) (getPaddingLeft()+textWidth+getPaddingRight());
                width = targetSize;
            }

            if(heightMode == MeasureSpec.EXACTLY){
                height = heightSize;
            }else{
                mPaint.setTextSize(mTitleTextSize);
                mPaint.getTextBounds(mTitleText,0,mTitleText.length(), mRectBounds);
                float textHeight = mRectBounds.height();
                int targetHeight = (int) (getPaddingTop()+textHeight+getPaddingBottom());
                height = targetHeight;
            }

            // 使设置的尺寸生效
            setMeasuredDimension(width,height);
        }else{
            setMeasuredDimension(widthSize,heightSize);
        }

    }


    public String getRandomText() {
        Random random = new Random();

        Set<String> set = new HashSet<>();
        while (set.size()<4){
            set.add(random.nextInt(10)+"");
        }

        StringBuilder sb = new StringBuilder();
        for (String s : set) {
            sb.append(s);
        }

        return sb.toString();
    }
}
