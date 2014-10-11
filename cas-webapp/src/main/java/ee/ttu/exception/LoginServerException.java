package ee.ttu.exception;

import javax.security.auth.login.LoginException;

/**
 * Created by Vahur Kaar on 8.10.2014.
 */
public class LoginServerException extends LoginException {

    public LoginServerException() {
        super();
    }

    public LoginServerException(String msg) {
        super(msg);
    }
}
