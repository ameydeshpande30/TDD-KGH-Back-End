package tdd.kgh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter { 
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();;


	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.inMemoryAuthentication()
		.withUser("amey").password(bCryptPasswordEncoder.encode("password")).roles("ADMIN");
	}  
	 
    @Override  
    protected void configure(HttpSecurity http) throws Exception {  
    	 http.cors().and().csrf().disable()
         .authorizeRequests()
         .anyRequest()
         .authenticated()
         .and().httpBasic()  
    	 .and().sessionManagement().disable();
    }

	
    
}