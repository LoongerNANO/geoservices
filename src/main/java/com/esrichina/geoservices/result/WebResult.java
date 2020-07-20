package com.esrichina.geoservices.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回格式：泛型格式
 *
 * @param <T>
 */
@Data
public class WebResult<T> {

    Integer code;
    String retval;
    T data;

    public WebResult(Integer code, String retval) {
        super();
        this.code = code;
        this.retval = retval;
    }

    public WebResult(Integer code, String retval, T data) {
        super();
        this.code = code;
        this.retval = retval;
        this.data = data;
    }

    public Map<String, Object> result() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", this.code);
        map.put("retval", this.retval);
        if (this.data != null) {
            map.put("data", this.data);
        }
        return map;
    }
}
