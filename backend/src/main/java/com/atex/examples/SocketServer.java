/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atex.examples;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
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
@Stateless
public class SocketServer {

    private static final Logger log = Logger.getLogger(SocketServer.class.getName());

    private static final Set<Session> sessions
            = Collections.synchronizedSet(new HashSet<Session>());

    @Resource
    ManagedExecutorService mes;

    public void allClients(String msg) {
        mes.execute(new Runnable() {

            @Override
            public void run() {
                log.info("Sending");
                synchronized (sessions) {
                    for (Session s : sessions) {
                        if (s.isOpen()) {
                            try {
                                // send the article summary to all the connected clients
                                s.getBasicRemote().sendText("New article up:");
                            } catch (IOException ex) {
                                sessions.remove(s); // Drop broken sessions
                            }
                        }
                    }
                }

            }
        });
    }

    @OnMessage
    public void receiveMessage(String message, Session session) {
        log.log(Level.INFO, "Received : {0}, session:{1}", new Object[]{message, session.getId()});
    }

    @OnOpen
    public void open(Session session) {
        log.log(Level.INFO, "Open session:{0}", session.getId());
        try {
            sessions.add(session);
            session.getBasicRemote().sendText("Welcome! All users to follow.");
        } catch (IOException ex) {
            // Ignore errors due to the client not being reachable.
        }
    }

    @OnClose
    public void close(Session session, CloseReason c) {
        log.log(Level.INFO, "Closing:{0}", session.getId());
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable t) {
    }
}
