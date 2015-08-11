package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
public class BookDto extends NamedDto<Long> {

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
