package com.lone.lonepicture.shared.websocket.strategy.impl;

import com.lone.lonepicture.application.service.UserApplicationService;
import com.lone.lonepicture.domain.user.entity.User;
import com.lone.lonepicture.shared.websocket.model.PictureEditRequestMessage;
import com.lone.lonepicture.shared.websocket.model.PictureEditResponseMessage;
import com.lone.lonepicture.shared.websocket.model.enums.PictureEditMessageTypeEnum;
import com.lone.lonepicture.shared.websocket.strategy.PictureEditMessageStrategy;
import com.lone.lonepicture.shared.websocket.util.PictureEditBroadcaster;
import com.lone.lonepicture.shared.websocket.util.PictureEditingStatusManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;

/**
 * 进入编辑图片状态
 */
@Component
public class EnterEditMessageStrategy implements PictureEditMessageStrategy {

    @Resource
    private UserApplicationService userApplicationService;

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
        // 没有用户正在编辑该图片，才能进入编辑
        if (!statusManager.isBeingEdited(pictureId)) {
            // 设置当前用户为编辑用户
            statusManager.setEditingUser(pictureId, user.getId());
            PictureEditResponseMessage responseMessage = new PictureEditResponseMessage();
            responseMessage.setType(PictureEditMessageTypeEnum.ENTER_EDIT.getValue());
            String message = String.format("%s开始编辑图片", user.getUserName());
            responseMessage.setMessage(message);
            responseMessage.setUser(userApplicationService.getUserVO(user));
            broadcaster.broadcastToPicture(pictureId, responseMessage);
        }
    }

}
