package jim.android.pay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/21.
 */
public class PayFragment02 extends Fragment {

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.pay_activity02,container,false);

        return view;

    }
}
