package jim.android.mainFrame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jim.android.adapter.MyPAdapter;
import jim.android.adapter.MyPChangeAdapter;
import jim.android.indexViewpager.R;
import jim.android.pieceWash.PacketWashPopup;
import jim.android.pieceWash.PieceWashMain;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class Home extends LazyFragment implements View.OnClickListener {

    private View view;
    private ViewPager viewPager;
    private LinearLayout layout;
    private ImageView imageView[];
    private List<Bitmap> bitmapList = new ArrayList<>();
    private List<ImageView> imageViewList=new ArrayList<>();
    public static JSONArray Arraybanner = new JSONArray();
    private final String TAG = Home.class.getName();
    private String[] imageUrls;
    private String[] imageUrls01=new String[3];
    private RequestQueue queue;
    private List<String> stringList=new ArrayList<>();

    private DisplayImageOptions options;

    /*  private int imageId[] = new int[]{
              R.drawable.frag_home_img03, R.drawable.frag_home_img04, R.drawable.frag_home_img05, R.drawable.frag_home_img03
      };
  */
    private boolean isPrepared;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_fragment_home, container, false);

        initView();

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(getActivity());

        ImageLoader.getInstance().init(configuration);

        /*options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();*/
        /*ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getActivity())
                .threadPriority(3)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(configuration);*/

        Banner();
        initViewPager();


        isPrepared = true;

        imageView = new ImageView[bitmapList.size()];
        Log.i("bitmapList.size()=",bitmapList.size()+"");


        if (layout.getChildCount()<bitmapList.size()){
            for (int i = 0; i < bitmapList.size(); i++) {
                ImageView image = new ImageView(getActivity());
                image.setImageBitmap(bitmapList.get(i));
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView[i] = image;
            }

            for (int i = 0; i < bitmapList.size(); i++) {
                ImageView image = new ImageView(getActivity());
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                image.setPadding(8, 8, 8, 8);
                image.setImageResource(R.drawable.circle_selector);
                image.setSelected(0 == i ? true : false);
                layout.addView(image);
            }
        }


        viewPager.setAdapter(new MyPAdapter(imageView));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyPChangeAdapter(layout));



        return view;
    }

    private void initViewPager(){

        //Log.i("image url",imageUrls.length+"");

            try {

                for (int i=0;i<imageUrls01.length;i++) {
                    Log.i("imageUrls01",imageUrls01.length+"");
                    ImageLoader.getInstance().loadImage(imageUrls01[i], new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String s, View view) {
                        }

                        @Override
                        public void onLoadingFailed(String s, View view, FailReason failReason) {
                        }

                        @Override
                        public void onLoadingComplete(String s, View view, Bitmap bitmap) {


                            bitmapList.add(bitmap);
                        }

                        @Override
                        public void onLoadingCancelled(String s, View view) {

                        }


                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }




    }

    private void Banner() {
       /* FragmentMainActivity.startRequest(new StringRequest("http://123.56.138.192:8002/banners/",
                new Home01(), new Home02()), this.TAG);*/

        if (stringList.size()>=3)
            return;
        queue=Volley.newRequestQueue(getActivity());

        StringRequest localStringRequest = new StringRequest("http://123.56.138.192:8002/banners/", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try
                {
                    JSONObject localJSONObject = new JSONObject(s);
                    if (localJSONObject.getInt("status_code") != 1)
                        return;
                    JSONArray localJSONArray = localJSONObject.getJSONArray("data");
                    imageUrls = new String[localJSONArray.length()];
                    for (int i=0;i<localJSONArray.length();i++){
                        String str  = ((JSONObject)localJSONArray.get(i)).getString("image");
                        Log.i("str",str);
                        imageUrls[i] = str;
                        imageUrls01[i]=str;
                        stringList.add(str);

                    }

                }
                catch (JSONException localJSONException)
                {
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

    private void initView() {



        Button pieceWashBtn = (Button) view.findViewById(R.id.frag_home_btn01);
        Button packetWashBtn = (Button) view.findViewById(R.id.frag_home_btn02);
        viewPager = (ViewPager) view.findViewById(R.id.frag_home_viewpager);
        layout = (LinearLayout) view.findViewById(R.id.frag_home_index_container);
        pieceWashBtn.setOnClickListener(this);
        packetWashBtn.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        //lazyLoad();
      /*  if (bitmapList.size() == 0 || bitmapList.isEmpty()) {
            //readImage();
            imageView = new ImageView[bitmapList.size()];
            Log.i("bitmapList.size()=",bitmapList.size()+"");
        }*/



    }

    private void readImage() {
        List<String> list = new ArrayList<>();
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory().getCanonicalPath() + "/images/";
                File filePath = new File(path);

                Log.i("Jim Log", "filePath ---> " + filePath);
                File file[] = filePath.listFiles();
                for (int i = 0; i < file.length; i++) {
                    String name = file[i].getName();
                    list.add(path + name);
                    Log.i("Jim Log", "file path--->" + name);
                }

                for (String name :
                        list) {
                    bitmapList.add(BitmapFactory.decodeFile(name));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_home_btn01:
                Intent intent = new Intent(getActivity(), PieceWashMain.class);
                startActivity(intent);
                break;
            case R.id.frag_home_btn02:
                Iterator<BasketItemMsg> iterator = FragmentMainActivity.basketList.iterator();
                Object localObject = null;
                boolean bool = false;
                while (true) {
                    if (!iterator.hasNext()) {
                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.packetwashpopup, null);
                        PacketWashPopup popup = new PacketWashPopup(getActivity(), view, bool, (BasketItemMsg) localObject);
                        popup.setContentView(view);
                        popup.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        popup.setFocusable(true);
                        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#58000000"));
                        popup.setBackgroundDrawable(drawable);
                        popup.setOutsideTouchable(true);
                        popup.setAnimationStyle(R.style.PopupAnimation);
                        popup.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                    }
                    BasketItemMsg b = iterator.next();
                    if (!b.getClothesName().equals("袋洗"))
                        continue;
                    bool = true;
                    localObject = b;
                }
                break;

        }

    }

    @Override
    protected void lazyLoad() {

        if (!isPrepared || !isVisible)
            return;
        Log.i("Jim log", "Home can be excutee?");

    }
}
