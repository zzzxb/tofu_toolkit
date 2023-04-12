package cn.tofucat.toolkit.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static void writeFile(String filepath, byte[] data) {
        try(FileOutputStream fos = new FileOutputStream(filepath)) {
            fos.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] readFile(String path) {
        try {
            return Files.readAllBytes(new File(path).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean fileExists(String filepath) {
        return Files.exists(Paths.get(filepath));
    }

    public static boolean dirExists(String path) {
        return Files.isDirectory(Paths.get(path));
    }
}
