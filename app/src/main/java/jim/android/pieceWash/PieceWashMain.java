package jim.android.pieceWash;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

import jim.android.adapter.MyPagerAdapter;
import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class PieceWashMain extends FragmentActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private Button btnSpring,btnSummer,btnAutumn,btnWinter;
    private ArrayList<Fragment> list;
    private Button btnBack;
    private static RequestQueue queue;
    public static DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_wash);
        initView();
    }

    private void initView() {

        queue= Volley.newRequestQueue(getApplicationContext());
        btnBack=(Button)findViewById(R.id.piece_wash_back);
        viewPager=(ViewPager)findViewById(R.id.piece_wash_viewpager);
        btnSpring=(Button)findViewById(R.id.btn_sprint);
        btnSummer=(Button)findViewById(R.id.btn_summer);
        btnAutumn=(Button)findViewById(R.id.btn_autumn);
        btnWinter=(Button)findViewById(R.id.btn_winter);

        btnBack.setOnClickListener(new MyClickListener());
        btnSpring.setOnClickListener(new MyClickListener());
        btnSummer.setOnClickListener(new MyClickListener());
        btnAutumn.setOnClickListener(new MyClickListener());
        btnWinter.setOnClickListener(new MyClickListener());

        FragmentSpring fragmentSpring = new FragmentSpring();
        FragmentSummer fragmentSummer = new FragmentSummer();
        FragmentAutumn fragmentAutumn = new FragmentAutumn();
        FragmentWinter fragmentWinter = new FragmentWinter();

        options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        list=new ArrayList<Fragment>();
        list.add(fragmentSpring);
        list.add(fragmentSummer);
        list.add(fragmentAutumn);
        list.add(fragmentWinter);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(0);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        initBtn();

        //showToast(position+"");

        switch (position){
            case 0:
                btnSpring.setBackgroundResource(R.drawable.wash_btn_press);
                btnSpring.setTextColor(Color.parseColor("#28ccfc"));
                break;
            case 1:
                btnSummer.setBackgroundResource(R.drawable.wash_btn_press);
                btnSummer.setTextColor(Color.parseColor("#28ccfc"));
                break;
            case 2:
                btnAutumn.setBackgroundResource(R.drawable.wash_btn_press);
                btnAutumn.setTextColor(Color.parseColor("#28ccfc"));
                break;
            case 3:
                btnWinter.setBackgroundResource(R.drawable.wash_btn_press);
                btnWinter.setTextColor(Color.parseColor("#28ccfc"));
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_sprint:
                    initBtn();
                    viewPager.setCurrentItem(0);
                    btnSpring.setBackgroundResource(R.drawable.wash_btn_press);
                    btnSpring.setTextColor(Color.parseColor("#28ccfc"));
                    break;
                case R.id.btn_summer:
                    initBtn();
                    viewPager.setCurrentItem(1);
                    btnSummer.setBackgroundResource(R.drawable.wash_btn_press);
                    btnSummer.setTextColor(Color.parseColor("#28ccfc"));
                    break;
                case R.id.btn_autumn:
                    initBtn();
                    viewPager.setCurrentItem(2);
                    btnAutumn.setBackgroundResource(R.drawable.wash_btn_press);
                    btnAutumn.setTextColor(Color.parseColor("#28ccfc"));
                    break;
                case R.id.btn_winter:
                    initBtn();
                    viewPager.setCurrentItem(3);
                    btnWinter.setBackgroundResource(R.drawable.wash_btn_press);
                    btnWinter.setTextColor(Color.parseColor("#28ccfc"));
                    break;
                case R.id.piece_wash_back:
                    /*Intent intent=new Intent(PieceWashMain.this, FragmentMainActivity.class);
                    startActivity(intent);*/
                    finish();
            }

        }
    }

    private void initBtn(){
        btnSpring.setBackgroundResource(R.drawable.wash_btn_selector);
        btnSummer.setBackgroundResource(R.drawable.wash_btn_selector);
        btnAutumn.setBackgroundResource(R.drawable.wash_btn_selector);
        btnWinter.setBackgroundResource(R.drawable.wash_btn_selector);

        btnSpring.setTextColor(getResources().getColor(R.color.piece_wash_btn_color));
        btnSummer.setTextColor(getResources().getColor(R.color.piece_wash_btn_color));
        btnAutumn.setTextColor(getResources().getColor(R.color.piece_wash_btn_color));
        btnWinter.setTextColor(getResources().getColor(R.color.piece_wash_btn_color));
    }

    private void showToast(String msg){

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public static <T> Request<T> startRequest(Request<T> paramRequest, Object paramObject)
    {
        paramRequest.setTag(paramObject);
        Request localRequest = queue.add(paramRequest);
        queue.start();
        return localRequest;
    }

    public static void camcelRequest(Object paramObject)
    {
        queue.cancelAll(paramObject);
    }
}
