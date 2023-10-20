package com.restwithspring.model.vo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class BookDTO {

    private Long id;
    private String title;
    private String author;
    private Double price;
    private LocalDate launch_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(LocalDate launch_date) {
        this.launch_date = launch_date;
    }
}
