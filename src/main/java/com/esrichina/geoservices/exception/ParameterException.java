package com.esrichina.geoservices.exception;

/**
 * 参数异常处理类
 *
 * @author: LOONGER CHEN
 * @date: 2020-05-29
 * @version: 1.0.0
 */
public class ParameterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException() {
    }

}
