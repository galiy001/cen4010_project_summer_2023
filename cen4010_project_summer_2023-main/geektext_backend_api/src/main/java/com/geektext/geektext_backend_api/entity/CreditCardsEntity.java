package com.geektext.geektext_backend_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="credit_cards")
public class CreditCardsEntity {
    @Column(name="card_id")
    private int card_id;
    @Column(name="user_id")
    private int user_id;
    @Column(name="card_number")
    private int card_number;
    @Column(name="expiration_date")
    private Date expiration_date;
    @Column(name="cvv")
    private int cvv;



    public CreditCardsEntity(int card_id,int user_id,int card_number,Date expiration_date,int cvv){
        this.card_id=card_id;
        this.user_id=user_id;
        this.card_number=card_number;
        this.expiration_date=expiration_date;
        this.cvv=cvv;
    }

    public int getCard_id() {
        return card_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getCard_number() {
        return card_number;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public int getCvv() {
        return cvv;
    }


    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setCard_number(int card_number){
        this.card_number=card_number;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
