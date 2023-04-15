package cn.tofucat.toolkit.llc.matcher.handler;

import cn.tofucat.toolkit.common.util.CipherUtils;
import cn.tofucat.toolkit.common.annotation.Register;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcher;
import cn.tofucat.toolkit.llc.parser.LLCNode;

@Register({"MD5", "SHA1", "SHA256"})
public class CipherMatcherHandler implements LLCNodeMatcher {
    @Override
    public String convert(LLCNode node) {
        CipherUtils.Encrypt encrypt;
        switch (node.getKey()) {
            case "SHA-1":
            case "SHA1": return CipherUtils.encrypt(node.getValue(), CipherUtils.Encrypt.SHA1);
            case "sha-256":
            case "SHA256": return CipherUtils.encrypt(node.getValue(), CipherUtils.Encrypt.SHA256);
            default: return CipherUtils.encrypt(node.getValue(), CipherUtils.Encrypt.MD5);
        }
    }
}
