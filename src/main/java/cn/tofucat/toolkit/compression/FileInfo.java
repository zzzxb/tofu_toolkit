package cn.tofucat.toolkit.compression;

import lombok.Data;

/**
 * zzzxb
 * 2023/8/10
 */
@Data
public class FileInfo {
    public String fileFullName;
    public String filename;
    public String fileType;
    public long fileSize;
    public byte[] fileContent;

    public FileInfo() {
    }

    public FileInfo(String fileFullName, long fileSize) {
        this.fileSize = fileSize;
        this.fileType = fileFullName.substring(fileFullName.lastIndexOf(".") + 1);
        this.filename = fileFullName.substring(0, fileFullName.lastIndexOf("."));
    }

    public FileInfo(String fileFullName, byte[] data) {
        this.fileContent = data;
        this.fileSize = data.length;
        this.fileType = fileFullName.substring(fileFullName.lastIndexOf(".") + 1);
        this.filename = fileFullName.substring(0, fileFullName.lastIndexOf("."));
    }
}
