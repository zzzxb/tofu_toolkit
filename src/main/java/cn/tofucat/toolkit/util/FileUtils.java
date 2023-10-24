package cn.tofucat.toolkit.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * zzzxb
 * 2023/8/10
 */
public final class FileUtils {

    public static boolean exists(Collection<String> filepath) {
        for (String path : filepath) {
            CheckParamUtils.isFalse(exists(path)).throwMessage("file not exists");
        }
        return true;
    }
    public static boolean exists(String filepath) {
        return Files.exists(Paths.get(filepath));
    }
}
