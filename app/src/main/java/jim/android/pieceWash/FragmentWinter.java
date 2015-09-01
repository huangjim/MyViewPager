package jim.android.pieceWash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class FragmentWinter extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_frag_winter,container,false);

        return view;

    }
}
