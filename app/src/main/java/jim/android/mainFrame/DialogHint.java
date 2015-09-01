package jim.android.mainFrame;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import jim.android.Splash.R;

/**
 * Created by Jim Huang on 2015/8/31.
 */
public class DialogHint extends Dialog {

    public DialogHint(Context context, String paramString,int theme) {
        super(context, theme);
        View view= LayoutInflater.from(context).inflate(R.layout.dialoghint, null);
        ((TextView)view.findViewById(R.id.dialog_txt)).setText(paramString);
        setContentView(view);
        Window localWindow=getWindow();
        WindowManager.LayoutParams layoutParams=localWindow.getAttributes();
        layoutParams.alpha=0.7f;
        layoutParams.gravity=17;
        localWindow.setAttributes(layoutParams);

    }


}
