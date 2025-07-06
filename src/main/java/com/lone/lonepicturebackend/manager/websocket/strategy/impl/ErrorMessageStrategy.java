package com.lone.lonepicturebackend.manager.websocket.strategy.impl;

import cn.hutool.json.JSONUtil;
import com.lone.lonepicturebackend.manager.websocket.model.PictureEditRequestMessage;
import com.lone.lonepicturebackend.manager.websocket.model.PictureEditResponseMessage;
import com.lone.lonepicturebackend.manager.websocket.model.enums.PictureEditMessageTypeEnum;
import com.lone.lonepicturebackend.manager.websocket.strategy.PictureEditMessageStrategy;
import com.lone.lonepicturebackend.model.entity.User;
import com.lone.lonepicturebackend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;

/**
 * 消息类型错误处理
 */
@Component
public class ErrorMessageStrategy implements PictureEditMessageStrategy {

    @Resource
    private UserService userService;

    /**
     * 处理图片编辑请求消息
     *
     * @param requestMessage 图片编辑请求消息
     * @param session        WebSocket会话
     * @param user           用户信息
     * @param pictureId      图片ID
     */
    @Override
    public void handle(PictureEditRequestMessage requestMessage, WebSocketSession session, User user, Long pictureId) throws Exception {
        PictureEditResponseMessage responseMessage = new PictureEditResponseMessage();
        responseMessage.setType(PictureEditMessageTypeEnum.ERROR.getValue());
        responseMessage.setMessage("消息类型错误");
        responseMessage.setUser(userService.getUserVO(user));
        session.sendMessage(new TextMessage(JSONUtil.toJsonStr(responseMessage)));
    }

}
