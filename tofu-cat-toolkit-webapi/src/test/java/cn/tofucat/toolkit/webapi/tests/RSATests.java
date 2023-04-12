package cn.tofucat.toolkit.webapi.tests;

import cn.tofucat.toolkit.common.rsa.RSA;
import org.junit.jupiter.api.Test;

public class RSATests {

    @Test
    public void a() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void generateKeyPair() {
        RSA rsa = new RSA();
        rsa.publicKey().writeFormatFile("./publicKey.pem");
        rsa.privateKey().writeFormatFile("./privateKey.pem");
    }
}
