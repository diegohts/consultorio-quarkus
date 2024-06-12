package org.quarkusclub;

public class BusinessException extends Exception{

    public BusinessException(String message) {
        super(message);
    }

}
