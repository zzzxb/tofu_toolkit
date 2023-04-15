package cn.tofucat.toolkit.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Register {

    /**
     * 注册的key, 多个key用逗号分隔, 例如: {"MD5","SHA1","SHA256"}
     * key 用来匹配对应的处理器, 例如: MD5 -> MD5MatcherHandler
     * key 必须大写 , 因为存放在了 ConcurrentHashMap 中, key 必须是唯一的
     * @return
     */
    String[] value() default {};
}
