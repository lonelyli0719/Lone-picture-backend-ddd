package com.lone.lonepicture.shared.websocket.strategy.impl;

import com.lone.lonepicture.application.service.UserApplicationService;
import com.lone.lonepicture.domain.user.entity.User;
import com.lone.lonepicture.shared.websocket.model.PictureEditRequestMessage;
import com.lone.lonepicture.shared.websocket.model.PictureEditResponseMessage;
import com.lone.lonepicture.shared.websocket.model.enums.PictureEditActionEnum;
import com.lone.lonepicture.shared.websocket.model.enums.PictureEditMessageTypeEnum;
import com.lone.lonepicture.shared.websocket.strategy.PictureEditMessageStrategy;
import com.lone.lonepicture.shared.websocket.util.PictureEditBroadcaster;
import com.lone.lonepicture.shared.websocket.util.PictureEditingStatusManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;

/**
 * 执行编辑操作
 */
@Component
public class EditActionMessageStrategy implements PictureEditMessageStrategy {

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
        Long editingUserId = statusManager.getEditingUser(pictureId);
        String editAction = requestMessage.getEditAction();
        PictureEditActionEnum actionEnum = PictureEditActionEnum.getEnumByValue(editAction);
        if (actionEnum == null) {
            return;
        }
        // 确认是当前编辑者
        if (editingUserId != null && editingUserId.equals(user.getId())) {
            PictureEditResponseMessage responseMessage = new PictureEditResponseMessage();
            responseMessage.setType(PictureEditMessageTypeEnum.EDIT_ACTION.getValue());
            String message = String.format("%s执行%s", user.getUserName(), actionEnum.getText());
            responseMessage.setMessage(message);
            responseMessage.setEditAction(editAction);
            responseMessage.setUser(userApplicationService.getUserVO(user));
            // 广播给除了当前客户端之外的其他用户，否则会造成重复编辑
            broadcaster.broadcastToPicture(pictureId, responseMessage, session);
        }
    }

}
