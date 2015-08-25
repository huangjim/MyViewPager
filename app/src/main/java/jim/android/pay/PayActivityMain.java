package jim.android.pay;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import jim.android.adapter.MyPagerAdapter;
import jim.android.indexViewpager.R;

/**
 * Created by Jim Huang on 2015/8/21.
 */
public class PayActivityMain extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private Button btnMoney;
    private Button btnEpay;
    private ViewPager viewPager;
    private ArrayList<Fragment> list;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_main);
        initView();
    }

    private void initView() {
        btnMoney=(Button)findViewById(R.id.pay_money_btn);
        btnEpay=(Button)findViewById(R.id.pay_epay_btn);
        back=(ImageView)findViewById(R.id.back);
        viewPager=(ViewPager)findViewById(R.id.pay_viewpager);

        back.setOnClickListener(new BtnListener());
        btnMoney.setOnClickListener(new BtnListener());
        btnEpay.setOnClickListener(new BtnListener());

        list= new ArrayList<>();
        list.add(new PayFragment01());
        list.add(new PayFragment02());
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        initBtn();
        switch (position){
            case 0:
                btnMoney.setBackgroundColor(Color.parseColor("#ff735c"));
                btnMoney.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 1:
                btnEpay.setBackgroundColor(Color.parseColor("#ff735c"));
                //btnEpay.setBackgroundResource(R.color.color_ff735c);
                btnEpay.setTextColor(Color.parseColor("#ffffff"));
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void initBtn(){
        btnMoney.setBackgroundResource(R.color.color_white);
        btnMoney.setTextColor(Color.parseColor("#000000"));

        btnEpay.setBackgroundResource(R.color.color_white);
        btnEpay.setTextColor(Color.parseColor("#000000"));
    }

    class BtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.pay_money_btn:
                    initBtn();
                    viewPager.setCurrentItem(0);
                    btnMoney.setBackgroundColor(Color.parseColor("#ff735c"));
                    btnMoney.setTextColor(Color.parseColor("#ffffff"));
                    break;
                case R.id.pay_epay_btn:
                    initBtn();
                    viewPager.setCurrentItem(1);
                    btnEpay.setBackgroundColor(Color.parseColor("#ff735c"));
                    //btnEpay.setBackgroundResource(R.color.color_ff735c);
                    btnEpay.setTextColor(Color.parseColor("#ffffff"));
                    break;

            }
        }
    }
}
