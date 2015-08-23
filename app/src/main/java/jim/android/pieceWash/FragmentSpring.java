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
import jim.android.indexViewpager.R;
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
    private PopupWindow popupWindow;
    private ImageView popupItemImg;
    private TextView popupItemPrice;
    private TextView popupItemName;
    private TextView popupItemCut;
    private TextView popupItemNum;
    private TextView popupItemAdd;
    private Button popupItemBtn;
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
                        pieceWashPopup.setContentView(view);
                        pieceWashPopup.setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        pieceWashPopup.setFocusable(true);
                        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#58000000"));
                        pieceWashPopup.setBackgroundDrawable(drawable);
                        pieceWashPopup.setOutsideTouchable(true);
                        pieceWashPopup.setAnimationStyle(R.style.PopupAnimation);
                        pieceWashPopup.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        break;
                    }
                    BasketItemMsg b = (BasketItemMsg)iterator.next();
                    if (!b.getClothesName().equals(pieceItemMsg.getClothesName()))
                        continue;
                    bool = true;
                    localObject = b;
                }
                /*popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setFocusable(true);
                ColorDrawable drawable = new ColorDrawable(Color.parseColor("#58000000"));
                popupWindow.setBackgroundDrawable(drawable);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.PopupAnimation);
                popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);*/


               /* popupItemImg = (ImageView) view.findViewById(R.id.piece_wash_popup_item_img);
                popupItemImg.setImageResource(list.get(position).getImageId());
                popupItemName = (TextView) view.findViewById(R.id.piece_wash_popup_item_name);
                popupItemName.setText(list.get(position).getClothesName());
                popupItemPrice = (TextView) view.findViewById(R.id.piece_wash_popup_item_price);
                popupItemPrice.setText(list.get(position).getPrice() + "");

                popupItemCut = (TextView) view.findViewById(R.id.frag_popup_item_cut);
                popupItemNum = (TextView) view.findViewById(R.id.frag_popup_item_count);
                popupItemAdd = (TextView) view.findViewById(R.id.frag_popup_item_add);
                popupItemBtn = (Button) view.findViewById(R.id.piece_wash_popup_item_btn);

                popupItemCut.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String str = popupItemNum.getText().toString().trim();
                        strCut = Integer.parseInt(str);
                        if (strCut > 1) {
                            strCut--;
                            popupItemNum.setText(strCut + "");
                        }

                    }
                });

                popupItemAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = popupItemNum.getText().toString().trim();
                        strCut = Integer.parseInt(str);
                        strCut++;
                        popupItemNum.setText(strCut + "");
                    }
                });

                popupItemBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num=Integer.parseInt(popupItemNum.getText().toString());
                        if (num>=1){
                            BasketItemMsg basketItemMsg=new BasketItemMsg();
                            basketItemMsg.setImageId(list.get(position).getImageId());
                            basketItemMsg.setClothesName(list.get(position).getClothesName());
                            basketItemMsg.setPrice(list.get(position).getPrice());
                            basketItemMsg.setAccount(num);
                            FragmentMainActivity.basketList.add(basketItemMsg);
                        }

                        popupWindow.dismiss();
                        //backgroundAlpha(1f);
                    }
                });*/
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
