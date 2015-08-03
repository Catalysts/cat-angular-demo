package cc.catalysts.angular.demo;

import cc.catalysts.angular.spring.CatAngularModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({CatAngularModule.class})
public class CatAngularDemoApplication extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    public static void main(String[] args) {
        SpringApplication.run(CatAngularDemoApplication.class, args);
    }
}
