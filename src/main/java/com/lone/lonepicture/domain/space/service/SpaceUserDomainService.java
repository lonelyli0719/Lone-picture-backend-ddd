package com.lone.lonepicture.domain.space.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lone.lonepicture.domain.space.entity.SpaceUser;
import com.lone.lonepicture.interfaces.dto.spaceuser.SpaceUserQueryRequest;

public interface SpaceUserDomainService {

    /**
     * 获取查询对象
     *
     * @param spaceUserQueryRequest
     * @return
     */
    QueryWrapper<SpaceUser> getQueryWrapper(SpaceUserQueryRequest spaceUserQueryRequest);
}