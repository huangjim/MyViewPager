package jim.android.Splash;

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
public class Login extends Activity{

    private Button getCodeButton;
    private Boolean flag=false;
    private EditText textPhone;
    private EditText textCode;
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
        Button registButton = (Button) findViewById(R.id.bt_register);
        textPhone=(EditText)findViewById(R.id.et_phone);
        textCode=(EditText)findViewById(R.id.et_code);

        String LOCAL_PASSWORD = "pass";
        SharedPreferences sharedPre = getSharedPreferences(LOCAL_PASSWORD, MODE_PRIVATE);
        editor= sharedPre.edit();

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
                    showToast("注册成功");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                editor.putString("phone", strPhone);
                                editor.apply();
                                Intent intent = new Intent(Login.this, FragmentMainActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

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
        System.gc();
        finish();
    }
}
