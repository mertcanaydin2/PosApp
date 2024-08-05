package com.greedz.posApp.core.crossCuttingConcerns.exceptionHandling;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

public BusinessException(String message){
    super(message);
}
}
