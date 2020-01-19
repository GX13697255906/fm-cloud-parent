package com.gx.cloud.admin.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author xun.guo
 * @since 2020-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="File对象", description="")
public class File implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String fName;

    @ApiModelProperty(value = "文件类型")
    private String fType;

    @ApiModelProperty(value = "用户id")
    private String userid;

    @ApiModelProperty(value = "文件描述")
    private String description;

    @ApiModelProperty(value = "文件路径")
    private String fPath;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updateTime;


}
