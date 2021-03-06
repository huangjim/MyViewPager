package jim.android.mainFrame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jim.android.adapter.BasketAdapter;
import jim.android.Splash.R;
import jim.android.pay.PayActivityMain;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class Basket extends LazyFragment implements View.OnClickListener{

    /*private int imageId[]=new int[]{R.drawable.basket_list_item_img01,R.drawable.basket_list_item_img02,R.drawable.basket_list_item_img03};
    private String clothesName[]=new String[]{"衬衫","短风衣","T恤"};
    private int account[]=new int[]{2,2,1};
    private int price[]=new int[]{8,12,8};*/
    private ListView myListView;
    private View view;
    private List<BasketItemMsg> listMsg;
    private ImageView ivEmpty;
    private BasketAdapter adapter;
    private TextView displayPrice;
    private boolean isPrepared;
    private TextView pay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Basket log", "onCreateView");
        view=inflater.inflate(R.layout.activity_fragment_basket,container,false);

        initView();

        isPrepared=true;

        adapter=new BasketAdapter(FragmentMainActivity.basketList, getActivity(),ivEmpty,displayPrice);
        myListView.setAdapter(adapter);
        if (FragmentMainActivity.basketList.size()==0){
            ivEmpty.setVisibility(View.VISIBLE);
            pay.setClickable(false);
        } else {
            ivEmpty.setVisibility(View.GONE);
            pay.setClickable(true);

        }
        return view;
    }

    private void initView() {
        myListView = (ListView) view.findViewById(R.id.basket_list);
        listMsg = new ArrayList<>();
        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        pay = (TextView) view.findViewById(R.id.btnpay);
        TextView deleteAll = (TextView) view.findViewById(R.id.deleteAll);
        displayPrice=(TextView)view.findViewById(R.id.price_pay);

        pay.setOnClickListener(this);
        deleteAll.setOnClickListener(this);

       /* for (int i=0;i<imageId.length;i++){
            BasketItemMsg itemMsg=new BasketItemMsg();
            itemMsg.setImageId(imageId[i]);
            itemMsg.setClothesName(clothesName[i]);
            itemMsg.setAccount(account[i]);
            itemMsg.setPrice(price[i]);
            listMsg.add(itemMsg);
        }*/

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i("Basket log", "onActivityCreated");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Basket log", "onResume");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Basket log", "onPause");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnpay:
                Intent intent=new Intent(getActivity(), PayActivityMain.class);
                startActivity(intent);
                break;
            case R.id.deleteAll:
                if (FragmentMainActivity.basketList.size()!=0){
                    FragmentMainActivity.basketList.clear();
                    adapter.notifyDataSetChanged();
                    ivEmpty.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared||!isVisible)
            return;
        Log.i("Jim log", "Basket can be excutee?");

        adapter=new BasketAdapter(FragmentMainActivity.basketList, getActivity(),ivEmpty,displayPrice);
        myListView.setAdapter(adapter);
        if (FragmentMainActivity.basketList.size()==0){
            ivEmpty.setVisibility(View.VISIBLE);
            pay.setClickable(false);
        } else {
            ivEmpty.setVisibility(View.GONE);
            pay.setClickable(true);

        }

    }
}
