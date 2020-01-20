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

/**
 * <p>
 * 
 * </p>
 *
 * @author xun.guo
 * @since 2020-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GatewayRoute对象", description="")
public class GatewayRoute implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "route_id", type = IdType.ID_WORKER)
    private String routeId;

    private String routeName;

    private String path;

    private String serviceId;

    private Integer stripPrefix;

    private String url;

    private String routeDesc;

    private Date createTime;

    private Date updateTime;


}
