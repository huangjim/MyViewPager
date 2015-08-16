package jim.android.indexViewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Jim Huang on 2015/8/2.
 */
public class FirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Intent intent=new Intent(FirstActivity.this,ViewPagerActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
