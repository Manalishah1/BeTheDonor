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
    private Date orderPlacedOn;
    @PrePersist
    private void onCreate() {
        orderPlacedOn = new Date();
    }
    @Column
    private Date orderPaidOn;
    @Column
    private Date orderDeliveredOn;
    @ManyToOne
    @JoinColumn(name="userId")
    private ApplicationUser userId;
    @Column
    private Double total;
    @Column
    private String orderStatus;
    @Column
    private Double riderTip;
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

    public Date getOrderPlacedOn() {
        return orderPlacedOn;
    }

    public void setOrderPlacedOn(Date timestamp) {
        this.orderPlacedOn = timestamp;
    }

    public Date getOrderPaidOn() {
        return orderPaidOn;
    }

    public void setOrderPaidOn(Date orderPaidOn) {
        this.orderPaidOn = orderPaidOn;
    }

    public Date getOrderDeliveredOn() {
        return orderDeliveredOn;
    }

    public void setOrderDeliveredOn(Date orderDeliveredOn) {
        this.orderDeliveredOn = orderDeliveredOn;
    }

    public Double getRiderTip() {
        return riderTip;
    }

    public void setRiderTip(Double riderTip) {
        this.riderTip = riderTip;
    }
}
