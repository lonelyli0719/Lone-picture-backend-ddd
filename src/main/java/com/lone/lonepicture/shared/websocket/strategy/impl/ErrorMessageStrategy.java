package com.lone.lonepicture.shared.websocket.strategy.impl;

import cn.hutool.json.JSONUtil;
import com.lone.lonepicture.application.service.UserApplicationService;
import com.lone.lonepicture.domain.user.entity.User;
import com.lone.lonepicture.shared.websocket.model.PictureEditRequestMessage;
import com.lone.lonepicture.shared.websocket.model.PictureEditResponseMessage;
import com.lone.lonepicture.shared.websocket.model.enums.PictureEditMessageTypeEnum;
import com.lone.lonepicture.shared.websocket.strategy.PictureEditMessageStrategy;
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
    private UserApplicationService userApplicationService;

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
        responseMessage.setUser(userApplicationService.getUserVO(user));
        session.sendMessage(new TextMessage(JSONUtil.toJsonStr(responseMessage)));
    }

}
