package jim.android.Splash;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/26.
 */
public class CustomTitle extends FrameLayout {

    private TextView titleText;
    private Button backButton;
    public CustomTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view= LayoutInflater.from(context).inflate(R.layout.activity_register_customtitle,this);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton=(Button)view.findViewById(R.id.button_left);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Activity)getContext()).finish();
            }
        });
    }

    public void setTitleText(String text){

        titleText.setText(text);
    }

    public void setBackButtonText(String text){

        backButton.setText(text);
    }

    public void setBackButtonListener(OnClickListener listener){

        setOnClickListener(listener);
    }
}
