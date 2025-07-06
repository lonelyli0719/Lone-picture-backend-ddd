package com.lone.lonepicture.shared.websocket.strategy;

import com.lone.lonepicture.domain.user.entity.User;
import com.lone.lonepicture.shared.websocket.model.PictureEditRequestMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 图片编辑消息处理策略
 */
public interface PictureEditMessageStrategy {

    /**
     * 处理图片编辑请求消息
     *
     * @param requestMessage 图片编辑请求消息
     * @param session        WebSocket会话
     * @param user           用户信息
     * @param pictureId      图片ID
     * @throws Exception 处理异常
     */
    void handle(PictureEditRequestMessage requestMessage, WebSocketSession session, User user, Long pictureId) throws Exception;

}
