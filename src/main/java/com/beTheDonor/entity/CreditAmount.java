package com.beTheDonor.entity;

import javax.persistence.*;

@Entity
public class CreditAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "double default 0")
    private Double creditAmount;
    @Column()
    private Double riderTipPercent;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRiderTipPercent() {
        return riderTipPercent;
    }

    public void setRiderTipPercent(Double riderTip) {
        this.riderTipPercent = riderTip;
    }
}
