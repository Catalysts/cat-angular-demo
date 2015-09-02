package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

/**
 * Created by Michael Mittermayr on 01.09.2015.
 */
public class CountryDto extends NamedDto<Long> {

    private boolean disabled;

    private String code;

    public CountryDto() {
    }

    public CountryDto(String name, String code) {
        this(name, code, false);
    }

    public CountryDto(String name, String code, boolean disabled) {
        this.disabled = disabled;
        this.code = code;
        setName(name);
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
