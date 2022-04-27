package com.norma.order.normaoder.dto;

import com.norma.order.normaoder.model.Gender;
import com.norma.order.normaoder.model.Membership;

public record GetCustomerAllDTO(String username,
                                String email,
                                Long identity,
                                Gender gender,
                                Membership membership,
                                CustomerAddressDTO customerAddressDTO) {
}