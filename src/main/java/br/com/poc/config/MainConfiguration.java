package br.com.poc.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.dialect.PostgreSQL94Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.arjuna.ats.jta.common.jtaPropertyManager;

@Configuration
@EnableTransactionManagement
//@Import({ NarayanaJtaConfiguration.class, XADataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "br.com.poc.config")
public class MainConfiguration {

	/*@Inject private NarayanaJtaConfiguration jtaConfiguration ;
	
	@Bean(name="jpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		hibernateJpaVendorAdapter.setDatabasePlatform(PostgreSQL94Dialect.class.getName());
		return hibernateJpaVendorAdapter;
	}
	
	@Bean(name="entityManagerFactory")
	@Primary
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("pocUnit");
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		return localContainerEntityManagerFactoryBean.getObject();
	}
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.xa.PGXADataSource");
		driverManagerDataSource.setUrl("jdbc:postgresql://172.17.0.3:5432/postgres");
		driverManagerDataSource.setUsername("super");
		driverManagerDataSource.setPassword("DnYuvMGbQa5M9VwO");
		
		PGXADataSource dataSource = new PGXADataSource();
		//dataSource.setUrl("jdbc:postgresql://172.17.0.3:5432/postgres");
		dataSource.setServerName("172.17.0.3");
		dataSource.setDatabaseName("postgres");
		dataSource.setPortNumber(5432);
		dataSource.setUser("super");
		dataSource.setPassword("DnYuvMGbQa5M9VwO");
		
		return new NarayanaDataSourceBean(dataSource);
	}
	
	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		return jtaConfiguration.narayanaUserTransaction();
	}

	@Bean(name = "narayanaTransactionManager")
	public TransactionManager narayanaTransactionManager() throws Throwable {
		return jtaConfiguration.narayanaTransactionManager();
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "narayanaTransactionManager" })
	public PlatformTransactionManager transactionManager() throws Throwable {
		return jtaConfiguration.transactionManager(userTransaction(), narayanaTransactionManager());
	}*/
	

	/*@Bean(name="entityManagerFactory")
	@Primary
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("pocUnit");
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		return localContainerEntityManagerFactoryBean.getObject();
	}
	
	@Bean(name="jpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		hibernateJpaVendorAdapter.setDatabasePlatform(PostgreSQL94Dialect.class.getName());
		return hibernateJpaVendorAdapter;
	}
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.xa.PGXADataSource");
		driverManagerDataSource.setUrl("jdbc:postgresql://172.17.0.3:5432/postgres");
		driverManagerDataSource.setUsername("super");
		driverManagerDataSource.setPassword("DnYuvMGbQa5M9VwO");
		
		return driverManagerDataSource;
	}
	
	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		return new UserTransactionImple();
	}

	@Bean(name = "arjunaTransactionManager")
	public TransactionManager arjunaTransactionManager() throws Throwable {
		return new TransactionManagerImple();
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "arjunaTransactionManager" })
	public PlatformTransactionManager transactionManager() throws Throwable {
		return new JtaTransactionManager(userTransaction(), arjunaTransactionManager());
	}*/
	
	@Bean(name="entityManagerFactory")
	@Primary
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("pocUnit");
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		return localContainerEntityManagerFactoryBean.getObject();
	}
	
	@Bean(name="jpaVendorAdapter")
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
		hibernateJpaVendorAdapter.setDatabasePlatform(PostgreSQL94Dialect.class.getName());
		return hibernateJpaVendorAdapter;
	}
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.xa.PGXADataSource");
		driverManagerDataSource.setUrl("jdbc:postgresql://172.17.0.2:5432/postgres");
		driverManagerDataSource.setUsername("super");
		driverManagerDataSource.setPassword("DnYuvMGbQa5M9VwO");
		
		return driverManagerDataSource;
	}
	
	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		return jtaPropertyManager.getJTAEnvironmentBean().getUserTransaction();
	}

	@Bean(name = "arjunaTransactionManager")
	public TransactionManager arjunaTransactionManager() throws Throwable {
		return jtaPropertyManager.getJTAEnvironmentBean().getTransactionManager();
	}

	@Bean(name = "transactionManager")
	@DependsOn({ "userTransaction", "arjunaTransactionManager" })
	public PlatformTransactionManager transactionManager() throws Throwable {
		return new JtaTransactionManager(userTransaction(), arjunaTransactionManager());
	}
	
	
}
