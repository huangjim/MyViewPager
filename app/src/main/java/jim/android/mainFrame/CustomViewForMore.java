package jim.android.mainFrame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import jim.android.Splash.R;

/**
 * Created by Administrator on 2015/7/24.
 */
public class CustomViewForMore extends FrameLayout {
    private ImageView leftImage;
    private TextView myText;
    private ImageView rightImage;

    public CustomViewForMore(Context context, AttributeSet attrs) {
        super(context, attrs);

        //注意inflate的第二个参数为this，意思是把资源文件挂载到上下文ViewGroup
        View view = LayoutInflater.from(context).inflate(R.layout.activity_customview_item, this);

        //找出自定义xml文件中的id
        leftImage = (ImageView) view.findViewById(R.id.iv_left);
        myText = (TextView) view.findViewById(R.id.tv_mytext);
        rightImage = (ImageView) findViewById(R.id.iv_right);

        //TypedArray是一个用来存放由context.obtainStyledAttributes获得的自定义属性的数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        //属性的名称是styleable中的名称+“_”+属性名称
        Drawable leftDraw = typedArray.getDrawable(R.styleable.MyView_leftImage);
        String myString = typedArray.getString(R.styleable.MyView_myText);
        Drawable rightDraw = typedArray.getDrawable(R.styleable.MyView_rightImage);

        leftImage.setImageDrawable(leftDraw);
        myText.setText(myString);
        rightImage.setImageDrawable(rightDraw);

        //一定要调用，否则这次的设定会对下次的使用造成影响
        typedArray.recycle();
    }

}
