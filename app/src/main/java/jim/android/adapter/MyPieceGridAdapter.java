package jim.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jim.android.Splash.R;
import jim.android.utils.PieceItemMsg;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class MyPieceGridAdapter extends BaseAdapter {

    private List<PieceItemMsg> list;
    private Context context;

    public MyPieceGridAdapter(List<PieceItemMsg> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.piece_wash_grid_item,null);
            holder.image=(ImageView)convertView.findViewById(R.id.piece_grid_item_img);
            holder.nameText=(TextView)convertView.findViewById(R.id.piece_grid_item_name);
            holder.priceText=(TextView)convertView.findViewById(R.id.piece_grid_item_price);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.image.setImageResource(list.get(position).getImageId());
        holder.nameText.setText(list.get(position).getClothesName());
        holder.priceText.setText(list.get(position).getPrice()+"");
        return convertView;
    }

    private static class ViewHolder{
        private ImageView image;
        private TextView priceText;
        private TextView nameText;
    }
}
