package com.beTheDonor.entity;

import com.beTheDonor.model.PatientRiderModel;

import javax.persistence.*;

@NamedNativeQuery(name = "Orders.getByCityName",
query = " select  o.order_id, p.product_name,oi.quantity,au.firstname,da.address,da.city,da.postal_code,au.phone_number from product p " +
        "inner join order_item oi on p.product_id = oi.product_id " +
        "inner join orders o on oi.order_id = o.order_id " +
        "inner join delivery_address da on o.address_id = da.address_id " +
        "inner join application_user au on o.user_id = au.id " +
        "where city = ?1 ",
resultSetMapping = "PatientRiderModel")
@SqlResultSetMapping(name = "PatientRiderModel",classes = @ConstructorResult(targetClass = PatientRiderModel.class,columns = { @ColumnResult(name = "order_id"),@ColumnResult(name = "product_name"),@ColumnResult(name = "quantity"),@ColumnResult(name = "firstname"),@ColumnResult(name = "address"),@ColumnResult(name = "city"),@ColumnResult(name = "postal_code"),@ColumnResult(name = "phone_number")}))
@Entity
public class Orders {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name="user_id")
    private ApplicationUser userId;

    @Column
    private Double total;

    @ManyToOne
    @JoinColumn(name="address_id")
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
}
