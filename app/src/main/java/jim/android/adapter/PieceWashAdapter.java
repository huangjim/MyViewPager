package jim.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import jim.android.bean.Results;
import jim.android.Splash.R;
import jim.android.pieceWash.PieceWashMain;

/**
 * Created by Jim Huang on 2015/9/7.
 */
public class PieceWashAdapter extends BaseAdapter {
    private List<Results> list;
    private Context context;
    private DisplayImageOptions options;

    public PieceWashAdapter(List<Results> list, Context context) {
        this.list = list;
        this.context = context;
        options= PieceWashMain.options;
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

        Results results=list.get(position);
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
        Log.i("PieceAdapter",results.getName()+"---->"+results.getPrice()+"---->"+results.getImage());
        String str=results.getImage();
        holder.nameText.setText(results.getName());
        holder.priceText.setText(results.getPrice()+"");
        ImageLoader.getInstance().displayImage(str,holder.image,options);
        return convertView;
    }

    static class ViewHolder {

        private ImageView image;
        private TextView priceText;
        private TextView nameText;
    }

    private String parseUTF(String str){

        try {
            String string=URLDecoder.decode(str, "ISO-8859-1");
            return URLEncoder.encode(string,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
