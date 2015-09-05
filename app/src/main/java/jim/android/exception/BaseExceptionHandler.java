package jim.android.exception;

import android.os.*;

/**
 * Created by Jim Huang on 2015/9/4.
 */
public abstract class BaseExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        if (handlerException(ex)){
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

        }

    }

    public abstract boolean handlerException(Throwable ex) ;
}
