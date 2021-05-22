package cn.ityihang.zblog.system.controller;

import cn.ityihang.zblog.system.entity.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 点对点消息发送
     * @param chatMsg
     */
    @MessageMapping("/ws/queue")
    public void handleMsg(ChatMsg chatMsg) {
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }

    /**
     * 消息订阅
     * @param msg
     * @return
     */
    @MessageMapping("/ws/topic")
    @SendTo("/topic/chat")
    public ChatMsg sendMsg(ChatMsg msg) {
        return msg;
    }
}
