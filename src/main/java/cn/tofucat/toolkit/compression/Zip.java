package cn.tofucat.toolkit.compression;


import cn.tofucat.toolkit.util.ArgumentCheck;
import cn.tofucat.toolkit.util.FileCheck;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zzzxb
 * 2023/8/10
 */
public class Zip implements CompressionFile {

    @Override
    public CompressFileInfo compress(List<String> filepathList, String outPath) {
        return compress(filepathList, outPath, false, 0);
    }

    @Override
    public CompressFileInfo decompress(String filepath) {
        return null;
    }

    /**
     * 压缩文件
     * @param filepathList 文件路径列表
     * @param outPath 输出路径
     * @param serial 是否按顺序生成文件名
     */
    public CompressFileInfo compress(Collection<String> filepathList, String outPath, boolean serial, int custom) {
        ArgumentCheck.collectionIsEmpty(filepathList, "filepath is empty");
        FileCheck.exists(filepathList);

        final HashSet<String> filepathSet = new HashSet<>(filepathList);
        final LinkedList<String> fileNameList = new LinkedList<>();
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(Paths.get(outPath)))) {
            int index = custom;
            for (String filepath : filepathSet) {
                String name = new File(filepath).getName();
                String filename = serial ? index + name.substring(name.lastIndexOf(".")) : name;
                fileNameList.add(filename);
                zipOutputStream.putNextEntry(new ZipEntry(filename));
                // 压缩大文件的话，这里可以使用缓冲流
                zipOutputStream.write(Files.readAllBytes(Paths.get(filepath)));
                zipOutputStream.closeEntry();
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = new File(outPath);
        return new CompressFileInfo(file.getName(), file.length(), outPath, fileNameList);
    }

    /**
     * 压缩文件, 压缩包内文件名字按顺序从 1 生成
     * @param filepathList 文件路径列表
     * @param outPath 输出路径
     * @param serial 是否按顺序生成文件名
     */
    public CompressFileInfo compress(LinkedList<String> filepathList, String outPath, boolean serial) {
        return compress(filepathList, outPath, serial, 1);
    }

    /**
     * 压缩文件, 压缩包内文件名字按顺序从 1 生成
     * @param fileInfos 文件基础信息
     * @param outPath 输出路径
     * @param serial 是否按顺序生成文件名
     */
    public CompressFileInfo compressFile(LinkedList<FileInfo> fileInfos, String outPath, boolean serial) {
        return compressFile(fileInfos, outPath, serial, 1);
    }

    public CompressFileInfo compressFile(Collection<FileInfo> fileInfos, String outPath, boolean serial, int custom) {
        ArgumentCheck.collectionIsEmpty(fileInfos, "filepath is empty");

        final LinkedList<String> fileNameList = new LinkedList<>();
        try(ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(Paths.get(outPath)))) {
            int index = custom;
            for (FileInfo fileInfo : fileInfos) {
                String filename = serial ? index  + "." + fileInfo.getFileType() : fileInfo.getFileFullName();
                fileNameList.add(filename);
                zipOutputStream.putNextEntry(new ZipEntry(filename));
                // 压缩大文件的话，这里可以使用缓冲流
                zipOutputStream.write(fileInfo.getFileContent());
                zipOutputStream.closeEntry();
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = new File(outPath);
        return new CompressFileInfo(file.getName(), file.length(), outPath, fileNameList);
    }

}
