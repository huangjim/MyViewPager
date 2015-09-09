package jim.android.pieceWash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import jim.android.bean.Data;
import jim.android.bean.PieceWashBean;
import jim.android.bean.Results;
import jim.android.Splash.R;
import jim.android.adapter.PieceWashAdapter;

/**
 * Created by Jim Huang on 2015/8/9.
 */
public class FragmentSummer extends Fragment {

    private View view;
    private GridView gridView;
    private RequestQueue myQueue;
    private List<Results> results;
    private Gson gson;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.activity_frag_summer,container,false);

        initView();
        return view;

    }

    private String parseUTF(String str){

        try {
            return URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }

    private void initView() {
        gridView=(GridView)view.findViewById(R.id.grid_summer);
        myQueue= Volley.newRequestQueue(getActivity());
        results=new ArrayList<>();
        gson=new Gson();

        StringRequest request=new StringRequest("http://123.56.138.192:8002/products/?category__name=" + parseUTF("夏装"), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("Summer Log",s);
                PieceWashBean pieceWashBean=gson.fromJson(s,PieceWashBean.class);
                Data data=pieceWashBean.getData();
                results=data.getResults();
                Log.i("result Log", results.get(1).toString());

                gridView.setAdapter(new PieceWashAdapter(results, getActivity()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
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
        Log.i("contentType", request.getBodyContentType());

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
