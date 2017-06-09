package cn.edu.nju.util;

import java.util.Date;

/**
 * Created by song on 17-3-18.
 * <p>
 * date工具类
 */
public enum DateUtil {
    ;

    /**
     * 一天的毫秒数
     */
    private static final int DAY_MILLIONS = 24 * 60 * 60 * 1000;

    /**
     * 计算first和second相差的天数
     */
    public static int dateDiff(final Date first, final Date second) {
        long time1 = first.getTime();
        long time2 = second.getTime();

        return (int) ((time1 - time2) / DAY_MILLIONS) + 1;
    }
}
