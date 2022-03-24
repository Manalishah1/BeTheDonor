package com.example.Be_The_Donor.entity;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name="userId")
    private ApplicationUser userId;
    @Column
    private Double total;
    @ManyToOne
    @JoinColumn(name="deliveryAdressId")
    private DeliveryAddress deliveryAddressId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ApplicationUser getUserId() {
        return userId;
    }

    public void setUserId(ApplicationUser userId) {
        this.userId = userId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double totalPrice) {
        total = totalPrice;
    }
}
