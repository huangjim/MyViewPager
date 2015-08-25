package jim.android.pieceWash;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.Flushable;

import jim.android.adapter.BasketAdapter;
import jim.android.indexViewpager.R;
import jim.android.mainFrame.FragmentMainActivity;
import jim.android.utils.BasketItemMsg;
import jim.android.utils.PieceItemMsg;

/**
 * Created by Jim Huang on 2015/8/23.
 */
public class PacketWashPopup extends PopupWindow {

    private Context context;
    private View view;
    private Button btnPay;
    private TextView cut;
    private TextView add;
    private TextView number;
    private BasketItemMsg bean;
    private boolean bool;
    private int strCut=1;

    public PacketWashPopup(Context context,View view,boolean bool,BasketItemMsg basketItemMsg){

        this.context=context;
        this.view=view;
        this.bool=bool;
        this.bean=basketItemMsg;
        initView();
    }

    private void initView() {
        btnPay=(Button)view.findViewById(R.id.packet_item_btn);
        cut=(TextView)view.findViewById(R.id.packet_item_cut);
        add=(TextView)view.findViewById(R.id.packet_item_add);
        number=(TextView)view.findViewById(R.id.packet_item_count);
        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = number.getText().toString().trim();
                strCut = Integer.parseInt(str);
                if (strCut > 1) {
                    strCut--;
                    number.setText(strCut + "");
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = number.getText().toString().trim();
                strCut = Integer.parseInt(str);
                strCut++;
                number.setText(strCut + "");
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num=Integer.parseInt(number.getText().toString());
                if (bool){
                    int j=num+bean.getAccount();
                    bean.setAccount(j);
                }
                else {

                    BasketItemMsg basketItemMsg=new BasketItemMsg();
                    basketItemMsg.setAccount(num);
                    basketItemMsg.setPrice(99);
                    basketItemMsg.setImageId(R.drawable.daixis);
                    basketItemMsg.setClothesName("袋洗");
                    FragmentMainActivity.basketList.add(basketItemMsg);

                }
                dismiss();
            }
        });
    }
}
