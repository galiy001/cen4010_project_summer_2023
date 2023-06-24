package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="publishers")

public class PublisherEntity {

    @Column(name="publisher_id")
    private int publisher_id;

    @Column(name="publisher_name")
    private String publisher_name;


    public PublisherEntity(int publisher_id,String publisher_name){
        this.publisher_id=publisher_id;
        this.publisher_name=publisher_name;
    }


    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }
}
