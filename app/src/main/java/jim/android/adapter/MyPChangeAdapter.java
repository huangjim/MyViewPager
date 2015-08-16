package jim.android.adapter;

import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class MyPChangeAdapter implements ViewPager.OnPageChangeListener {

    private LinearLayout layout;

    public MyPChangeAdapter(LinearLayout layout){

        this.layout=layout;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int count=layout.getChildCount();
        for (int i = 0; i <count ; i++) {
            layout.getChildAt(i).setSelected(i==position?true:false);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
