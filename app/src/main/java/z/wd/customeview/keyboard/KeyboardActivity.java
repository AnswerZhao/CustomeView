package z.wd.customeview.keyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import z.wd.customeview.R;

/**
 * Created by wenda on 16/5/16.
 * Email:wenda@artand.cn
 */
public class KeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer,new KeyboardFragment()).commit();
    }
}
