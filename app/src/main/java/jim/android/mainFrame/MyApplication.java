package jim.android.mainFrame;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/22.
 */
public class MyApplication extends Application {

    private static List<ImageView> imageViewList;
    private static MyApplication myApplication;
    private static SharedPreferences preferences;
    public DbUtils dbUtils;
    public HttpUtils httpUtils;
    private static List<String> imageUrl;
    private File cacheDir;
    public List<ImageView> getImageViewList() {

        return imageViewList;
    }

    public SharedPreferences getPreferences() {

        return preferences;
    }

    public List<String> getImageUrl(){

        return imageUrl;
    }

    public static MyApplication getInstance(){
        /*if (myApplication==null){
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
                .diskCacheSize(30 * 1024 * 1024)
                .diskCache(new UnlimitedDiskCache(cacheDir))
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
                    for (int i = 0; i < localJSONArray.length(); i++) {
                        String str = ((JSONObject) localJSONArray.get(i)).getString("image");
                        Log.i("str", str);
                        imageUrl.add(str);
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
        cacheDir= StorageUtils.getOwnCacheDirectory(getApplicationContext(),"imageLoader/Cache");
        myApplication=new MyApplication();
        imageViewList=new ArrayList<>();
        imageUrl=new ArrayList<>();
        preferences=getSharedPreferences("userinfo",MODE_PRIVATE);
        dbUtils=DbUtils.create(this);
        httpUtils=new HttpUtils();

    }


}
