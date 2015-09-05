package jim.android.mainFrame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/9/4.
 */
public class MyLocation extends Activity {

    @ViewInject(R.id.location_back)
    private ImageView back;
    @ViewInject(R.id.modify_location)
    private TextView motifyLocation;
    @ViewInject(R.id.mylocation_name)
    private TextView name;
    @ViewInject(R.id.mylocation_phone)
    private TextView phone;
    @ViewInject(R.id.mylocation_location)
    private TextView location;

    private MyApplication application=MyApplication.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylocation);
        ViewUtils.inject(this);


    }


    @OnClick({R.id.location_back,R.id.modify_location})
    public void viewOnClick(View v) {
        switch (v.getId()){
            case R.id.location_back:
                finish();
                break;
            case R.id.modify_location:
                Intent intent2=new Intent(this,UserInfo.class);
                startActivity(intent2);
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (application.getPreferences()!=null){
            if (application.getPreferences().getInt("sex01",-1)==0)
                name.setText(application.getPreferences().getString("name","")+"女士");
            else if (application.getPreferences().getInt("sex01",-1)==0)
                name.setText(application.getPreferences().getString("name","")+"先生");
            phone.setText(application.getPreferences().getString("phone",""));
            location.setText(application.getPreferences().getString("address", ""));
        }
    }
}
