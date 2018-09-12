package com.generator.utils;

/**
 * 接口方法的抽象属性
 *
 * @author zhangzhidong
 * @date 2018/9/11
 */
public class ApiAbstract {

    /**
     * 请求路径
     */
    String url;

    /**
     * 请求类型
     */
    String methodType;

    /**
     * 接口方法名称
     */
    String methodName;

    /**
     * 方法介绍
     */
    String methodDesc;

    /**
     * 请求参数注解
     */
    String requestAnnotation;

    String methodUpperCase;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public String getRequestAnnotation() {
        return requestAnnotation;
    }

    public void setRequestAnnotation(String requestAnnotation) {
        this.requestAnnotation = requestAnnotation;
    }

    public String getMethodUpperCase() {
        return methodUpperCase;
    }

    public void setMethodUpperCase(String methodUpperCase) {
        this.methodUpperCase = methodUpperCase;
    }
}
