package jim.android.mainFrame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jim.android.adapter.MyBaseAdapter;
import jim.android.indexViewpager.R;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class FragmentBasket extends Fragment {

    private int imageId[]=new int[]{R.drawable.basket_list_item_img01,R.drawable.basket_list_item_img02,R.drawable.basket_list_item_img03};

    private String clothesName[]=new String[]{"衬衫","短风衣","T恤"};

    private int account[]=new int[]{2,2,1};

    private int price[]=new int[]{8,12,8};

    private ListView myListView;

    private View view;

    private List<BasketItemMsg> listMsg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_fragment_basket,container,false);


        return view;
    }

    private void initView() {
        myListView=(ListView)view.findViewById(R.id.basket_list);

        listMsg=new ArrayList<BasketItemMsg>();

        for (int i=0;i<imageId.length;i++){
            BasketItemMsg itemMsg=new BasketItemMsg();
            itemMsg.setImageId(imageId[i]);
            itemMsg.setClothesName(clothesName[i]);
            itemMsg.setAccount(account[i]);
            itemMsg.setPrice(price[i]);
            listMsg.add(itemMsg);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        myListView.setAdapter(new MyBaseAdapter(listMsg, getActivity()));
    }
}
