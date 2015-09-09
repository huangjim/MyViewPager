package jim.android.mainFrame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jim.android.adapter.MyPAdapter;
import jim.android.adapter.MyPChangeAdapter;
import jim.android.Splash.R;
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
    private ScheduledExecutorService scheduledExecutorService;
    private List<Bitmap> bitmapList;
    private List<ImageView> imageViewList;
    private List<String> imageUrl;
    private int currentItem = 0;
    private MyApplication application=MyApplication.getInstance();
    private DisplayImageOptions options;


    private Handler handler = new Handler() {
        public void handleMessage(Message paramMessage) {
            super.handleMessage(paramMessage);

            viewPager.setCurrentItem(currentItem);


        }
    };

    /*  private int imageId[] = new int[]{
              R.drawable.frag_home_img03, R.drawable.frag_home_img04, R.drawable.frag_home_img05, R.drawable.frag_home_img03
      };
  */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_home, container, false);

        if (savedInstanceState==null){
            Log.i("Home log", "onCreateView");
            initView();
            initUI();
        }
        return view;
    }

    private void initView() {
        Log.i("imageViewList", "first");
        imageViewList = application.getImageViewList();
        imageUrl=application.getImageUrl();
        options=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        bitmapList = new ArrayList<>();
        Button pieceWashBtn = (Button) view.findViewById(R.id.frag_home_btn01);
        Button packetWashBtn = (Button) view.findViewById(R.id.frag_home_btn02);
        viewPager = (ViewPager) view.findViewById(R.id.frag_home_viewpager);
        layout = (LinearLayout) view.findViewById(R.id.frag_home_index_container);
        pieceWashBtn.setOnClickListener(this);
        packetWashBtn.setOnClickListener(this);
    }

    private void initUI() {

        if (imageUrl.size()==0)
            return;

        for (int i = 0;i<imageUrl.size() ; ++i) {

            ImageView image02 = new ImageView(getActivity());
            image02.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            image02.setPadding(5, 5, 0, 0);
            image02.setImageResource(R.drawable.circle_selector);
            image02.setSelected(0 == i ? true : false);
            layout.addView(image02);

            if (i>=imageUrl.size()-1){
                viewPager.setAdapter(new MyPAdapter(imageViewList,options,imageUrl,getActivity()));
                viewPager.setOnPageChangeListener(new MyPChangeAdapter(layout));
                viewPager.setFocusable(true);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startPlay();
    }

    private void startPlay() {

        Log.i("Home log", "startPlay");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1L, 4L, TimeUnit.SECONDS);
    }

    private void stopPlay() {

        this.scheduledExecutorService.shutdown();
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
                        PacketWashPopup popup = new PacketWashPopup(view, bool, (BasketItemMsg) localObject);
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

    }

    private class SlideShowTask implements Runnable {
        private SlideShowTask() {

        }

        int i=3;
        public void run() {
            synchronized (viewPager) {
                currentItem = ((i++) % imageViewList.size());
                handler.obtainMessage().sendToTarget();
                return;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        stopPlay();
    }

}
