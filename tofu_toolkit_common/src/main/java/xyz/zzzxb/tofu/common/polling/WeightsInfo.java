package xyz.zzzxb.tofu.common.polling;

import lombok.Data;

/**
 * zzzxb
 * 2023/8/24
 */
@Data
public class WeightsInfo<T> {
    /**
     * 原始权重
     */
    private int originalWeights;
    /**
     * 当前权重
     */
    private int currentWeights;
    private T data;

    public WeightsInfo(T data, int originalWeights) {
        this.originalWeights = originalWeights;
        this.currentWeights = originalWeights;
        this.data = data;
    }
}