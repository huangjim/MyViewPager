package jim.android.indexViewpager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jim.android.mainFrame.FragmentMainActivity;

/**
 * Created by Jim Huang on 2015/7/26.
 */
public class Login extends Activity {

    private final String LOCAL_PASSWORD="pass";
    private Button getCodeButton;
    private Button registButton;
    private Boolean flag=false;
    private EditText textPhone;
    private EditText textCode;
    private SharedPreferences sharedPre;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        final CountTime time=new CountTime(60000,1000);
        getCodeButton=(Button)findViewById(R.id.bt_code);
        registButton=(Button)findViewById(R.id.bt_register);
        textPhone=(EditText)findViewById(R.id.et_phone);
        textCode=(EditText)findViewById(R.id.et_code);

        sharedPre=getSharedPreferences(LOCAL_PASSWORD,MODE_PRIVATE);
        editor=sharedPre.edit();
       /* registButton.setClickable(false);
        registButton.setBackgroundColor(Color.parseColor("#bcbdc3"));


        textPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){

                    if (!(textPhone.getText().toString().isEmpty())){
                        registButton.setClickable(true);
                        registButton.setBackgroundColor(Color.parseColor("#28ccfc"));
                    }
                }else {
                    registButton.setClickable(false);
                    registButton.setBackgroundColor(Color.parseColor("#bcbdc3"));
                }
            }
        });*/
        getCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                time.start();
                getCodeButton.setBackgroundColor(Color.parseColor("#28ccfc"));
            }
        });
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strPhone = textPhone.getText().toString();

                if (patternForPhone(strPhone)) {

                    editor.putString("phone", strPhone);
                    editor.apply();
                    showToast("注册成功");
                    Intent intent = new Intent(Login.this, FragmentMainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    try {
                        throw new Exception("电话号码格式不正确");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    showToast("请输入正确的手机号");
                }

            }
        });
    }

    private void showToast(String msg){

        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    private Boolean patternForPhone(String phone) {

        String regExp = "^[1]([3][0-9]{1}|59|58|88|89|87)[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        //return m.find();
        return true;
    }

    class CountTime extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CountTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            flag=false;
            getCodeButton.setText(millisUntilFinished/1000+"秒");
            getCodeButton.setClickable(flag);
        }

        @Override
        public void onFinish() {
            flag=true;
            getCodeButton.setText("重新发送");
            getCodeButton.setBackgroundColor(Color.parseColor("#bcbdc3"));
            getCodeButton.setClickable(flag);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
