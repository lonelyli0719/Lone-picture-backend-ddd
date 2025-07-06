package com.lone.lonepicture.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lone.lonepicture.domain.user.entity.User;
import com.lone.lonepicture.domain.user.repository.UserRepository;
import com.lone.lonepicture.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户仓储实现
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<UserMapper, User> implements UserRepository {
}
