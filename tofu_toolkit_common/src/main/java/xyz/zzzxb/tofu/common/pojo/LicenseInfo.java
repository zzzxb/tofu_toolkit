package xyz.zzzxb.tofu.common.pojo;

import lombok.Data;

/**
 * zzzxb
 * 2024/3/11
 */
@Data
public class LicenseInfo {
    // 签发时间
    private Long toi;
    // 过期时间
    private Long et;
    // 授权用户
    private String au;
    // 数据签名
    private String ds;
}
