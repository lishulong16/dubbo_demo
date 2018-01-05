package net.dubbo.spring.boot.annotation;

import java.lang.annotation.*;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/29
 * @doing
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableDubboConfiguration {
}
