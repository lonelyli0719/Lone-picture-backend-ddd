package com.lone.lonepicture.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lone.lonepicture.domain.picture.entity.Picture;
import com.lone.lonepicture.domain.picture.repository.PictureRepository;
import com.lone.lonepicture.infrastructure.mapper.PictureMapper;
import org.springframework.stereotype.Service;

@Service
public class PictureRepositoryImpl extends ServiceImpl<PictureMapper, Picture> implements PictureRepository {
}