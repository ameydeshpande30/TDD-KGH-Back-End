package tdd.kgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
public class MainServer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SpringApplication.run(MainServer.class, args);
		
	}
	
	@Bean
	   public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	   @Bean(name = "dataSource")
	   public DriverManagerDataSource dataSource() {
	       DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	       driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	       driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/KondwaGuestHouse");
	       driverManagerDataSource.setUsername("bunksheet");
	       driverManagerDataSource.setPassword("sheetbunk123");
	       return driverManagerDataSource;
	   }
}
