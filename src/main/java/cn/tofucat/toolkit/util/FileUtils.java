package cn.tofucat.toolkit.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * zzzxb
 * 2023/8/10
 */
@Slf4j
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

    public static void write(byte[] bytes, String outPath) {
        try(FileOutputStream fos = new FileOutputStream(outPath)) {
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] read(String filePath) {
        return read(new File(filePath));
    }

    public static byte[] read(File file) {
        try {
            log.info("find file path: {}", file.getAbsolutePath());
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
