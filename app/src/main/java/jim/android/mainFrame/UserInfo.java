package jim.android.mainFrame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/30.
 */
public class UserInfo extends Activity implements View.OnClickListener{

    private EditText userName;
    private EditText userPhone;
    private TextView district;
    private EditText userAddress;
    private RadioGroup sex;
    private SharedPreferences preferences;
    private RadioButton man;
    private RadioButton woman;
    private Button saveInfo;
    private SharedPreferences.Editor editor;
    private int sexFlag;
    private DialogHint dialogHint;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        initView();
        ImageView back = (ImageView) findViewById(R.id.userinfo_back);
        back.setOnClickListener(this);

    }



    private void initView() {

        handler=new Handler();
        userName = (EditText) findViewById(R.id.userinfo_name);
        userAddress = (EditText) findViewById(R.id.userinfo_address);
        userPhone = (EditText) findViewById(R.id.userinfo_phone);
        district = (TextView) findViewById(R.id.userinfo_district);
        sex = (RadioGroup) findViewById(R.id.select_sex);
        saveInfo=(Button)findViewById(R.id.userinfo_save);
        man=(RadioButton)findViewById(R.id.male);
        woman=(RadioButton)findViewById(R.id.lady);
        saveInfo.setOnClickListener(this);

        preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        editor=preferences.edit();

        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.male) {
                    sexFlag=1;
                    man.setTextColor(Color.WHITE);
                    woman.setTextColor(Color.BLACK);


                } else if (checkedId == R.id.lady) {
                    sexFlag=0;
                    woman.setTextColor(Color.WHITE);
                    man.setTextColor(Color.BLACK);


                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userinfo_back:
                finish();
                break;
            case R.id.userinfo_save:
                while (true){
                    if (userPhone.getText().toString().length()!=11){
                        dialogHint=new DialogHint(UserInfo.this,"请输入正确的手机号",0);
                        dialogHint.show();
                        dialogHint.setCancelable(true);
                        dialogHint.setCanceledOnTouchOutside(true);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialogHint.dismiss();
                            }
                        },1000);
                        break;

                    }
                    Log.i("userphone", userPhone.getText().toString());
                    editor.putString("name",userName.getText().toString());
                    editor.putString("phone",userPhone.getText().toString());
                    editor.putString("address", userAddress.getText().toString());
                    editor.putString("district", district.getText().toString());
                    if (sexFlag==1){
                        editor.putInt("sex01", 1);
                    }else if (sexFlag==0)
                        editor.putInt("sex01", 0);
                    editor.apply();
                    break;

                }
                break;

        }
    }

    @Override
    protected void onResume() {

        super.onResume();

        userName.setText(preferences.getString("name", ""));
        userPhone.setText(preferences.getString("phone", ""));
        userAddress.setText(preferences.getString("address", ""));
        int str=preferences.getInt("sex01", -1);
        if (str!=-1){
            if (str==1){
                man.setChecked(true);
            }else if (str==0){
                woman.setChecked(true);
            }
        }
    }

}
