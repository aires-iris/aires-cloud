package com.aires.cloud.common.exception;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-13 15:42
 */
public class AiresException extends Exception{
    private static final long serialVersionUID = -6916154462432027437L;

    public AiresException(String message){
        super(message);
    }
}
