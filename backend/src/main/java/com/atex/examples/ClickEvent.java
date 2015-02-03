/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atex.examples;

import java.io.Serializable;

/**
 *
 * @author anders
 */
public class ClickEvent implements Serializable {
    
    private String msg;
    
    public ClickEvent() {
        msg = "";
    }
    
    public ClickEvent(String msg) {
        this.msg = msg;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ClickEvent{" + "msg=" + msg + '}';
    }
    
}
