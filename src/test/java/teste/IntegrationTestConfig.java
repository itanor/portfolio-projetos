package teste;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.transaction.Transactional;

//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@SpringApplicationConfiguration(WebApplication.class)
//@WebAppConfiguration
@ActiveProfiles("itest")
@TestPropertySource("classpath:application-itest.yml")
@Transactional
public @interface IntegrationTestConfig {

}
