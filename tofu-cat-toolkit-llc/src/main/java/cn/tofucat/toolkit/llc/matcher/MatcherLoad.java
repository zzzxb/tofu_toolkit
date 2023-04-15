package cn.tofucat.toolkit.llc.matcher;

import cn.tofucat.toolkit.common.load.LoadClassAbstract;
import cn.tofucat.toolkit.common.annotation.Register;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class MatcherLoad extends LoadClassAbstract {
    public final static MatcherLoad INSTANCE = new MatcherLoad();

    private MatcherLoad() {}

    @Override
    public void load(String pkg) {
        log.info("scan package: {}", pkg);
        try {
            super.load(pkg);
        }catch (RuntimeException e) {
            log.warn(e.getMessage());
        }
    }

    /**
     * 加载对应类，被注解的类
     * @param classPath cn.tofucat.toolkit.llc.matcher.abc.class
     * @return 返回对应类的实例
     *
     * @see Register
     */
    @Override
    protected Object loadClass(String classPath){
        try {
            Class<?> clazz = Class.forName(classPath);
            Register annotation = clazz.getAnnotation(Register.class);
            if (null != annotation) {
                Object o = clazz.getConstructor().newInstance();
                String[] values = annotation.value();
                for (String value : values) {
                    LLCNodeMatcherAbstract.register(value, (LLCNodeMatcher) o);
                }
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            log.error("load class error: {}", classPath, e);
            throw new RuntimeException(e);
        }
        log.info("load class: {}", classPath);
        return null;
    }
}
