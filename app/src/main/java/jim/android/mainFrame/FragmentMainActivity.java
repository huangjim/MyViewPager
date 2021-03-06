package jim.android.mainFrame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.ViewUtils;

import java.util.ArrayList;

import jim.android.adapter.MyPagerAdapter;
import jim.android.Splash.R;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class FragmentMainActivity extends FragmentActivity {

    private LinearLayout buttonHome,buttonBasket,buttonMy,buttonMore;
    private ImageView imageHome,imageBasket,imageMy,imageMore;
    private TextView textHome,textBasket,textMy,textMore;
    private ViewPager viewPager;
    private ArrayList<Fragment> list;
    public static ArrayList<BasketItemMsg> basketList;
    private static RequestQueue queue;
    private Fragment fragmentHome,fragmentBasket,fragmentMy,fragmentMore;

    public ImageView has_basket;

    private int frameID;

    static {
        basketList=new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity log", "onCreate");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cotent_main);
        queue= Volley.newRequestQueue(this);
        if (savedInstanceState==null){
            initView();
        }
        setDefaultFragment();
    }

    private void initView() {

        //初始化LinearLayout作为按钮
        buttonHome = (LinearLayout) findViewById(R.id.mybutton_home);
        buttonBasket= (LinearLayout) findViewById(R.id.mybutton_basket);
        buttonMy = (LinearLayout) findViewById(R.id.mybutton_my);
        buttonMore = (LinearLayout) findViewById(R.id.mybutton_more);

        //初始化按钮中的图片
        imageHome=(ImageView)findViewById(R.id.mybutton_home_img);
        imageBasket=(ImageView)findViewById(R.id.mybutton_basket_img);
        imageMy=(ImageView)findViewById(R.id.mybutton_my_img);
        imageMore=(ImageView)findViewById(R.id.mybutton_more_img);

        //按钮中的文字
        textHome=(TextView)findViewById(R.id.mybutton_home_txt);
        textBasket=(TextView)findViewById(R.id.mybutton_basket_txt);
        textMy=(TextView)findViewById(R.id.mybutton_my_txt);
        textMore=(TextView)findViewById(R.id.mybutton_more_txt);

        //设置事件
        buttonHome.setOnClickListener(new MyBtnOnclick());
        buttonBasket.setOnClickListener(new MyBtnOnclick());
        buttonMy.setOnClickListener(new MyBtnOnclick());
        buttonMore.setOnClickListener(new MyBtnOnclick());

        frameID=R.id.layout_frame_view;

        //basket=new Basket();
        has_basket=(ImageView) LayoutInflater.from(FragmentMainActivity.this).inflate(
                R.layout.activity_fragment_basket,null).findViewById(R.id.iv_empty);

        list= new ArrayList<>();
        fragmentHome=new Home();
        fragmentBasket=new Basket();
        fragmentMy=new My();
        fragmentMore=new FragmentMore();
        list.add(fragmentHome);
        list.add(fragmentBasket);
        list.add(fragmentMy);
        list.add(fragmentMore);

        /*viewPager=(ViewPager)findViewById(R.id.viewpager_main);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), list));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);*/

    }
    private void setDefaultFragment(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(frameID,fragmentHome);
        ft.commit();
    }

   /* @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        initBottomBtn();
        switch (position){
            case 0:
                imageHome.setImageResource(R.drawable.main_home_click);
                textHome.setTextColor(Color.parseColor("#28CCFC"));
                break;
            case 1:
                imageBasket.setImageResource(R.drawable.main_basket_click);
                textBasket.setTextColor(Color.parseColor("#28CCFC"));
                break;
            case 2:
                imageMy.setImageResource(R.drawable.main_my_click);
                textMy.setTextColor(Color.parseColor("#28CCFC"));
                break;
            case 3:
                imageMore.setImageResource(R.drawable.main_more_click);
                textMore.setTextColor(Color.parseColor("#28CCFC"));

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }*/

    private class MyBtnOnclick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            initBottomBtn();
            switch (v.getId()){
                case R.id.mybutton_home:
                    ft.replace(frameID, fragmentHome);
                    //viewPager.setCurrentItem(0);
                    imageHome.setImageResource(R.drawable.main_home_click);
                    textHome.setTextColor(Color.parseColor("#28CCFC"));
                    break;
                case R.id.mybutton_basket:
                    ft.replace(frameID, fragmentBasket);
                    //viewPager.setCurrentItem(1);
                    imageBasket.setImageResource(R.drawable.main_basket_click);
                    textBasket.setTextColor(Color.parseColor("#28CCFC"));
                    break;
                case R.id.mybutton_my:
                    ft.replace(frameID, fragmentMy);
                    //viewPager.setCurrentItem(2);
                    imageMy.setImageResource(R.drawable.main_my_click);
                    textMy.setTextColor(Color.parseColor("#28CCFC"));
                    break;
                case R.id.mybutton_more:
                    ft.replace(frameID, fragmentMore);
                    //viewPager.setCurrentItem(3);
                    imageMore.setImageResource(R.drawable.main_more_click);
                    textMore.setTextColor(Color.parseColor("#28CCFC"));
                    break;
            }
            ft.commit();
        }
    }

    private void initBottomBtn(){
        imageHome.setImageResource(R.drawable.main_home_selector);
        if (basketList.size()>0){
            imageBasket.setImageResource(R.drawable.icon_basket_nor_have);
        }else {
            imageBasket.setImageResource(R.drawable.main_basket_selector);
        }
        imageMy.setImageResource(R.drawable.main_my_selector);
        imageMore.setImageResource(R.drawable.main_more_selector);

        textHome.setTextColor(getResources().getColor(R.color.btn_textcolor));
        textBasket.setTextColor(getResources().getColor(R.color.btn_textcolor));
        textMy.setTextColor(getResources().getColor(R.color.btn_textcolor));
        textMore.setTextColor(getResources().getColor(R.color.btn_textcolor));

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("MainActivity log", "onResume");
        if (basketList.size()!=0){

            has_basket.setVisibility(View.INVISIBLE);
        }else {
            has_basket.setVisibility(View.VISIBLE);
        }

    }

    public static <T> Request<T> startRequest(Request paramRequest,Object paramObject){
        paramRequest.setTag(paramObject);
        Request localRequest=queue.add(paramRequest);
        queue.start();
        return localRequest;
    }

}
