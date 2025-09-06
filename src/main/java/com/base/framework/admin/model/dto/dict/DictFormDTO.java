package com.base.framework.admin.model.dto.dict;

import com.base.framework.admin.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: 郭郭
 * @Create: 2025/9/3
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class DictFormDTO extends BaseDTO implements Serializable {

    /**
     * 字典名
     */
    private String name;

    /**
     * 上级
     */
    private Long parentId;

    /**
     * 排序
     */
    private int sortOrder;

    /**
     * 备注
     */
    private String remark;

}
