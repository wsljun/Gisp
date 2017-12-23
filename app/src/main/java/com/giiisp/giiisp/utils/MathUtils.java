package com.giiisp.giiisp.utils;

/**
 * Created by FuBo on 2017/1/5.
 * Link：QQ:1530120997
 */

public class MathUtils {
    static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }
}
