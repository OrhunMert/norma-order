package com.norma.order.normaoder.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class Brand extends BaseModel {

    private String name;

}
