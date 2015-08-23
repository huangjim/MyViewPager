package jim.android.mainFrame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import jim.android.indexViewpager.R;
import jim.android.pay.PayActivityMain;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/3.
 */
public class FragmentBasket extends Fragment implements View.OnClickListener{

    /*private int imageId[]=new int[]{R.drawable.basket_list_item_img01,R.drawable.basket_list_item_img02,R.drawable.basket_list_item_img03};
    private String clothesName[]=new String[]{"衬衫","短风衣","T恤"};
    private int account[]=new int[]{2,2,1};
    private int price[]=new int[]{8,12,8};*/
    private ListView myListView;
    private View view;
    private List<BasketItemMsg> listMsg;
    private ImageView ivEmpty;
    private BasketAdapter adapter;
    private TextView deleteAll;
    private TextView displayPrice;
    private TextView pay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("FragmentBasket log", "onCreateView");
        view=inflater.inflate(R.layout.activity_fragment_basket,container,false);
        initView();
        return view;
    }

    private void initView() {
        myListView = (ListView) view.findViewById(R.id.basket_list);
        listMsg = new ArrayList<BasketItemMsg>();
        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        pay=(TextView)view.findViewById(R.id.btnpay);
        deleteAll=(TextView)view.findViewById(R.id.deleteAll);
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
        Log.i("FragmentBasket log", "onActivityCreated");


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("FragmentBasket log", "onResume");
        adapter=new BasketAdapter(FragmentMainActivity.basketList, getActivity(),ivEmpty,displayPrice);
        myListView.setAdapter(adapter);
        if (FragmentMainActivity.basketList.size()==0){
            ivEmpty.setVisibility(View.VISIBLE);
        } else {
            ivEmpty.setVisibility(View.INVISIBLE);
        }

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
                    ivEmpty.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FragmentBasket log", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("FragmentBasket log", "onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("FragmentBasket log", "onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FragmentBasket log", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("FragmentBasket log", "onDetach");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("FragmentBasket log", "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("FragmentBasket log", "onCreate");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("FragmentBasket log", "onDestroyView");
    }
}
