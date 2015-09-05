package jim.android.exception;

/**
 * Created by Jim Huang on 2015/9/4.
 */
public class BaseException extends RuntimeException {

    public BaseException() {

    }

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }
}
