package ee.ttu;

import ee.ttu.xml.GetUserResponse;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;

/**
 * Created by Vahur Kaar on 6.10.2014.
 */
public class CustomUsersAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    private CoreWebServiceClient coreWebServiceClient;

    /** {@inheritDoc} */
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
        logger.debug("Loading user from Core Application...");

        final String username = credential.getUsername();
        final String cachedPassword = getUserPassword(username);

        logger.debug("Found password from core for user {}", username);
        if (cachedPassword == null) {
            logger.debug("{} was not found in the map.", username);
            throw new AccountNotFoundException(username + " not found in backing map.");
        }

        final String encodedPassword = this.getPasswordEncoder().encode(credential.getPassword());
        if (!cachedPassword.equals(encodedPassword)) {
            throw new FailedLoginException();
        }
        return createHandlerResult(credential, new SimplePrincipal(username), null);
    }

    private String getUserPassword(String username) throws AccountNotFoundException {
        if (username == null) {
            logger.debug("Username was null.");
            throw new AccountNotFoundException("Username was null!");
        }

        GetUserResponse response = coreWebServiceClient.getUserByUsername(username);
        if (response != null && response.getUser() != null) {
            return response.getUser().getPassword();
        }

        return null;
    }

    public void setCoreWebServiceClient(CoreWebServiceClient coreWebServiceClient) {
        this.coreWebServiceClient = coreWebServiceClient;
    }
}
