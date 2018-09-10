package com.generator.form;

/**
 * @author zhangzhidong
 * @date 2018/7/16
 */
public class ApiRequestVo {
    String field;
    String type;
    Integer isNullable;
    String desc;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Integer isNullable) {
        this.isNullable = isNullable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
