/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.atex.examples;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;

/**
 *
 * @author anders
 */
public class ANormalPojo {
    private static final Logger LOG = Logger.getLogger(ANormalPojo.class.getName());
    private String me = "A normal Pojo";
    
    public ANormalPojo() {
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }
    
    @PostConstruct
    public void init() {
        LOG.info("POJO CREATED ===========================");
    }
    
}
