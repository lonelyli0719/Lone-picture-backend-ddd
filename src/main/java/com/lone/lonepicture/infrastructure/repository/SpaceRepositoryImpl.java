package com.lone.lonepicture.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lone.lonepicture.domain.space.entity.Space;
import com.lone.lonepicture.domain.space.repository.SpaceRepository;
import com.lone.lonepicture.infrastructure.mapper.SpaceMapper;
import org.springframework.stereotype.Service;

/**
 * 空间仓储实现
 */
@Service
public class SpaceRepositoryImpl extends ServiceImpl<SpaceMapper, Space> implements SpaceRepository {
}