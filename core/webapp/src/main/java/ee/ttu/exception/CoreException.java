package ee.ttu.exception;

/**
 * Created by Vahur Kaar on 27.09.2014.
 */
public class CoreException extends RuntimeException {

    public CoreException() {
        super();
    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }

    protected CoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
