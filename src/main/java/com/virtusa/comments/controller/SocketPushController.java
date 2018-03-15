package com.virtusa.comments.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.virtusa.comments.entities.HelloMessage;
import com.virtusa.comments.rest.Greeting;

@Controller
public class SocketPushController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
    @MessageMapping("/publish")
    @SendTo("/topic/comments")
    public String publish() throws Exception {
    	return "done";
    }
    @MessageMapping("/publishuser")
    @SendTo("/topic/users")
    public String publishUser() throws Exception {
    	return "done";
    }
}
