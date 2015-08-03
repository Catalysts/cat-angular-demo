package cc.catalysts.angular.demo.dto;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
public class BookDto {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Long id;
    private String name;



}
