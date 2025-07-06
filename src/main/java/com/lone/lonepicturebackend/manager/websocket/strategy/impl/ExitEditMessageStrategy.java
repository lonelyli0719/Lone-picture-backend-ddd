package com.lone.lonepicturebackend.manager.websocket.strategy.impl;

import com.lone.lonepicturebackend.manager.websocket.model.PictureEditRequestMessage;
import com.lone.lonepicturebackend.manager.websocket.model.PictureEditResponseMessage;
import com.lone.lonepicturebackend.manager.websocket.model.enums.PictureEditMessageTypeEnum;
import com.lone.lonepicturebackend.manager.websocket.strategy.PictureEditMessageStrategy;
import com.lone.lonepicturebackend.manager.websocket.util.PictureEditBroadcaster;
import com.lone.lonepicturebackend.manager.websocket.util.PictureEditingStatusManager;
import com.lone.lonepicturebackend.model.entity.User;
import com.lone.lonepicturebackend.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;

/**
 * 退出编辑图片状态
 */
@Component
public class ExitEditMessageStrategy implements PictureEditMessageStrategy {

    @Resource
    private UserService userService;

    @Resource
    private PictureEditBroadcaster broadcaster;

    @Resource
    private PictureEditingStatusManager statusManager;

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
        Long editingUserId = statusManager.getEditingUser(pictureId);
        if (editingUserId != null && editingUserId.equals(user.getId())) {
            // 移除当前用户的编辑状态
            statusManager.removeEditingUser(pictureId);
            // 构造响应，发送退出编辑的消息通知
            PictureEditResponseMessage responseMessage = new PictureEditResponseMessage();
            responseMessage.setType(PictureEditMessageTypeEnum.EXIT_EDIT.getValue());
            String message = String.format("%s退出编辑图片", user.getUserName());
            responseMessage.setMessage(message);
            responseMessage.setUser(userService.getUserVO(user));
            broadcaster.broadcastToPicture(pictureId, responseMessage);
        }
    }

}
