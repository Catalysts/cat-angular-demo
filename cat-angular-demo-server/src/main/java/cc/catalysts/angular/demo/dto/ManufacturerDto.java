package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

import java.time.LocalDate;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
public class ManufacturerDto extends NamedDto<Long> {

    private LocalDate foundingDate;
    private CountryDto country;

    public ManufacturerDto() {
    }

    public ManufacturerDto(String name,LocalDate foundingDate, CountryDto countryDto) {
        setName(name);
        setFoundingDate(foundingDate);
        setCountry(countryDto);
    }

    public void setFoundingDate(LocalDate foundingDate){
        this.foundingDate = foundingDate ;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }
}
