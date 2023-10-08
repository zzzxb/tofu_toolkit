package cn.tofucat.toolkit.compression;

import java.util.List;

/**
 * zzzxb
 * 2023/8/10
 */
public interface CompressionFile {
    /**
     * 压缩
     * @param filepath 文件路径
     */
    CompressFileInfo compress(List<String> filepath, String outPath);

    /**
     * 解压
     * @param filepath 压缩包路径
     */
    CompressFileInfo decompress(String filepath);
}
