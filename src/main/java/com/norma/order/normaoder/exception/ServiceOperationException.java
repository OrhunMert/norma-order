package com.norma.order.normaoder.exception;


public final class ServiceOperationException {

    private ServiceOperationException() {
    }

    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductNotFoundException extends BaseException{
        public ProductNotFoundException(String message){
            super(message);
        }
    }
}
