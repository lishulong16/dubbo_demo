package net.dubbo.common.utils;

import java.util.Random;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/28
 * @doing
 */
public class RandomUtil {

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuyzABCDEFGHIJKLMNOPQRSTUYZ";
    public static final String CHAER = "abcdefghijklmnopqrstuyz";
    public static final String NUMBER = "0123456789";
    public static Random random = new Random();
    /***
     *
     * @param length
     * @return 大小写数字随机字符串
     */
    public static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 大小写随机字符串
     * @param length
     * @return 大小写随机字符串
     */
    public static String generateMixString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append(CHAER.charAt(random.nextInt(CHAER.length())));
        return sb.toString();
    }

    public static String generateLowerString(int length){
        return generateMixString(length).toLowerCase();
    }

    public static String generateUpperString(int length){
        return generateMixString(length).toUpperCase();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtil.generateString(10));
            System.out.println(RandomUtil.generateMixString(10));
            System.out.println(RandomUtil.generateLowerString(10));
            System.out.println(RandomUtil.generateUpperString(10));
        }

    }
}
