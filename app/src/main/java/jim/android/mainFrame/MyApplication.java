package jim.android.mainFrame;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import jim.android.utils.BasketItemMsg;

/**
 * Created by Jim Huang on 2015/8/22.
 */
public class MyApplication extends Application {

    private List<BasketItemMsg> list;
    @Override
    public void onCreate() {
        super.onCreate();
        list=new ArrayList<BasketItemMsg>();
    }

    public List<BasketItemMsg> getList() {
        return list;
    }

    public void setList(List<BasketItemMsg> list) {
        this.list = list;
    }
}
