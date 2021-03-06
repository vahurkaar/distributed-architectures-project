package ee.ttu;

import ee.ttu.exception.LoginServerException;
import ee.ttu.xml.GetUserResponse;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.security.GeneralSecurityException;

/**
 * Created by Vahur Kaar on 6.10.2014.
 */
public class CustomUsersAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    private CoreWebServiceClient coreWebServiceClient;

    private AuthenticationLoggingService authenticationLoggingService;

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
            authenticationLoggingService.logAuthentication(credential.getUsername(), credential.getPassword(), "User was not found in map!");
            throw new AccountNotFoundException(username + " not found in backing map.");
        }

        final String encodedPassword = this.getPasswordEncoder().encode(credential.getPassword());
        if (!cachedPassword.equals(encodedPassword)) {
            authenticationLoggingService.logAuthentication(credential.getUsername(), credential.getPassword(), "Invalid credentials!");
            throw new FailedLoginException();
        }
        authenticationLoggingService.logAuthentication(credential.getUsername(), credential.getPassword(), "Success!");
        return createHandlerResult(credential, new SimplePrincipal(username), null);
    }

    private String getUserPassword(String username) throws LoginException {
        if (username == null) {
            logger.debug("Username was null.");
            throw new AccountNotFoundException("Username was null!");
        }

        GetUserResponse response = null;
        try {
            response = coreWebServiceClient.getUserByUsername(username);
        } catch (Exception e) {
            throw new LoginServerException();
        }

        if (response.getUser() == null) {
            throw new AccountNotFoundException();
        }

        return response.getUser().getPassword();
    }

    public void setCoreWebServiceClient(CoreWebServiceClient coreWebServiceClient) {
        this.coreWebServiceClient = coreWebServiceClient;
    }

    public void setAuthenticationLoggingService(AuthenticationLoggingService authenticationLoggingService) {
        this.authenticationLoggingService = authenticationLoggingService;
    }
}
