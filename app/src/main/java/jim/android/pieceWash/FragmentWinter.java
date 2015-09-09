package jim.android.pieceWash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
import java.util.List;

import jim.android.Splash.R;
import jim.android.adapter.PieceWashAdapter;
import jim.android.bean.Data;
import jim.android.bean.PieceWashBean;
import jim.android.bean.Results;
import jim.android.mainFrame.LazyFragment;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class FragmentWinter extends LazyFragment {

    private View view;
    private GridView gridView;
    private RequestQueue myQueue;
    private List<Results> results;
    private Gson gson;
    private boolean isPrepared;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_winter, container, false);

        initView();
        isPrepared = true;
        return view;

    }

    private String parseUTF(String str) {

        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void lazyLoad() {
        if (isVisible && isPrepared) {
            StringRequest request = new StringRequest("http://123.56.138.192:8002/products/?category__name=" + parseUTF("皮衣"), new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    PieceWashBean pieceWashBean = gson.fromJson(s, PieceWashBean.class);
                    Data data = pieceWashBean.getData();
                    results = data.getResults();
                    gridView.setAdapter(new PieceWashAdapter(results, getActivity()));

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

    private void initView() {
        gridView = (GridView) view.findViewById(R.id.grid_winter);
        myQueue = Volley.newRequestQueue(getActivity());
        results = new ArrayList<>();
        gson = new Gson();
    }
}
