package cn.ityihang.zblog.system.controller;

import cn.ityihang.zblog.system.entity.ChatMsg;
import cn.ityihang.zblog.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMsg(ChatMsg chatMsg) {
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        chatMsg.setFrom(currentUser.getUsername());
        chatMsg.setFromNickname(currentUser.getRealname());
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
}
