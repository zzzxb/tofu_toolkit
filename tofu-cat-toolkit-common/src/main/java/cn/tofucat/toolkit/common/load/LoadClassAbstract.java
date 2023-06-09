package cn.tofucat.toolkit.common.load;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 动态加载类
 */
@Slf4j
public abstract class LoadClassAbstract {
    private final static ConcurrentSkipListSet<String> PACKAGE_RECORD = new ConcurrentSkipListSet<>();

    /**
     * 扫描包下所有文件, 并加载类
     * @param pkg 示例： "cn.tofucat.toolkit.llc.matcher"
     */
    public void load(String pkg) {
        if (PACKAGE_RECORD.contains(pkg)) {
            throw new RuntimeException("Reloading Package: " + pkg);
        }
        loadClassList(scanPackage(pkg));
        PACKAGE_RECORD.add(pkg);
    }

    /**
     * 扫描包下注解
     * @param pkg 示例： "cn.tofucat.toolkit.llc.matcher"
     */
    protected List<String> scanPackage(String pkg) {
        String resourcePath = resourcePath(pkg);
        String absoluteResourcePath = new File(resourcePath).getAbsolutePath();
        List<File> files = scanFile(new File(resourcePath));
        return formatFilePath(pkg, absoluteResourcePath, files);
    }

    /**
     * 加载类
     * @param classPathList 类路径列表
     * @return 对应类的实例
     */
    protected List<Object> loadClassList(List<String> classPathList) {
        List<Object> objectList = new LinkedList<>();
        for (String classPath : classPathList) {
            Object o = loadClass(classPath);
            if (o != null) {
                objectList.add(o);
            }
        }
        return objectList;
    }

    /**
     * 加载类
     * @param classPath cn.tofucat.toolkit.llc.matcher.abc.class
     * @return new abc();
     */
    protected abstract Object loadClass(String classPath);

    /**
     *
     * @param pkg cn.tofucat.toolkit.llc.handler
     * @param resourcePath Resource absolute path, For example: /opt/app/llc/target/classes/cn/tofucat/toolkit/llc/handler
     * @param files /opt/app/llc/target/classes/cn/tofucat/toolkit/llc/handler/abc.class
     * @return   cn.tofucat.toolkit.llc.handler.abc
     */
    private List<String> formatFilePath(String pkg, String resourcePath, List<File> files) {
        List<String> classPathList = new LinkedList<>();
        for (File file : files) {
            String classPath = file.getPath()
                    .replace(resourcePath, "")
                    .replace(File.separator, ".")
                    .replace(".class", "");
            classPathList.add(pkg + classPath);
        }
        return classPathList;
    }

    private List<File> scanFile(File ...files) {
        List<File> collectFileList = new LinkedList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                if (null != fileList) {
                    collectFileList.addAll(scanFile(fileList));
                }
                continue;
            }
            if (file.isFile()) {
                collectFileList.add(file);
            }
        }
        return collectFileList;
    }

    private String resourcePath(String classPath) {
        return Objects.requireNonNull(Thread
                        .currentThread()
                        .getContextClassLoader()
                        .getResource(classPath.replace(".", "/")))
                .getFile();
    }
}
