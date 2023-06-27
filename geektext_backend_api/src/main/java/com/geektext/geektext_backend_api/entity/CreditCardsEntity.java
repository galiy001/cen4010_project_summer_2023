package com.geektext.geektext_backend_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "credit_cards")
public class CreditCardsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long card_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user_id;

    @Column(name = "card_number")
    private int card_number;

    @Column(name = "expiration_date")
    private Date expiration_date;

    @Column(name = "cvv")
    private int cvv;

    public CreditCardsEntity() {}

    public CreditCardsEntity(UserEntity user_id, int card_number, Date expiration_date, int cvv) {
        this.user_id = user_id;
        this.card_number = card_number;
        this.expiration_date = expiration_date;
        this.cvv = cvv;
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public UserEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UserEntity user_id) {
        this.user_id = user_id;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}