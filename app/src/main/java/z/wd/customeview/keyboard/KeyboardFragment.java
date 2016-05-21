package z.wd.customeview.keyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import z.wd.customeview.R;

/**
 * Created by wenda on 16/5/16.
 * Email:wenda@artand.cn
 */
public class KeyboardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyboard,container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
