package com.atex.examples;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

// For later. Register client encoders and decoders for JSON message format
// @ClientEndpoint( encoders = { MessageEncoder.class }, decoders = { MessageDecoder.class } )
@ClientEndpoint
@Singleton
public class ClickStreamListener {

    private static final Logger log = Logger.getLogger(ClickStreamListener.class.getName());
    private Session mySession;
    private long messageCount = 0;

//    @Inject
//    private Event<ClickEvent> msgEvent;
    @Inject
    private ANormalPojo pojo;

    public Session getMySession() {
        return mySession;
    }

    public void setMySession(Session mySession) {
        this.mySession = mySession;
    }

    public ANormalPojo getPojo() {
        return pojo;
    }

    public void setPojo(ANormalPojo pojo) {
        this.pojo = pojo;
    }

    @OnOpen
    public void onOpen(final Session session) throws IOException, EncodeException {
        log.info("Socket client got 'open' event");
        mySession = session;
    }

    @OnMessage
    public void onMessage(final String message) {
        messageCount++;
        log.finest(String.format("Received message '%s'", message));
        log.log(Level.INFO, "TOTAL MESSAGES RECEVIED: {0}", messageCount);
        if (pojo == null) {
            log.log(Level.INFO, "POJO IS NULL");
        } else {
            log.log(Level.INFO, "POJO HASH {0}", pojo.hashCode());
        }

//        if (msgEvent == null) {
//            log.info("msgEvent IS NULL");
//        } else {
//            msgEvent.fire(new ClickEvent("Hello-"+messageCount));
//        }
    }

    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.mySession = null;
        if (reason != null) {
            log.log(Level.INFO, "Session closed. Reason: {0}", reason.getReasonPhrase());
        } else {
            log.log(Level.INFO, "Session closed.");
        }
    }

    @OnError
    public void onError(Session session, Throwable t) {
        log.log(Level.SEVERE, "ERROR TERROR: {0}", t.getMessage());
    }

    public long getMessageCount() {
        return messageCount;
    }

}
