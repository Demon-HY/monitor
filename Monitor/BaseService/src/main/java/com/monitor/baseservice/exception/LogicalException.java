package com.monitor.baseservice.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 逻辑错误异常
 * @author monitor
 *
 */
public class LogicalException extends Exception implements Serializable {
    
    private static final long serialVersionUID = -2247331292377127222L;
    
    public String stat;
    public String errMsg;
    public Map<String, Object> reaultMap;
    
    public LogicalException(String stat, String errMsg) {
        super(stat + "\t" + errMsg);
        this.stat = stat;
        this.errMsg = errMsg;
        reaultMap = new HashMap<>();
    }
    
}
