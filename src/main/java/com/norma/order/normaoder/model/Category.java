package com.norma.order.normaoder.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Category extends BaseModel {

    @OneToOne
    private Category parent;

    private String name;
}
