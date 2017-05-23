package br.com.poc.core;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaProperties;
import org.springframework.boot.autoconfigure.transaction.jta.NarayanaJtaConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@ConditionalOnClass(javax.transaction.Transaction.class)
@ConditionalOnProperty(prefix = "spring.jta", value = "enabled", matchIfMissing = true)
@AutoConfigureBefore({ XADataSourceAutoConfiguration.class, ActiveMQAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@Import({NarayanaJtaConfiguration.class })
@EnableConfigurationProperties(JtaProperties.class)
public class JtaAutoConfiguration {

}