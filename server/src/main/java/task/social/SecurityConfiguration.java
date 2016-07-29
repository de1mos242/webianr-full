package task.social;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebMvcSecurity
@Order(200)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SocialConfig socialConfig;
	
	@Autowired
	private UsersConnectionRepository usersConnectionRepository;
	
	@Autowired
	private SocialAuthenticationServiceLocator socialServiceLocator;

	private AuthenticationManager authenticationManagerBuild;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		authenticationManagerBuild = auth.getOrBuild();
		auth.authenticationProvider(new SocialAuthenticationProvider(usersConnectionRepository, socialUsersDetailService()));
		super.configure(auth);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		Filter socialAuthenticationFilter = new SocialAuthenticationFilter(
				http.getSharedObject(AuthenticationManager.class),
		socialConfig.getUserIdSource(), 
		usersConnectionRepository,
		socialServiceLocator);
		
		
        
		http
         .formLogin()
                .loginPage("/resources/login.html")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?param.error=bad_credentials")
                .permitAll()
        .and()
            .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
        .and()
            .authorizeRequests()
                .antMatchers("/favicon.ico", "/resources/**").permitAll()
                .antMatchers("/**").authenticated()
        .and()
            .rememberMe()
        .and()
            .apply(new SpringSocialConfigurer()
                .postLoginUrl("/")
                .alwaysUsePostLoginUrl(true))
         .and().addFilterBefore(socialAuthenticationFilter, BasicAuthenticationFilter.class);
        
    }

    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SimpleSocialUsersDetailService(userDetailsService());
    }
}
