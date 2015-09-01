package jim.android.pay;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/21.
 */
public class PayFragment01 extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout layout;
    private Button surePay;
    private TextView box1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.pay_activity01,container,false);
        initView();
        return view;

    }

    private void initView() {
        layout=(LinearLayout)view.findViewById(R.id.pay_password);
        surePay=(Button)view.findViewById(R.id.surePay);
        box1=(TextView)view.findViewById(R.id.password_box1);
        //layout.setOnClickListener(this);
        surePay.setOnClickListener(this);
        box1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_password:
                layout.requestFocus();
                InputMethodManager imm=(InputMethodManager)layout.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0,InputMethodManager.SHOW_FORCED);
                break;
            case R.id.surePay:
                break;
            case R.id.password_box1:

                break;
        }

    }
}
