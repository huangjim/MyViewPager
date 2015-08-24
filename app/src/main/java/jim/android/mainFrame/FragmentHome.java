package jim.android.mainFrame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import jim.android.utils.PieceItemMsg;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class FragmentHome extends LazyFragment implements View.OnClickListener {

    private View view;
    private ViewPager viewPager;
    private LinearLayout layout;
    private ImageView imageView[];
    private List<String> list;
    private List<Bitmap> bitmapList;
    private int imageId[] = new int[]{
            R.drawable.frag_home_img03, R.drawable.frag_home_img04, R.drawable.frag_home_img05, R.drawable.frag_home_img03
    };

    private boolean isPrepared;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_fragment_home, container, false);
        isPrepared = true;
        return view;
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
        lazyLoad();


    }

    private void readImage() {
        list = new ArrayList<>();
        bitmapList = new ArrayList<>();
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
                Iterator iterator = FragmentMainActivity.basketList.iterator();
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
                    BasketItemMsg b = (BasketItemMsg) iterator.next();
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
        initView();
        readImage();
        imageView = new ImageView[bitmapList.size()];
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

        viewPager.setAdapter(new MyPAdapter(imageView));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyPChangeAdapter(layout));
    }
}
