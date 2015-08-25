package jim.android.mainFrame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import jim.android.indexViewpager.R;

/**
 * Created by Jim Huang on 2015/8/4.
 */
public class FragmentMy extends LazyFragment {

    private View view;
    private int imageId[]=new int[]{
            R.drawable.my_img01,R.drawable.my_img02,
            R.drawable.my_img03,
            R.drawable.my_img04,R.drawable.my_img05
    };
    private String strInfo[]=new String[]{
            "我的订单","洗衣币","我的地址","分享推荐码","验证推荐码"
    };

    private GridView gridView;

    private List<Map<String,Object>> list;
    private SimpleAdapter adapter=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_fragment_my,container,false);

        return view;

    }
    private void initView() {
        gridView=(GridView)view.findViewById(R.id.frag_my_grid);

        list= new ArrayList<>();

        for (int i=0;i<imageId.length;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("image",imageId[i]);
            map.put("info",strInfo[i]);
            list.add(map);
        }
        adapter=new SimpleAdapter(getActivity(), list,R.layout.activity_frag_my_griditem,new String[]{"image","info"},
                new int[]{R.id.frag_my_item_img,R.id.frag_my_item_text});

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"推荐码1be2",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getActivity(),ShareCode.class);
                        startActivity(intent);
                        break;
                    case 4:
                        Intent intent1=new Intent(getActivity(),ShareCodeCheck.class);
                        startActivity(intent1);
                        break;
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {

    }
}
