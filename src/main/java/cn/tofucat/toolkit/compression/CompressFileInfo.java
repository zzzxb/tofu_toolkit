package cn.tofucat.toolkit.compression;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * zzzxb
 * 2023/8/10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CompressFileInfo extends FileInfo {
    public List<String> fileListInfo;
    public String absoluteFilePath;

    public CompressFileInfo(String fileFullName, long fileSize) {
        super(fileFullName, fileSize);
    }

    public CompressFileInfo(String fileFullName, long fileSize, String absoluteFilePath) {
        super(fileFullName, fileSize);
        this.absoluteFilePath = absoluteFilePath;
    }

    public CompressFileInfo(String fileFullName, long fileSize, String absoluteFilePath, List<String> fileListInfo) {
        super(fileFullName, fileSize);
        this.absoluteFilePath = absoluteFilePath;
        this.fileListInfo = fileListInfo;
    }

    @Override
    public String toString() {
        return "CompressFileInfo{" +
                "fileListInfo=" + fileListInfo +
                ", absoluteFilePath='" + absoluteFilePath + '\'' +
                ", filename='" + filename + '\'' +
                ", fileFullName='" + fileFullName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
