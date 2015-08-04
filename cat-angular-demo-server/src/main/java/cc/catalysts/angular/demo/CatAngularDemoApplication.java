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

import java.time.LocalDate;

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
    public boolean databaseInit(ManufacturerService manufacturerService, ManufacturerRepository manufacturerRepository) {
        if (manufacturerRepository.count() > 0) {
            return true;
        }

        LocalDate foundingDate = LocalDate.now();

        manufacturerService.create(new ManufacturerDto("VW", foundingDate, "GER"));
        manufacturerService.create(new ManufacturerDto("Audi",foundingDate.minusYears(1),"GER"));
        manufacturerService.create(new ManufacturerDto("Bmw",foundingDate.minusYears(2),"GER"));
        manufacturerService.create(new ManufacturerDto("Seat",foundingDate.minusYears(3),"ESP"));
        manufacturerService.create(new ManufacturerDto("Opel",foundingDate.minusYears(4),"GER"));
        manufacturerService.create(new ManufacturerDto("Skoda",foundingDate.minusYears(5),"CZE"));
        manufacturerService.create(new ManufacturerDto("Tesla",foundingDate.minusYears(6),"USA"));
        manufacturerService.create(new ManufacturerDto("Mercedes-Benz",foundingDate.minusYears(7),"GER"));
        manufacturerService.create(new ManufacturerDto("Mazda",foundingDate.minusYears(8),"JPN"));

        return true;
    }
}
