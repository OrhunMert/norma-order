package com.norma.order.normaoder.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Customer extends BaseExtendedModel {

    private String username;
    private String email;
    private Long identity;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String password;

    @Enumerated(EnumType.STRING)
    private Membership membership;

    //we created customerId in customerAdress Table.
    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private CustomerAddress customerAddress;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Basket basket;

}
