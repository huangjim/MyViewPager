package jim.android.mainFrame;

import android.app.Application;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/22.
 */
public class MyApplication extends Application {

    private static List<ImageView> imageViewList;
    private static String imageUrls[];
    private static MyApplication myApplication;


    public List<ImageView> getImageViewList() {
        return imageViewList;
    }

    public String[] getImageUrls() {
        return imageUrls;
    }

    public static MyApplication getInstance(){
       /* if (myApplication==null){
            synchronized (MyApplication.class){
                if (myApplication==null){

                    myApplication=new MyApplication();
                }
            }
        }*/
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initUtil();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(3)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest localStringRequest = new StringRequest("http://123.56.138.192:8002/banners/", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject localJSONObject = new JSONObject(s);
                    if (localJSONObject.getInt("status_code") != 1)
                        return;
                    JSONArray localJSONArray = localJSONObject.getJSONArray("data");
                    imageUrls = new String[localJSONArray.length()];
                    for (int i = 0; i < localJSONArray.length(); i++) {
                        String str = ((JSONObject) localJSONArray.get(i)).getString("image");
                        Log.i("str", str);

                        ImageView imageView=new ImageView(getApplicationContext());
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        ImageLoader.getInstance().displayImage(str,imageView);
                        imageViewList.add(imageView);


                    }

                } catch (JSONException localJSONException) {
                    localJSONException.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            public void onErrorResponse(VolleyError paramVolleyError) {

                paramVolleyError.printStackTrace();
            }
        });
        queue.add(localStringRequest);

    }
    private void initUtil(){
        myApplication=new MyApplication();
        imageViewList=new ArrayList<>();
    }


}
