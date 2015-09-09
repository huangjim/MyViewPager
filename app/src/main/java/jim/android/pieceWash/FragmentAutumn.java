package jim.android.pieceWash;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jim.android.Splash.R;
import jim.android.adapter.PieceWashAdapter;
import jim.android.bean.BasketBean;
import jim.android.bean.Data;
import jim.android.bean.PieceWashBean;
import jim.android.bean.Results;
import jim.android.mainFrame.Basket;
import jim.android.mainFrame.FragmentMainActivity;
import jim.android.mainFrame.LazyFragment;
import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class FragmentAutumn extends LazyFragment {
    private View view;
    private GridView gridView;
    private RequestQueue myQueue;
    private List<Results> list;
    private Gson gson;
    private boolean isPrepared;
    private JianxiPopup jianxiPopup;
    private Results results;
    private BasketBean basketBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_autumn, container, false);

        initView();
        isPrepared = true;
        return view;

    }

    @Override
    protected void lazyLoad() {
        if (isVisible && isPrepared) {
            StringRequest request = new StringRequest("http://123.56.138.192:8002/products/?category__name=" + parseUTF("冬装"), new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    PieceWashBean pieceWashBean = gson.fromJson(s, PieceWashBean.class);
                    Data data = pieceWashBean.getData();
                    list = data.getResults();
                    gridView.setAdapter(new PieceWashAdapter(list, getActivity()));

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            }) {
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {
                        String jsonObject = new String(response.data, "UTF-8");
                        return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    } catch (Exception je) {
                        return Response.error(new ParseError(je));
                    }

                }
            };

            myQueue.add(request);
        }
    }

    private String parseUTF(String str) {

        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }

    private void initView() {
        gridView = (GridView) view.findViewById(R.id.grid_autumn);
        myQueue = Volley.newRequestQueue(getActivity());
        list = new ArrayList<>();
        gson = new Gson();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                results= (Results) gridView.getItemAtPosition(position);
                Iterator iterator= FragmentMainActivity.basketList.iterator();
                Drawable drawable=((ImageView)view.findViewById(R.id.piece_grid_item_img)).getDrawable();

                JianxiPopup jianxiPopup=new JianxiPopup(getActivity(),results,drawable);
                jianxiPopup.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
