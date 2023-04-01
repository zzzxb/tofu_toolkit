package cn.tofucat.toolkit.llc.load;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class LoadClassAbstract {

    protected void load(String pkg) {
        loadClassList(scanPackage(pkg));
    }

    /**
     * 扫描包下注解
     * @param pkg 示例： "cn.tofucat.toolkit.llc.matcher"
     */
    protected List<String> scanPackage(String pkg) {
        String resourcePath = resourcePath(pkg);
        List<File> files = scanFile(new File(resourcePath));
        return formatFilePath(pkg, resourcePath, files);
    }

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

    private List<String> formatFilePath(String pkg, String resourcePath, List<File> files) {
        List<String> classPathList = new LinkedList<>();
        for (File file : files) {
            String classPath = file.getPath()
                    .substring(resourcePath.length())
                    .replace(File.separator, ".");
            classPathList.add(pkg + "." + classPath.replace(".class", ""));
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
