package jim.android.pieceWash;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import jim.android.Splash.R;
import jim.android.mainFrame.FragmentMainActivity;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/23.
 */
public class PacketWashPopup extends PopupWindow {

    private View view;
    private Button btnPay;
    private TextView cut;
    private TextView add;
    private TextView number;
    private BasketItemMsg bean;
    private boolean bool;
    private int strCut=1;

    public PacketWashPopup(View view,boolean bool,BasketItemMsg basketItemMsg){

        this.view=view;
        this.bool=bool;
        this.bean=basketItemMsg;
        setContentView(view);
        setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#58000000"));
        setBackgroundDrawable(drawable);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.PopupAnimation);
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
