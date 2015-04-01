package allinone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import scala.annotation.meta.getter;
import allinone.repositories.TestEntityRepositoryCustom;
import allinone.repositories.TestEntityRepositoryImpl;

import com.zaxxer.hikari.HikariDataSource;

//todo check those
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@PropertySource ( "classpath:/application.properties")
@EnableJpaAuditing
@EnableScheduling
@EnableJpaRepositories
@ComponentScan
@EnableTransactionManagement
public class ApplicationTest extends WebMvcConfigurerAdapter {

	private static final Logger	log	= LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public TestEntityRepositoryImpl testEntityRepositoryImpl() {
		TestEntityRepositoryImpl repo = new TestEntityRepositoryImpl();
		return repo;
	}
	
	
	@Bean
	org.h2.tools.Server h2Server() {
		log.info("Loading h2 server.");
		org.h2.tools.Server server = new Server();
		try {
			server.runTool("-tcp");
			server.runTool("-tcpAllowOthers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return server;
	}

	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}

	@Bean
	@Primary
	public static HikariDataSource dataSource() {
		int maxPoolSize = 10;
		boolean isAutoCommit = false;

		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName("org.h2.Driver");
		dataSource
		        .setJdbcUrl("jdbc:h2:./skeledbTest;AUTO_SERVER=FALSE;IFEXISTS=FALSE;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		dataSource.setConnectionTestQuery("select 1");
		dataSource.setMaximumPoolSize(maxPoolSize);
		dataSource.setAutoCommit(isAutoCommit);
		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}

}
