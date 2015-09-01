package jim.android.mainFrame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/25.
 */
public class ShareCodeCheck extends Activity implements View.OnClickListener {
    private EditText code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_check);
        initView();
    }

    private void initView() {
        ImageView back=(ImageView)findViewById(R.id.share_check_back);
        Button check=(Button)findViewById(R.id.btn_share_check);
        code=(EditText)findViewById(R.id.share_code);
        back.setOnClickListener(this);
        check.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_check_back:
                finish();
                break;
            case R.id.btn_share_check:
                if (code.getText().equals("1be2")){
                    Toast.makeText(this,"验证成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"验证码错误",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
