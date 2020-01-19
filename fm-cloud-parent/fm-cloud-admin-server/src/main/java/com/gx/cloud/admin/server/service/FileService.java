package com.gx.cloud.admin.server.service;

import com.gx.cloud.admin.server.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xun.guo
 * @since 2020-01-17
 */
public interface FileService extends IService<File> {

    List<File> getFile(String id, String fileName, String fileType);

    String add(File file);

    String del(String id);

}
