package tdd.kgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import tdd.kgh.file.FileStorageProperties;



@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class MainServer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		 System.out.println(bCryptPasswordEncoder.encode("admin123"));
		 SpringApplication.run(MainServer.class, args);
		 
		
	}
	
	@Bean
	   public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
//	   @Bean(name = "dataSource")
//	   public DriverManagerDataSource dataSource() {
//	       DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//	       driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	       driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/KondwaGuestHouse");
//	       driverManagerDataSource.setUsername("bunksheet");
//	       driverManagerDataSource.setPassword("sheetbunk123");
//	       return driverManagerDataSource;
//	   }
	   @Bean(name = "dataSource")
	   public DriverManagerDataSource dataSource() {
	       DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	       driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	       driverManagerDataSource.setUrl("jdbc:mysql://db4free.net/kondwaguesthouse");
	       driverManagerDataSource.setUsername("asdtddkgh");
	       driverManagerDataSource.setPassword("asd-tdd-kgh");
	       return driverManagerDataSource;
	   }
}
