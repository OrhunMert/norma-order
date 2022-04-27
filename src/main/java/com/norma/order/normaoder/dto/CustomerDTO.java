package com.norma.order.normaoder.dto;

import com.norma.order.normaoder.model.Gender;
import com.norma.order.normaoder.model.Membership;

public record CustomerDTO(String userName, String email, Long identity, Gender gender, String password,
                          Membership membership,
                          CustomerAddressDTO customerAddress) {

}
