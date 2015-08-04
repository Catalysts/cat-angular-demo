package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

import java.time.LocalDate;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
public class ManufacturerDto extends NamedDto<Long> {

    private LocalDate foundingDate;
    private String countryCode;

    public ManufacturerDto() {
    }

    public ManufacturerDto(String name,LocalDate foundingDate, String countryCode) {
        setName(name);
        setFoundingDate(foundingDate);
        setCountryCode(countryCode);
    }

    public void setFoundingDate(LocalDate foundingDate){
        this.foundingDate = foundingDate ;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getFoundingDate() {
        return foundingDate;
    }
}
