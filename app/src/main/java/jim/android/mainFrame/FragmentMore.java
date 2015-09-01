package jim.android.mainFrame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import jim.android.Splash.R;

/**
 * Created by huangjim on 8/14/2015.
 */
public class FragmentMore extends Fragment implements View.OnClickListener {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_fragment_more, container, false);

        return view;

    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CustomViewForMore customViewForMore1 = (CustomViewForMore) view.findViewById(R.id.myitem01);
        customViewForMore1.setOnClickListener(this);
    }

    private void showToast(String msg) {

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.myitem01:
                showToast("myitem01");
                break;
            case R.id.myitem02:
                showToast("myitem02");
                break;
            case R.id.myitem03:
                showToast("myitem03");
                break;
            case R.id.myitem04:
                showToast("myitem0");
                break;
            case R.id.myitem05:
                showToast("myitem05");
                break;
            case R.id.myitem06:
                showToast("myitem06");
                break;
        }
    }
}
