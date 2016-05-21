package z.wd.customeview.scatter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import z.wd.customeview.R;

/**
 * Created by wenda on 16/5/16.
 * Email:wenda@artand.cn
 */
public class ScatterActivity extends AppCompatActivity implements View.OnClickListener{

    Button mMenu;
    Button mItem1;
    Button mItem2;
    Button mItem3;
    Button mItem4;
    Button mItem5;

    private boolean isOpen = false;
    private int radius = 400;
    private int itemNum = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatter);

        mMenu = (Button) findViewById(R.id.menu);
        mItem1 = (Button) findViewById(R.id.item1);
        mItem2 = (Button) findViewById(R.id.item2);
        mItem3 = (Button) findViewById(R.id.item3);
        mItem4 = (Button) findViewById(R.id.item4);
        mItem5 = (Button) findViewById(R.id.item5);

        mMenu.setOnClickListener(this);
        mItem1.setOnClickListener(this);
        mItem2.setOnClickListener(this);
        mItem3.setOnClickListener(this);
        mItem4.setOnClickListener(this);
        mItem5.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu:
                toScatter();
                break;
            case R.id.item1:
            case R.id.item2:
            case R.id.item3:
            case R.id.item4:
            case R.id.item5:
                closeAll();
                break;
        }
    }

    private void toScatter() {
        if(isOpen){
            closeAll();
        }else{
            openItem(mItem1,0);
            openItem(mItem2,1);
            openItem(mItem3,2);
            openItem(mItem4,3);
            openItem(mItem5,4);
            isOpen = true;
        }
    }

    private void closeAll() {
        closeItem(mItem1,0);
        closeItem(mItem2,1);
        closeItem(mItem3,2);
        closeItem(mItem4,3);
        closeItem(mItem5,4);
        isOpen = false;
    }

    private void openItem(View view,int index) {
        if(view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90)/(itemNum-1)*index;// 每个的旋转角度
        double dx = -radius*Math.sin(degree);
        double dy = -radius*Math.cos(degree);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",0,(int)dx),
                ObjectAnimator.ofFloat(view,"translationY",0,(int)dy),
                ObjectAnimator.ofFloat(view,"alpha",0,1),
                ObjectAnimator.ofFloat(view,"scaleX",0,1),
                ObjectAnimator.ofFloat(view,"scaleY",0,1));
        animatorSet.setDuration(300).start();
    }

    private void closeItem(final View view, int index) {
        if(view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90)/(itemNum-1)*index;// 每个的旋转角度
        double dx = -radius*Math.sin(degree);
        double dy = -radius*Math.cos(degree);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",(int)dx,0),
                ObjectAnimator.ofFloat(view,"translationY",(int)dy,0),
                ObjectAnimator.ofFloat(view,"alpha",1,0),
                ObjectAnimator.ofFloat(view,"scaleX",1,0),
                ObjectAnimator.ofFloat(view,"scaleY",1,0));
        animatorSet.setDuration(300).start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 防止点击重叠
                if(view.getVisibility() == View.VISIBLE){
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
