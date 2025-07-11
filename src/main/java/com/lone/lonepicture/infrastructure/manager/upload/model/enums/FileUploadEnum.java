package com.lone.lonepicture.infrastructure.manager.upload.model.enums;

import com.lone.lonepicture.infrastructure.exception.BusinessException;
import com.lone.lonepicture.infrastructure.exception.ErrorCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
public enum FileUploadEnum {
    FILE("file"),

    URL("url");

    private final String type;

    FileUploadEnum(String type) {
        this.type = type;
    }

    public static String getType(@NonNull String type) {
        for (FileUploadEnum fileUploadEnum : FileUploadEnum.values()) {
            if (fileUploadEnum.getType().equals(type)) {
                return fileUploadEnum.getType();
            }
        }
        throw new BusinessException(ErrorCode.PARAMS_ERROR, "不支持的上传类型");
    }
}


