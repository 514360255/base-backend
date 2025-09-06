package com.base.framework.admin.model.dto.dict;

import com.base.framework.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/9/4
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DictQueryDTO extends PageRequest implements Serializable {

    /**
     * 字典名
     */
    private String name;

    /**
     * 上级id
     */
    private Long parentId;

    /**
     * 状态
     */
    private Long isActive;

}
