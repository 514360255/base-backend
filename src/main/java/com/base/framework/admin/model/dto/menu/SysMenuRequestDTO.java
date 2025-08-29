package com.base.framework.admin.model.dto.menu;

import com.base.framework.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuRequestDTO extends PageRequest implements Serializable {

    /**
     * 菜单名
     */
    private String name;

}
