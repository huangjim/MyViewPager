package jim.android.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class MyPAdapter extends PagerAdapter {
    private ImageView imageView[]=null;
    public MyPAdapter(ImageView imageView[]){
        this.imageView=imageView;

    }
    @Override
    public int getCount() {
        return imageView.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(imageView[position]);
        return imageView[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageView[position]);
    }
}
