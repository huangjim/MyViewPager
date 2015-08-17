package jim.android.mainFrame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jim.android.adapter.MyPAdapter;
import jim.android.adapter.MyPChangeAdapter;
import jim.android.indexViewpager.R;
import jim.android.pieceWash.PieceWashMain;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class FragmentHome extends Fragment {

    private View view;
    private ViewPager viewPager;
    private LinearLayout layout;
    private ImageView imageView[];
    private List<String> list;
    private List<Bitmap> bitmapList;
    private int imageId[] = new int[]{
            R.drawable.frag_home_img03, R.drawable.frag_home_img04, R.drawable.frag_home_img05, R.drawable.frag_home_img03
    };

    private Button pieceWashBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_fragment_home, container, false);

        return view;
    }

    private void initView() {

        pieceWashBtn = (Button) view.findViewById(R.id.frag_home_btn01);
        viewPager = (ViewPager) view.findViewById(R.id.frag_home_viewpager);
        layout = (LinearLayout) view.findViewById(R.id.frag_home_index_container);
        pieceWashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PieceWashMain.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
            image.setSelected(i == 0 ? true : false);
            layout.addView(image);
        }

        viewPager.setAdapter(new MyPAdapter(imageView));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyPChangeAdapter(layout));

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
}
