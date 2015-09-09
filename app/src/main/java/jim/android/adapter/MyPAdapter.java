package jim.android.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class MyPAdapter extends PagerAdapter {
    private List<ImageView> list;

    private List<String> imageUrl;

    private DisplayImageOptions options;
    private Context context;

    public MyPAdapter(List<ImageView> list,DisplayImageOptions options,List<String> imageUrl,Context context){

        this.list=list;
        this.options=options;
        this.imageUrl=imageUrl;
        this.context=context;
    }
    @Override
    public int getCount() {

        return imageUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


       /* if (list.get(position).getParent()==null){

            container.addView(list.get(position),0);

        }else {
            ((ViewGroup)list.get(position).getParent()).removeView(list.get(position));
            container.addView(list.get(position));
        }*/

        ImageView imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(imageUrl.get(position), imageView, options);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        //container.removeView(list.get(position));
        container.removeView((View)object);
    }

}
