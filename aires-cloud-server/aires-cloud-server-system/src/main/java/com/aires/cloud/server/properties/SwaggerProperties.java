package com.aires.cloud.server.properties;

import lombok.Data;

/**
 * @program: cloud-project
 * @description: swagger配置信息
 * @author: fan zhengxiang
 * @create: 2020-10-13 19:48
 */
@Data
public class SwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;
    private String grantUrl;
    private String name;
    private String scope;
}