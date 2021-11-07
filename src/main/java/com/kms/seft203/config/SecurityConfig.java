package com.kms.seft203.config;

import com.kms.seft203.filter.CustomAuthenticationFilter;
import com.kms.seft203.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


/**
 * This class is responsible for the security configuration. To turn of the project security, just
 * ignore the @Configuration and @EnableWebSecurity.
 *
 * For more details, see the configure method where each API is established for an appropriate User-Role.
 * Basically, this class is called by the authentication & authorization filter.
 */

// @Configuration
// @EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String APP_API = "/app/**";
    public static final String AUTH_API = "/auth/**";
    public static final String CONTACT_API = "/contacts/**";
    public static final String DASHBOARD_API = "/dashboards/**";
    public static final String REPORT_API = "/reports/**";
    public static final String TASK_API = "/tasks/**";
    public static final String SECURITY_API = "/security/login";

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());

        customAuthenticationFilter.setFilterProcessesUrl(SECURITY_API);
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.csrf().ignoringAntMatchers(SECURITY_API);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(SECURITY_API).permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET, APP_API, CONTACT_API, DASHBOARD_API, REPORT_API)
                .hasAnyAuthority(ROLE_USER);

        http.authorizeRequests().antMatchers(HttpMethod.POST, AUTH_API)
                .hasAnyAuthority(ROLE_USER);

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, CONTACT_API, TASK_API)
                .hasAnyAuthority(ROLE_ADMIN);

        http.authorizeRequests()
                .antMatchers(HttpMethod.PUT, DASHBOARD_API, CONTACT_API, TASK_API)
                .hasAnyAuthority(ROLE_ADMIN);

        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, CONTACT_API, TASK_API)
                .hasAnyAuthority(ROLE_ADMIN);

        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
