package com.lone.lonepicture.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lone.lonepicture.domain.space.entity.SpaceUser;
import com.lone.lonepicture.domain.space.repository.SpaceUserRepository;
import com.lone.lonepicture.infrastructure.mapper.SpaceUserMapper;
import org.springframework.stereotype.Service;

/**
 * 空间用户仓储实现
 */
@Service
public class SpaceUserRepositoryImpl extends ServiceImpl<SpaceUserMapper, SpaceUser> implements SpaceUserRepository {
}