package net.dubbo.spring.boot.annotation;

import java.lang.annotation.*;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DubboConsumer {

    /**
     * @return 版本
     */
    String version() default "";

    /**
     * @return 远程调用超时时间
     */
    int timeout() default 0;

    /**
     * @return 注册中心
     */
    String registry() default "";

    /**
     * @return 服务分组
     */
    String group() default "";

    /**
     * @return 客户端类型
     */
    String client() default "";

    /**
     * @return 点对点直连服务提供地址
     */
    String url() default "";

    /**
     * @return 协议
     */
    String protocol() default "";

    /**
     * @return 检查服务提供者是否存在
     */
    boolean check() default true;

    /**
     * @return
     */
    boolean lazy() default false;

    /**
     * @return 重试次数
     */
    int retries() default 0;

    /**
     * @return 最大并发调用数
     */
    int actives() default 0;

    /**
     * @return 负载均衡
     */
    String loadbalance() default "";

    /**
     * @return 是否异步
     */
    boolean async() default false;

    /**
     * @return 异步发送是否等待发送成功
     */
    boolean sent() default false;


}
