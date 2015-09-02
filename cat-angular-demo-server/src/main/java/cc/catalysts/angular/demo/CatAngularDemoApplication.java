package cc.catalysts.angular.demo;

import cc.catalysts.angular.demo.dto.CountryDto;
import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.repository.CountryRepository;
import cc.catalysts.angular.demo.repository.ManufacturerRepository;
import cc.catalysts.angular.demo.service.CountryService;
import cc.catalysts.angular.demo.service.ManufacturerService;
import cc.catalysts.angular.demo.web.controller.api.CountrySearchRequest;
import cc.catalysts.angular.spring.CatAngularModule;
import cc.catalysts.angular.spring.dto.PageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

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
    public boolean databaseInit(ManufacturerService manufacturerService, ManufacturerRepository manufacturerRepository,
                                CountryService countryService, CountryRepository countryRepository) {
        boolean result = true;

        result &= initCountryData(countryService, countryRepository);

        result &= initManufacturerData(manufacturerService, manufacturerRepository, countryService);

        return result;
    }

    private boolean initCountryData(CountryService countryService, CountryRepository countryRepository) {

        if (countryRepository.count() > 0) {
            return true;
        }

        countryService.create(new CountryDto("Austria", "ATU"));
        countryService.create(new CountryDto("Germany", "GER"));
        countryService.create(new CountryDto("France", "FRA"));
        countryService.create(new CountryDto("Czech Republic", "CZE"));
        countryService.create(new CountryDto("Japan", "JPN"));
        countryService.create(new CountryDto("United States", "USA"));
        countryService.create(new CountryDto("Spain", "ESP"));
        countryService.create(new CountryDto("Just another", "???", true));

        return true;
    }

    private boolean initManufacturerData(ManufacturerService manufacturerService, ManufacturerRepository manufacturerRepository, CountryService countryService) {
        if (manufacturerRepository.count() > 0) {
            return true;
        }

        LocalDate foundingDate = LocalDate.now().minusYears(1);

        PageDto<CountryDto> countries = countryService.list(new CountrySearchRequest());

        Map<String, CountryDto> countryMap = countries.getElements().stream().collect(Collectors.toMap(CountryDto::getCode, x -> x));

        manufacturerService.create(new ManufacturerDto("VW", foundingDate, countryMap.get("GER")));
        manufacturerService.create(new ManufacturerDto("Audi", foundingDate.minusYears(1), countryMap.get("GER")));
        manufacturerService.create(new ManufacturerDto("Bmw", foundingDate.minusYears(2), countryMap.get("GER")));
        manufacturerService.create(new ManufacturerDto("Seat", foundingDate.minusYears(3), countryMap.get("ESP")));
        manufacturerService.create(new ManufacturerDto("Opel", foundingDate.minusYears(4), countryMap.get("GER")));
        manufacturerService.create(new ManufacturerDto("Skoda", foundingDate.minusYears(5), countryMap.get("CZE")));
        manufacturerService.create(new ManufacturerDto("Tesla", foundingDate.minusYears(6), countryMap.get("USA")));
        manufacturerService.create(new ManufacturerDto("Mercedes-Benz", foundingDate.minusYears(7), countryMap.get("GER")));
        manufacturerService.create(new ManufacturerDto("Mazda", foundingDate.minusYears(8), countryMap.get("JPN")));
        return false;
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
