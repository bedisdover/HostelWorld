package cn.edu.nju.util;

/**
 * Created by song on 17-3-8.
 * <p>
 * id工具类
 */
public enum IdUtil {
    ;

    public static String getNext(String id) {
        return getNext(id, id.length());
    }

    /**
     * 递增生成下一个ID
     * 返回结果长度为length，若长度不足，高位补0；若长度超过length，抛出异常
     *
     * @param id     ID
     * @param length 目标ID长度
     * @return 生成的ID
     */
    public static String getNext(String id, int length) throws IllegalStateException {
        String temp = Integer.parseInt(id) + 1 + "";

        if (temp.length() > length) {
            throw new IllegalStateException("out of bounds");
        }

        while (temp.length() < length) {
            temp = '0' + temp;
        }

        return temp;
    }
}
