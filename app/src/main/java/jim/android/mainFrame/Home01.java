package jim.android.mainFrame;

import android.util.Log;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Jim Huang on 2015/8/25.
 */
class Home01 implements Response.Listener<String> {

    @Override
    public void onResponse(String o) {
        try {
            JSONObject localJSONObject=new JSONObject(o);
            if (localJSONObject.getInt("status_code")==1){
                Home.Arraybanner=localJSONObject.getJSONArray("data");
                Log.i("数量",""+Home.Arraybanner.length());
                return;
            }
        }catch (JSONException e){

            e.printStackTrace();
        }
    }
}
