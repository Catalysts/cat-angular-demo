package cc.catalysts.angular.demo;

import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.repository.ManufacturerRepository;
import cc.catalysts.angular.demo.service.ManufacturerService;
import cc.catalysts.angular.spring.CatAngularModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public boolean databseInit(ManufacturerService manufacturerService, ManufacturerRepository manufacturerRepository) {
        if (manufacturerRepository.count() > 0) {
            return true;
        }

        manufacturerService.create(new ManufacturerDto("VW"));
        return true;
    }
}
