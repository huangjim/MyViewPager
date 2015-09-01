package jim.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jim.android.Splash.R;
import jim.android.mainFrame.BasketQuan;
import jim.android.mainFrame.UserInfo;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/4.
 */
public class BasketAdapter extends BaseAdapter {

    private final int VIEW_TYPE_COUNT = 2;
    private final int ITEM = 0;
    private final int ITEM1 = 1;
    public List<BasketItemMsg> list;
    private Context context;
    private ImageView ivEmpty;
    private TextView displayPrice;


    public BasketAdapter(List<BasketItemMsg> list, Context context, ImageView ivEmpty, TextView displayPrice) {

        this.list = list;
        this.context = context;
        this.ivEmpty = ivEmpty;
        this.displayPrice = displayPrice;

    }

    public BasketAdapter(List<BasketItemMsg> list) {

        this.list = list;

    }
    public BasketAdapter(){
        list=new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (list.size() == 0) {
            ivEmpty.setVisibility(View.VISIBLE);
            return 0;
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i("Jim Log ", "  ----->    getView()");

        int count = getItemViewType(position);

        switch (count) {
            case ITEM:
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.frag_basket_list_item, null);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.frag_basket_item_clothseimg);
                    holder.clothesName = (TextView) convertView.findViewById(R.id.frag_basket_item_clothsename);
                    holder.accoumt = (TextView) convertView.findViewById(R.id.item_count);
                    holder.price = (TextView) convertView.findViewById(R.id.frag_basket_item_price);
                    holder.del = (ImageButton) convertView.findViewById(R.id.basket_item_del);
                    holder.cut = (TextView) convertView.findViewById(R.id.item_cut);
                    holder.add = (TextView) convertView.findViewById(R.id.item_add);
                    convertView.setTag(holder);
                }
                holder = (ViewHolder) convertView.getTag();
                holder.imageView.setImageResource(list.get(position).getImageId());
                holder.clothesName.setText(list.get(position).getClothesName());
                holder.accoumt.setText(list.get(position).getAccount() + "");
                holder.price.setText(list.get(position).getPrice() + "");
                holder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (list.size() > 0) {
                            list.remove(position);
                            notifyDataSetChanged();
                        } else if (list.size() == 0) {

                            list.clear();
                            notifyDataSetChanged();

                            ivEmpty.setVisibility(View.VISIBLE);
                        }
                    }
                });
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = list.get(position).getAccount();
                        num++;
                        list.get(position).setAccount(num);
                        notifyDataSetChanged();
                    }
                });
                holder.cut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = list.get(position).getAccount();
                        if (num > 1) {
                            num--;
                            list.get(position).setAccount(num);
                            notifyDataSetChanged();
                        }

                    }
                });
                break;
            case ITEM1:

                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.activity_frag_basket_buttomitem, null);
                    holder.allPrice = (TextView) convertView.findViewById(R.id.allprice);
                    holder.quan=(RelativeLayout)convertView.findViewById(R.id.frag_basket_quan);
                    holder.wirteLocation=(RelativeLayout)convertView.findViewById(R.id.write_location1);
                    convertView.setTag(holder);
                }
                holder = (ViewHolder) convertView.getTag();
                holder.allPrice.setText(getTotalPrice() + "");
                displayPrice.setText(getTotalPrice() + "");
                holder.quan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, BasketQuan.class);

                        context.startActivity(intent);
                    }
                });

                holder.wirteLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, UserInfo.class);

                        context.startActivity(intent);
                    }
                });
                break;
        }

        return convertView;
    }

    public float getTotalPrice() {
        float f = 0.0F;
        for (int i = 0; ; ++i) {
            if (i >= this.list.size())
                return f;
            BasketItemMsg basketItemMsg = this.list.get(i);
            f += basketItemMsg.getPrice() * basketItemMsg.getAccount();
        }
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView clothesName;
        private TextView accoumt;
        private TextView price;
        private TextView allPrice;
        private ImageView del;
        private TextView cut;
        private TextView add;
        private RelativeLayout quan;
        private RelativeLayout wirteLocation;
    }
}
