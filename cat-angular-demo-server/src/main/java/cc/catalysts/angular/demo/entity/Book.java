package cc.catalysts.angular.demo.entity;

import cc.catalysts.angular.spring.entity.AbstractNamed;

import javax.persistence.Entity;

@Entity
public class Book extends AbstractNamed<Long> {

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
