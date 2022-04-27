package com.norma.order.normaoder.converter;

import com.norma.order.normaoder.dto.GetBasketDTO;
import com.norma.order.normaoder.model.Basket;
import org.springframework.stereotype.Component;



@Component
public class BasketConverter implements IBasketConverter{

    @Override
    public GetBasketDTO getBasketConvert(Basket basket) {
        return new GetBasketDTO(basket.getPrice(),
                basket.getDiscountPrice(),
                basket.getTaxPrice(),
                basket.getShippingPrice(),
                basket.getTotalPrice(),
                basket.getItems());
    }
}
