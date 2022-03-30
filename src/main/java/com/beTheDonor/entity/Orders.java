package com.beTheDonor.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;
    @PrePersist
    private void onCreate() {
        timestamp = new Date();
    }
    @ManyToOne
    @JoinColumn(name="userId")
    private ApplicationUser userId;
    @Column
    private Double total;
    @Column
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name="addressId")
    private DeliveryAddress deliveryAddressId;

    public DeliveryAddress getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(DeliveryAddress deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String order_status) {
        this.orderStatus = order_status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
