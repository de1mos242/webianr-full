package task.oauth;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableOAuth2Client
@EnableWebMvc
@Configuration
public class OAuthConfig {
	
	@Bean
	public FacebookController facebookController(@Qualifier("facebookRestTemplate")
	RestOperations facebookRestTemplate) {
		FacebookController controller = new FacebookController();
		controller.setFacebookRestTemplate(facebookRestTemplate);
		return controller;
	}
	
	@Autowired
	public DataSource dataSource;
	
	
	@Resource
	@Qualifier("accessTokenRequest")
	private AccessTokenRequest accessTokenRequest;
	
	@Autowired
	private OAuth2ClientContext oauth2Context;

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	public OAuth2RestTemplate facebookRestTemplate() {
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(facebook(), oauth2Context);
		AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
		provider.setClientTokenServices(clientTokenServices());
		return oAuth2RestTemplate;
	}
	
	@Bean
	public ClientTokenServices clientTokenServices() {
		return new JdbcClientTokenServices(dataSource);
	}
	
	
	@Bean
	public OAuth2ProtectedResourceDetails facebook() {
		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
		details.setId("facebook");
		details.setClientId("233668646673605");
		details.setClientSecret("33b17e044ee6a4fa383f46ec6e28ea1d");
		details.setAccessTokenUri("https://graph.facebook.com/oauth/access_token");
		details.setUserAuthorizationUri("https://www.facebook.com/dialog/oauth");
		details.setScope(Arrays.asList("user_friends"));
		details.setTokenName("oauth_token");
		details.setAuthenticationScheme(AuthenticationScheme.query);
		details.setClientAuthenticationScheme(AuthenticationScheme.form);
		return details;
	}

}
