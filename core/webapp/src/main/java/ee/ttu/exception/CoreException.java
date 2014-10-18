package ee.ttu.exception;

/**
 * Created by Vahur Kaar on 27.09.2014.
 */
public class CoreException extends RuntimeException {

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
