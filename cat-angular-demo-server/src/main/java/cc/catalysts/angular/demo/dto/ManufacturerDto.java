package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
public class ManufacturerDto extends NamedDto<Long> {

    public ManufacturerDto() {
    }

    public ManufacturerDto(String name) {
        setName(name);
    }

}
