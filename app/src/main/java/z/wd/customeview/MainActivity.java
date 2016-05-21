package z.wd.customeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mFrameLayout = (FrameLayout) findViewById(R.id.root);
//
//        View view = new CustomeView(this);
//        mFrameLayout.addView(view);

        Button btn = (Button) findViewById(R.id.btn);
        final PaintView bezierView = (PaintView) findViewById(R.id.notepad);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bezierView.reset();
            }
        });

    }

   

}
