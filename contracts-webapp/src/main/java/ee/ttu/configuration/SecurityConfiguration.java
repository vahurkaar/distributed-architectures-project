package ee.ttu.configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorageImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.authentication.EhCacheBasedTicketCache;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.cas.web.authentication.ServiceAuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * Created by Vahur Kaar on 8.11.2014.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(environment.getProperty("cas.service"));
        serviceProperties.setSendRenew(false);
        serviceProperties.setAuthenticateAllArtifacts(true);

        return serviceProperties;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        return new SingleSignOutFilter();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(environment.getProperty("cas.location") + "/logout",
                securityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/j_spring_cas_security_logout");

        return logoutFilter;
    }

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        casAuthenticationFilter.setServiceProperties(serviceProperties());
        casAuthenticationFilter.setProxyGrantingTicketStorage(proxyGrantingTicketStorage());
        casAuthenticationFilter.setProxyReceptorUrl("/j_spring_cas_security_proxyreceptor");
        casAuthenticationFilter.setAuthenticationDetailsSource(serviceAuthenticationDetailsSource());

        return casAuthenticationFilter;
    }

    @Bean
    public ServiceAuthenticationDetailsSource serviceAuthenticationDetailsSource() {
        return new ServiceAuthenticationDetailsSource();
    }

    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        casAuthenticationEntryPoint.setLoginUrl(environment.getProperty("cas.location") + "/login");

        return casAuthenticationEntryPoint;
    }

    @Bean
    public CasAuthenticationProvider authenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setKey("casAuthProviderKey");
        casAuthenticationProvider.setAuthenticationUserDetailsService(
                new UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken>(userDetailsService));
        casAuthenticationProvider.setTicketValidator(cas20ProxyTicketValidator());
        casAuthenticationProvider.setStatelessTicketCache(ehCacheBasedTicketCache());

        return casAuthenticationProvider;
    }

    @Bean
    public TicketValidator cas20ProxyTicketValidator() {
        Cas20ProxyTicketValidator cas20ProxyTicketValidator = new Cas20ProxyTicketValidator(environment.getProperty("cas.location"));
        cas20ProxyTicketValidator.setAcceptAnyProxy(true);
        cas20ProxyTicketValidator.setProxyCallbackUrl(environment.getProperty("cas.callbackurl"));
        cas20ProxyTicketValidator.setProxyGrantingTicketStorage(proxyGrantingTicketStorage());

        return cas20ProxyTicketValidator;
    }

    @Bean
    public EhCacheBasedTicketCache ehCacheBasedTicketCache() {
        EhCacheBasedTicketCache ehCacheBasedTicketCache = new EhCacheBasedTicketCache();
        ehCacheBasedTicketCache.setCache(ticketCache());

        return ehCacheBasedTicketCache;
    }

    @Bean
    public Cache ticketCache() {
        Cache cache = new Cache("casTickets", 50, true, false, 3600, 900);
        cache.setCacheManager(ehCacheManager());

        return cache;
    }

    @Bean
    public CacheManager ehCacheManager() {
        return ehCacheManagerFactoryBean().getObject();
    }


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        return new EhCacheManagerFactoryBean();
    }

    @Bean
    public ProxyGrantingTicketStorage proxyGrantingTicketStorage() {
        return new ProxyGrantingTicketStorageImpl();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(casAuthenticationFilter())
                .exceptionHandling()
                    .authenticationEntryPoint(casAuthenticationEntryPoint())
                    .and()
                .authorizeRequests()
                    .antMatchers("/static/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .addFilterBefore(logoutFilter(), LogoutFilter.class)
                .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class)
                .addFilter(casAuthenticationFilter())
                .logout();
    }
}
