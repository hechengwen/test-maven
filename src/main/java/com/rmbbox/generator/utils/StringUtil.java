package com.rmbbox.generator.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lxl on 15/5/9.
 */
public class StringUtil {
    private static final String TIME_FORMAT_SHORT = "yyyyMMddHHmmss";
    private static final String TIME_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_FORMAT_ENGLISH = "MM/dd/yyyy HH:mm:ss";
    private static final String TIME_FORMAT_CHINA = "yyyy年MM月dd日 HH时mm分ss秒";

    private static final String DATE_FORMAT_SHORT = "yyyyMMdd";
    private static final String DATE_FORMAT_NORMAL = "yyyy-MM-dd";
    private static final String DATE_FORMAT_ENGLISH = "MM/dd/yyyy";
    private static final String DATE_FORMAT_CHINA = "yyyy年MM月dd日";
    private static final String DATE_FORMAT_NORMAL2 = "yyyy.MM.dd";

    /**
     * 验证给定的字符串是否为空
     *
     * @param input
     *            输入字符串
     * @return 是否为空 空返回true
     * @author MASK
     * */
    public static boolean isEmpty(Object input) {
        return input == null || "".equals(input) || input.toString().length() == 0 || input.toString().trim().length() == 0;
    }

    /**
     * 验证给定的字符串是否为非空
     *
     * @param input
     *            输入字符串
     * @return 是否为空 非空返回true
     * @author MASK
     * */
    public static boolean isNotEmpty(Object input) {
        return !isEmpty(input);
    }

    /**
     * 从第n个位置开始截取至末尾 <br/>
     * 示例： cut2end("abc",1) &nbsp;返回 "bc" <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; cut2end("abc",-1)
     * 返回 "abc" <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; cut2end("abc",5)
     * &nbsp;返回 ""
     *
     * @param source
     *            源字符串
     * @param beginIndex
     *            截取的位置
     * @return 截取后的结果
     * @author MASK
     * */
    public static String cut2end(String source, int beginIndex) {
        if (StringUtil.isEmpty(source)) {
            return "";
        } else if (beginIndex < 0) {
            return source;
        } else if (beginIndex >= source.length()) {
            return "";
        } else {
            return source.substring(beginIndex);
        }
    }

    /**
     * 从第n个位置开始截取至开头 <br/>
     * 示例： cut2end("abc",1) &nbsp;返回 "ab" <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; cut2end("abc",-1)
     * 返回 "" <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; cut2end("abc",5)
     * &nbsp;返回 "abc"
     *
     * @param source
     *            源字符串
     * @param beginIndex
     *            截取的位置
     * @return 截取后的结果
     * @author MASK
     * */
    public static String cut2head(String source, int beginIndex) {
        if (StringUtil.isEmpty(source)) {
            return "";
        } else if (beginIndex < 0) {
            return "";
        } else if (beginIndex >= source.length()) {
            return source.substring(0, source.length());
        } else {
            return source.substring(0, beginIndex + 1);
        }
    }

    /**
     * 字符转换方法
     *
     * @param orig
     *            原字符串
     * @param clazz
     *            转换类型
     * @return
     * @throws ParseException
     */
    public static Object convert(String orig, Class<?> clazz) {
        if (orig == null) {
            return null;
        }
        if (clazz != Calendar.class && clazz != java.util.Date.class) {
            orig = orig.replaceAll("\\s", "");
        }
        if (clazz == String.class) {
            return orig;
        }
        if (clazz == short.class) {
            return Short.parseShort(orig);
        }
        if (clazz == Short.class) {
            return new Short(orig);
        }
        if (clazz == int.class) {
            return Integer.parseInt(orig);
        }
        if (clazz == Integer.class) {
            return new Integer(orig);
        }
        if (clazz == long.class) {
            return Long.parseLong(orig);
        }
        if (clazz == Long.class) {
            return new Long(orig);
        }
        if (clazz == float.class) {
            return Float.parseFloat(orig);
        }
        if (clazz == Float.class) {
            return new Float(orig);
        }
        if (clazz == double.class) {
            return Double.parseDouble(orig);
        }
        if (clazz == Double.class) {
            return new Double(orig);
        }

        if (orig.equalsIgnoreCase("t") || orig.equalsIgnoreCase("ture") || orig.equalsIgnoreCase("y") || orig.equalsIgnoreCase("yes")) {
            if (clazz == boolean.class) {
                return true;
            }
            if (clazz == Boolean.class) {
                return new Boolean(true);
            }
        } else {
            if (clazz == boolean.class) {
                return false;
            }
            if (clazz == Boolean.class) {
                return new Boolean(false);
            }
        }

        try {
            if (clazz == java.util.Date.class) {
                DateFormat fmt = null;
                if (orig.matches("\\d{14}")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
                } else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
                } else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_ENGLISH);
                } else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_CHINA);
                } else if (orig.matches("\\d{8}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
                } else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL);
                } else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
                } else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_CHINA);
                } else if (orig.matches("\\d{4}.\\d{1,2}.\\d{1,2}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL2);
                }
                return fmt.parse(orig);
            }
            if (clazz == Calendar.class) {
                Calendar cal = Calendar.getInstance();
                DateFormat fmt = null;
                if (orig.matches("\\d{14}")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_SHORT);
                } else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_NORMAL);
                } else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_ENGLISH);
                } else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒")) {
                    fmt = new SimpleDateFormat(TIME_FORMAT_CHINA);
                } else if (orig.matches("\\d{8}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_SHORT);
                } else if (orig.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL);
                } else if (orig.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_ENGLISH);
                } else if (orig.matches("\\d{4}年\\d{1,2}月\\d{1,2}日")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_CHINA);
                } else if (orig.matches("\\d{4}.\\d{1,2}.\\d{1,2}")) {
                    fmt = new SimpleDateFormat(DATE_FORMAT_NORMAL2);
                }
                cal.setTime(fmt.parse(orig));
                return cal;
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("字符串不能转换为" + clazz.getName() + "类型.");
        }
        throw new IllegalArgumentException("字符串不能转换为" + clazz.getName() + "类型.");
    }

    /**
     * 字符转换方法
     *
     * @param orig
     *            原字符串
     * @return
     * @throws ParseException
     */
    public static String convert(Object orig) {
        if (orig == null) {
            return null;
        }
        if (orig instanceof String) {
            return ((String) orig).trim();
        }
        if (orig instanceof Short) {
            return Short.toString((Short) orig).trim();
        }
        if (orig instanceof Integer) {
            return Integer.toString((Integer) orig).trim();
        }
        if (orig instanceof Long) {
            return Long.toString((Long) orig).trim();
        }
        if (orig instanceof Float) {
            return Float.toString((Float) orig).trim();
        }
        if (orig instanceof Double) {
            return Double.toString((Double) orig).trim();
        }
        if (orig instanceof Boolean) {
            return Boolean.toString((Boolean) orig).trim();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        if (orig instanceof java.util.Date) {
            return format.format((java.util.Date) orig).trim();
        }
        if (orig instanceof java.sql.Date) {
            return format.format((java.sql.Date) orig).trim();
        }
        if (orig instanceof Calendar) {
            return format.format(((Calendar) orig).getTime()).trim();
        }
        throw new IllegalArgumentException("参数类型不支持.");
    }

    /**
     * 去掉字符串中最后一个","号（如果最后一个字符是","）
     * @param str
     * @return 处理之后的字符串
     * */
    public static String cutLastComma(String str){
        if(str!=null&&str.length()>0&&str.length()==str.lastIndexOf(",")+1){
            str=str.substring(0, str.lastIndexOf(","));
        }
        return str;
    }

    public static String trimToEmpty(String str) {
        return str == null?"":str.trim();
    }

    public static void main(String[] args) {
    }
}
