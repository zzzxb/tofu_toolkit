package cn.tofucat.toolkit.llc.load;

import cn.tofucat.toolkit.llc.annotation.Register;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcher;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcherAbstract;

import java.lang.reflect.InvocationTargetException;

public class MatcherLoad extends LoadClassAbstract {

    public MatcherLoad() {
        load("cn.tofucat.toolkit.llc.matcher");
    }

    @Override
    public Object loadClass(String classPath){
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
            throw new RuntimeException(e);
        }
        return null;
    }
}
