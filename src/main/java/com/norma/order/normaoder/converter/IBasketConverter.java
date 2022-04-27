package com.norma.order.normaoder.converter;

import com.norma.order.normaoder.dto.GetBasketDTO;
import com.norma.order.normaoder.model.Basket;

public interface IBasketConverter {

    GetBasketDTO getBasketConvert(Basket basket);

}
