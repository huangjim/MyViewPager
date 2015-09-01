package jim.android.pieceWash;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import jim.android.Splash.R;
import jim.android.mainFrame.FragmentMainActivity;
import jim.android.utils.BasketItemMsg;
import jim.android.utils.PieceItemMsg;

/**
 * Created by Jim Huang on 2015/8/22.
 */
public class PieceWashPopup extends PopupWindow {

    private Context context;
    private PieceItemMsg result;
    private View view;
    private ImageView popupItemImg;
    private TextView popupItemPrice;
    private TextView popupItemName;
    private TextView popupItemCut;
    private TextView popupItemNum;
    private TextView popupItemAdd;
    private Button popupItemBtn;
    private int strCut=1;
    private boolean bool;
    private BasketItemMsg bean;


    public PieceWashPopup(Context context, PieceItemMsg result, View view,boolean bool,BasketItemMsg basketItemMsg){

        this.context=context;
        this.result=result;
        this.view=view;
        this.bool=bool;
        this.bean =basketItemMsg;
        initView();
    }

    public PieceWashPopup(){

    }

    private void initView() {
        popupItemImg = (ImageView) view.findViewById(R.id.piece_wash_popup_item_img);
        popupItemImg.setImageResource(result.getImageId());
        popupItemName = (TextView) view.findViewById(R.id.piece_wash_popup_item_name);
        popupItemName.setText(result.getClothesName());
        popupItemPrice = (TextView) view.findViewById(R.id.piece_wash_popup_item_price);
        popupItemPrice.setText(result.getPrice() + "");

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
                if (bool){
                    int j=num+bean.getAccount();
                    bean.setAccount(j);
                }
                else {
                    BasketItemMsg basketItemMsg=new BasketItemMsg();
                    basketItemMsg.setImageId(result.getImageId());
                    basketItemMsg.setClothesName(result.getClothesName());
                    basketItemMsg.setPrice(result.getPrice());
                    basketItemMsg.setAccount(num);
                    FragmentMainActivity.basketList.add(basketItemMsg);
                }
                /*setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        dismiss();
                    }
                });*/
                dismiss();
                //backgroundAlpha(1f);
            }
        });
    }
}
