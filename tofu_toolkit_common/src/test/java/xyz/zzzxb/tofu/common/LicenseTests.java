package xyz.zzzxb.tofu.common;

import com.google.gson.Gson;
import org.junit.Test;
import xyz.zzzxb.tofu.common.algorithm.RSA;
import xyz.zzzxb.tofu.common.pojo.LicenseInfo;
import xyz.zzzxb.tofu.common.util.CipherUtils;
import xyz.zzzxb.tofu.common.util.DateUtils;
import xyz.zzzxb.tofu.common.util.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * zzzxb
 * 2024/3/11
 */
public class LicenseTests {

    @Test
    public void generationLicense() {
        byte[] jarPackageBytes = FileUtils.read("target/tofu_toolkit-1.0-SNAPSHOT.jar");
        String jarPackageMD5 = CipherUtils.md5_32(jarPackageBytes);
        System.out.println("jarPackageMD5 = " + jarPackageMD5);

        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.setToi(new Date().getTime());
        licenseInfo.setEt(DateUtils.parserDate("yyyy-MM-dd HH:mm:ss","2024-03-12 00:00:00").getTime());
        licenseInfo.setAu("联动优势");
        String json = new Gson().toJson(licenseInfo);
        System.out.println("json = " + json);

        String md516 = CipherUtils.md5_16(jarPackageMD5 + json);
        System.out.println(md516);
        licenseInfo.setDs(md516);

        String data = new Gson().toJson(licenseInfo);
        System.out.println("data = " + data);

        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        System.out.println("bytes = " + bytes.length);
        String encode = RSA.getInstance().encode(new File("etc/pub.key"), RSA.Key.PublicKey, data);
        String decode = RSA.getInstance().decode(new File("etc/pri.key"), RSA.Key.PrivateKey, encode);
        System.out.println(decode);
    }
}
