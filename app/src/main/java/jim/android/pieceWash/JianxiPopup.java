package jim.android.pieceWash;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import jim.android.Splash.R;
import jim.android.bean.BasketBean;
import jim.android.bean.Results;
import jim.android.utils.BasketItemMsg;
import jim.android.utils.PieceItemMsg;

/**
 * Created by Jim Huang on 2015/9/8.
 */
public class JianxiPopup extends PopupWindow {
    private Results result;
    private TextView popupItemPrice;
    private TextView popupItemName;
    private TextView popupItemCut;
    private TextView popupItemNum;
    private TextView popupItemAdd;
    private Button popupItemBtn;
    private int strCut=1;
    private boolean bool;
    private BasketBean bean;
    private Drawable localDrawable;
    private ImageView imageView;
    private Context context;


    public JianxiPopup(Context context,Results results,Drawable localDrawable){
        this.context=context;
        this.result=results;
        this.localDrawable=localDrawable;
        View popupView = LayoutInflater.from(context).inflate(R.layout.activity_piece_wash_popupitem, null);
        imageView=(ImageView)popupView.findViewById(R.id.piece_wash_popup_item_img);
        popupItemName=(TextView)popupView.findViewById(R.id.piece_wash_popup_item_name);
        popupItemPrice=(TextView)popupView.findViewById(R.id.piece_wash_popup_item_price);
        imageView.setImageDrawable(localDrawable);
        popupItemName.setText(result.getName());
        popupItemPrice.setText(result.getPrice()+"");
        setContentView(popupView);
        setWindowLayoutMode(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        ColorDrawable drawable = new ColorDrawable(Color.parseColor("#58000000"));
        setBackgroundDrawable(drawable);
        setOutsideTouchable(true);
        setAnimationStyle(R.style.PopupAnimation);
    }
}
