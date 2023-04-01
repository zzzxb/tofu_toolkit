package cn.tofucat.toolkit.llc.parser;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * zzzxb
 * 2023/3/31
 */
@Data
public class LLCNode {
    /**
     * 低代码类型
     */
    private String key;

    /**
     * 低代码内容
     */
    private String value;

    /**
     * 是否包含下一级低代码
     */
    private LLCNode node;

    private boolean next = false;

    public LLCNode(String key, String value, LLCNode node) {
        this.key = key;
        this.value = value;
        this.node = node;
        this.next = node != null;
    }

    public boolean hasNext() {
        return next;
    }
}
