package com.gx.cloud.admin.server.controller;


import com.gx.cloud.admin.server.constants.ResultCode;
import com.gx.cloud.admin.server.entity.File;
import com.gx.cloud.admin.server.service.FileService;
import com.gx.cloud.admin.server.constants.ResultDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xun.guo
 * @since 2020-01-17
 */
@Slf4j
@RestController
@RequestMapping("/file")
@Api(value = "文件管理", tags = "文件管理")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "多条件查询文件",notes = "多添加查询文件")
    @RequestMapping(value = "/sel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "params",value = "{\"id\":\"文件ID\", \"文件名称\":\"fileName\", \"fileType\":\"文件类型\"}")
    public List<File> getFile(@RequestBody Map<String,Object> params){
        String id = MapUtils.getString(params,"id");
        String fileName = MapUtils.getString(params, "fileName");
        String fileType = MapUtils.getString(params, "fileType");
        return fileService.getFile(id,fileName,fileType);

    }

    @ApiOperation(value = "添加文件",notes = "添加文件")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(File file){
        return fileService.add(file);
    }

    @ApiOperation(value = "删除文件",notes = "删除文件")
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public ResultDao del(String id){
        ResultDao result = new ResultDao();
        log.info("id = {}",id);

        result.setSuccess(false);
        result.setResultCode(3);
        result.setResult(fileService.del(id));
        return result;
    }

}

