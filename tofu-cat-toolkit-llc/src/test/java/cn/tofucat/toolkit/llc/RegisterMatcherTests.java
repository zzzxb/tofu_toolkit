package cn.tofucat.toolkit.llc;

import cn.tofucat.toolkit.llc.annotation.Register;
import cn.tofucat.toolkit.llc.matcher.DefaultNodeMatcher;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcher;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcherAbstract;
import cn.tofucat.toolkit.llc.parser.DefaultLLCParser;
import cn.tofucat.toolkit.llc.parser.LLCNode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RegisterMatcherTests {

    @Test
    public void register() {
        scanClass(resourcePath());
        DefaultLLCParser defaultLLCParser = new DefaultLLCParser();
        LLCNode parser = defaultLLCParser.parser("{MD5[{BASE64[内容]}]}");

        DefaultNodeMatcher defaultNodeMatcher = new DefaultNodeMatcher();
        String convert = defaultNodeMatcher.convert(parser);
        System.out.println(convert);
    }

    public String resourcePath() {
        return Objects.requireNonNull(Thread
                        .currentThread()
                        .getContextClassLoader()
                        .getResource("cn.tofucat.toolkit.llc.matcher".replace(".", "/")))
                .getFile();
    }

    public File[] scanClass(String path) {
        List<String> list = new LinkedList<>();
        File file = new File(path);
        if (file.isDirectory()) {
            return file.listFiles(new RegisterFilter());
        }
        return null;
    }
}

class RegisterFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        String filepath = "cn.tofucat.toolkit.llc.matcher."+ pathname.getName().replace(".class", "").replace("/", ".");
        try {
            Class<?> clazz = Class.forName(filepath);
            Register annotation = clazz.getAnnotation(Register.class);
            if (null != annotation) {
                Object o = clazz.getConstructor().newInstance();
                String[] value = annotation.value();
                for (String s : value) {
                    LLCNodeMatcherAbstract.register(s, (LLCNodeMatcher) o);
                }
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
