package com.lone.lonepicture.shared.websocket.strategy;

import com.lone.lonepicture.shared.websocket.model.enums.PictureEditMessageTypeEnum;
import com.lone.lonepicture.shared.websocket.strategy.impl.EditActionMessageStrategy;
import com.lone.lonepicture.shared.websocket.strategy.impl.EnterEditMessageStrategy;
import com.lone.lonepicture.shared.websocket.strategy.impl.ErrorMessageStrategy;
import com.lone.lonepicture.shared.websocket.strategy.impl.ExitEditMessageStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息处理策略工厂
 */
@Component
public class PictureEditMessageStrategyFactory {

    private final Map<String, PictureEditMessageStrategy> strategies = new HashMap<>();

    @Resource
    private EnterEditMessageStrategy enterEditMessageStrategy;

    @Resource
    private EditActionMessageStrategy editActionMessageStrategy;

    @Resource
    private ExitEditMessageStrategy exitEditMessageStrategy;

    @Resource
    private ErrorMessageStrategy errorMessageStrategy;

    @PostConstruct
    public void init() {
        strategies.put(PictureEditMessageTypeEnum.ENTER_EDIT.getValue(), enterEditMessageStrategy);
        strategies.put(PictureEditMessageTypeEnum.EDIT_ACTION.getValue(), editActionMessageStrategy);
        strategies.put(PictureEditMessageTypeEnum.EXIT_EDIT.getValue(), exitEditMessageStrategy);
    }

    /**
     * 获取消息处理策略
     *
     * @param type 消息类型
     * @return 消息处理策略
     */
    public PictureEditMessageStrategy getStrategy(String type) {
        return strategies.getOrDefault(type, errorMessageStrategy);
    }

}
