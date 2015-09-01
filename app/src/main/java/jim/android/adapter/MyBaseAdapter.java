package jim.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jim.android.Splash.R;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/4.
 */
public class MyBaseAdapter extends BaseAdapter {

    private List<BasketItemMsg> list;
    private Context context;
    private final int VIEW_TYPE_COUNT = 2;
    private final int ITEM = 0;
    private final int ITEM1 = 1;
    private ImageView imageView;
    private TextView clothesName;
    private TextView accoumt;
    private TextView price;

    public MyBaseAdapter(List<BasketItemMsg> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        int count = list.size();
        if (position < count) {
            return ITEM;
        } else {
            return ITEM1;
        }
    }

    @Override
    public int getViewTypeCount() {

        return VIEW_TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("Jim Log ", "  ----->    getView()");

        int count = getItemViewType(position);


        if (convertView == null) {

            switch (count) {
                case ITEM:
                    convertView = LayoutInflater.from(context).inflate(R.layout.frag_basket_list_item, null);
                    imageView = (ImageView) convertView.findViewById(R.id.frag_basket_item_clothseimg);
                    clothesName = (TextView) convertView.findViewById(R.id.frag_basket_item_clothsename);
                    accoumt = (TextView) convertView.findViewById(R.id.item_count);
                    price = (TextView) convertView.findViewById(R.id.frag_basket_item_price);
                    imageView.setImageResource(list.get(position).getImageId());
                    clothesName.setText(list.get(position).getClothesName());
                    accoumt.setText(list.get(position).getAccount() + "");
                    price.setText(list.get(position).getPrice() + "");
                    break;
                case ITEM1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.activity_frag_basket_buttomitem, null);
                    break;
            }

        }

        return convertView;
    }

}
