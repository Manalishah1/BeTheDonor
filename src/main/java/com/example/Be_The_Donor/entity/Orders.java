package com.example.Be_The_Donor.entity;

import com.example.Be_The_Donor.dto.PatientRiderDto;

import javax.persistence.*;

@NamedNativeQuery(name = "Orders.getByCityName",
query = " select firstname,address,city,postal_code,phone_number from orders o " +
        "             inner join delivery_address da on o.address_id = da.address_id " +
        "            inner join application_user au on o.user_id = au.id " +
        "             where city = ?1 ",
resultSetMapping = "Mapping.PatientRiderDto")
@SqlResultSetMapping(name = "Mapping.PatientRiderDto",classes = @ConstructorResult(targetClass = PatientRiderDto.class,columns = {@ColumnResult(name = "firstname"),@ColumnResult(name = "address"),@ColumnResult(name = "city"),@ColumnResult(name = "postal_code"),@ColumnResult(name = "phone_number")}))
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
