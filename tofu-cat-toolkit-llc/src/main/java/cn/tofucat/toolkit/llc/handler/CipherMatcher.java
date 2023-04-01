package cn.tofucat.toolkit.llc.handler;

import cn.tofucat.toolkit.common.util.CipherUtils;
import cn.tofucat.toolkit.common.annotation.Register;
import cn.tofucat.toolkit.llc.matcher.LLCNodeMatcher;
import cn.tofucat.toolkit.llc.parser.LLCNode;

@Register({"MD5", "SHA1", "SHA256"})
public class CipherMatcher implements LLCNodeMatcher {
    @Override
    public String convert(LLCNode node) {
        CipherUtils.Encrypt encrypt;
        switch (node.getKey()) {
            case "SHA1": return CipherUtils.encrypt(node.getValue(), CipherUtils.Encrypt.SHA1);
            case "SHA256": return CipherUtils.encrypt(node.getValue(), CipherUtils.Encrypt.SHA256);
            default: return CipherUtils.encrypt(node.getValue(), CipherUtils.Encrypt.MD5);
        }
    }
}
