package com.aires.cloud.common.constant;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:39
 */
public class AiresConstant {



    /**
     * Zuul请求头TOKEN名称（不要有空格）
     */
    public static final String ZUUL_TOKEN_HEADER = "ZuulToken";
    /**
     * Zuul请求头TOKEN值
     */
    public static final String ZUUL_TOKEN_VALUE = "aires:zuul:5566";

    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "aires.captcha.";


    /**
     * 排序规则：降序
     */
    public static String ORDER_DESC = "descending";
    /**
     * 排序规则：升序
     */
    public static String ORDER_ASC = "ascending";

    /**
     * Gateway请求头TOKEN名称（不要有空格）
     */
    public static String GATEWAY_TOKEN_HEADER = "GatewayToken";
    /**
     * Gateway请求头TOKEN值
     */
    public static String GATEWAY_TOKEN_VALUE = "febs:gateway:123456";

    /**
     * 允许下载的文件类型，根据需求自己添加（小写）
     */
    public static String[] VALID_FILE_TYPE = {"xlsx", "zip"};



    /**
     * 异步线程池名称
     */
    public static String ASYNC_POOL = "febsAsyncThreadPool";

    /**
     * OAUTH2 令牌类型 https://oauth.net/2/bearer-tokens/
     */
    public static String OAUTH2_TOKEN_TYPE = "bearer";
    /**
     * Java默认临时目录
     */
    public static String JAVA_TEMP_DIR = "java.io.tmpdir";
    /**
     * utf-8
     */
    public static String UTF8 = "utf-8";
    /**
     * 注册用户角色ID
     */
    public static Long REGISTER_ROLE_ID = 2L;

    public static String LOCALHOST = "localhost";
    public static String LOCALHOST_IP = "127.0.0.1";


}