package com.gx.cloud.admin.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gx.cloud.admin.server.constants.ConstantParameter;
import com.gx.cloud.admin.server.entity.File;
import com.gx.cloud.admin.server.mapper.FileMapper;
import com.gx.cloud.admin.server.service.FileService;
import com.gx.cloud.common.util.UtilId;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xun.guo
 * @since 2020-01-17
 */
@Service
@Slf4j
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Override
    public List<File> getFile(String id, String fileName, String fileType) {
        log.info("多条件查询文件");
        QueryWrapper<File> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(StringUtils.isNotBlank(id), File::getId, id)
                .like(StringUtils.isNotBlank(fileName), File::getFName, fileName)
                .like(StringUtils.isNotBlank(fileType), File::getDescription, fileType);
        return this.list(queryWrapper);
    }

    @Override
    @Transactional
    public String add(File file) {

        file.setId(UtilId.UUID());


        file.setCreateTime(new Date());

        boolean result = this.save(file);
        if(result == true){
            return ConstantParameter.SUCCESS;
        }else {
            return ConstantParameter.FAIL;
        }
    }

    @Override
    @Transactional
    public String del(String id) {
        boolean result = this.removeById(id);
        if(result == true){
            return ConstantParameter.SUCCESS;
        }else {
            return ConstantParameter.FAIL;
        }
    }
}
