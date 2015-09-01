package jim.android.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class MyPAdapter extends PagerAdapter {
    private ImageView imageView[]=null;
    private List<ImageView> list;
    public MyPAdapter(List<ImageView> list){

        this.list=list;
    }
    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        if (list.get(position).getParent()==null){

            container.addView(list.get(position),0);

        }else {
            ((ViewGroup)list.get(position).getParent()).removeView(list.get(position));
            container.addView(list.get(position));
        }


        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //container.removeView(imageView[position]);

        container.removeView(list.get(position));
    }

}
