/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.examples;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author anders
 */
@ServerEndpoint("/activeusers")
public class SocketServer {

    private static final Logger log = Logger.getLogger(SocketServer.class.getName());

    @OnMessage
    public void receiveMessage(String message, Session session) {
        log.log(Level.INFO, "Received : {0}, session:{1}", new Object[]{message, session.getId()});
    }

    @OnOpen
    public void open(Session session) {
        log.log(Level.INFO, "Open session:{0}", session.getId());
    }

    @OnClose
    public void close(Session session, CloseReason c) {
        log.log(Level.INFO, "Closing:{0}", session.getId());
    }

    @OnError
    public void onError(Throwable t) {
    }
}
