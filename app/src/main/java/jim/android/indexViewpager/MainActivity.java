package jim.android.indexViewpager;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;

    //放置图片资源
    private ImageView mImageView[];

    //图片资源ID
    private int imageID[]=new int[]{R.drawable.view1,R.drawable.view2,R.drawable.view3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initUI();
    }
    private void initUI(){

        mImageView=new ImageView[imageID.length];

        //加载图片
        for (int i=0;i<imageID.length;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(imageID[i]);
            mImageView[i]=imageView;
           // mImageView[i].setBackgroundResource(imageID[i]);
        }


        viewPager=(ViewPager)findViewById(R.id.my_viewPager);
        MyAdapter adapter=new MyAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new MyListener());
        viewPager.setCurrentItem(0);

    }

    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {

            return imageID.length;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            if (position<imageID.length) {

                container.addView(mImageView[position], 0);

                return mImageView[position];
            }
            return null;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            if (position<imageID.length) {

                container.removeView(mImageView[position]);
            }
        }
        @Override
        public boolean isViewFromObject(View view, Object o) {

            return view==o;
        }



    }

    class MyListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    }

}
