package com.btbox.web.controller;

import com.github.linyuzai.connection.loadbalance.core.extension.PathMessage;
import com.github.linyuzai.connection.loadbalance.core.extension.UserMessage;
import com.github.linyuzai.connection.loadbalance.core.message.Message;
import com.github.linyuzai.connection.loadbalance.core.message.ObjectMessage;
import com.github.linyuzai.connection.loadbalance.websocket.concept.WebSocketLoadBalanceConcept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws")
public class WsController {

    @Autowired
    private WebSocketLoadBalanceConcept concept;

    @RequestMapping("/send")
    public void send(@RequestParam String msg) {

        concept.send(msg);
    }


    @RequestMapping("/path")
    public void sendZdy(@RequestParam String pd, @RequestParam String msg) {

        concept.send(new PathMessage(msg, pd));
    }

    @RequestMapping("/user")
    public void user(@RequestParam String pd, @RequestParam String msg) {

        concept.send(new UserMessage(msg, pd));
    }

    @RequestMapping("/pathUser")
    public void pathUser(@RequestParam String pd, @RequestParam String userId, @RequestParam String msg) {

        Message message = new ObjectMessage(msg);
        PathMessage.condition(pd).apply(message);
        UserMessage.condition(userId).apply(message);
        concept.send(message);
    }
}