package jim.android.mainFrame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/30.
 */
public class BasketQuan extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiyiquan);
        ImageView back=(ImageView)findViewById(R.id.quan_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
