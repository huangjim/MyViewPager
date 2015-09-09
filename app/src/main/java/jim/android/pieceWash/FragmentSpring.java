package jim.android.pieceWash;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jim.android.adapter.BasketAdapter;
import jim.android.adapter.MyPieceGridAdapter;
import jim.android.Splash.R;
import jim.android.mainFrame.FragmentMainActivity;
import jim.android.utils.BasketItemMsg;
import jim.android.utils.PieceItemMsg;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class FragmentSpring extends Fragment {

    private View view;
    private int imageId[];
    private int price[];
    private String clothesName[];
    private List<PieceItemMsg> list;
    private PieceItemMsg pieceItemMsg;
    private GridView gridView;

    private Handler handler;
    private int strCut=1;

    private BasketAdapter adapter;
    private PieceWashPopup pieceWashPopup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_frag_spring, container, false);

        return view;

    }

    private void initView() {


        adapter=new BasketAdapter();
        gridView = (GridView) view.findViewById(R.id.grid_spring);
        imageId = new int[]{R.drawable.basket_list_item_img01, R.drawable.basket_list_item_img02,
                R.drawable.basket_list_item_img03, R.drawable.basket_list_item_img10,
                R.drawable.basket_list_item_img11};

        price = new int[]{8, 8, 12, 18, 24};
        clothesName = new String[]{getActivity().getString(R.string.blouse), getActivity().getString(R.string.T_shirt), getActivity().getString(R.string.short_wind_jicket), getActivity().getString(R.string.long_wind_jicket), getActivity().getString(R.string.suit)};

        list = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++) {
            pieceItemMsg = new PieceItemMsg();
            pieceItemMsg.setImageId(imageId[i]);
            pieceItemMsg.setPrice(price[i]);
            pieceItemMsg.setClothesName(clothesName[i]);
            list.add(pieceItemMsg);
        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_piece_wash_popupitem, null);
                pieceItemMsg= (PieceItemMsg) gridView.getItemAtPosition(position);
                Iterator iterator=FragmentMainActivity.basketList.iterator();
                Object localObject = null;
                boolean bool=false;
                while (true)
                {
                    if (!iterator.hasNext())
                    {
                        pieceWashPopup=new PieceWashPopup(getActivity(),pieceItemMsg,view,bool,(BasketItemMsg)localObject);
                        pieceWashPopup.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                    }
                    BasketItemMsg b = (BasketItemMsg)iterator.next();
                    if (!b.getClothesName().equals(pieceItemMsg.getClothesName()))
                        continue;
                    bool = true;
                    localObject = b;
                }

            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        gridView.setAdapter(new MyPieceGridAdapter(list, getActivity()));

    }

}
