package tw.eis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

public class AppConfig {
	@Autowired
    private Environment env;
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	//@Bean
	public void getDataSource() {
		additionalProperties();
	}
	
	private void additionalProperties() {
		driverClassName = env.getRequiredProperty("datasource.driverClassName");
		url = env.getRequiredProperty("datasource.url");
		username = env.getRequiredProperty("datasource.username");
		password = env.getRequiredProperty("datasource.password");
		
		System.out.println("driverClassName:" + driverClassName);
		System.out.println("url:" + url);
		System.out.println("username:" + username);
		System.out.println("password:" + password);
	}
}
