package com.cfpt.base.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


public class BaseUtils {

    private final static int[] li_SecPosValue = {1601, 1637, 1833, 2078, 2274,
            2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
            4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};
    private final static String[] lc_FirstLetter = {"a", "b", "c", "d", "e",
            "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "w", "x", "y", "z"};

    public static BigDecimal trunc(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 取字符串长度;
     *
     * @param str
     * @return
     */
    public static int getStrLength(String str) {
        if (str == null || str.equals("")) {
            return 0;
        } else {
            str = str.replaceAll("[^\\x00-\\xff]", "**");
            int length = str.length();
            return length;
        }
    }


    public static BigDecimal nvl(BigDecimal bigDecimal,
                                 BigDecimal defaultBigDecimal) {
        return bigDecimal == null ? defaultBigDecimal : bigDecimal;
    }

    public static BigDecimal nvl(BigDecimal bigDecimal) {
        return bigDecimal == null ? new BigDecimal(0) : bigDecimal;
    }

    /**
     * 如果date 为空,那么返回 默认日期dateDefualt;
     *
     * @param date
     * @param dateDefualt
     * @return
     */
    public static Date nvl(Date date, Date dateDefualt) {
        if ("".equals(date) || date == null) {
            date = dateDefualt;
        }
        return date;
    }

    public static String nvl(String value) {
        return value == null ? "" : value;
    }


    private static String conversionStr(String str, String charsetName,
                                        String toCharsetName) {
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch (UnsupportedEncodingException ex) {
            System.out.println("字符串编码转换异常：" + ex.getMessage());
        }

        return str;
    }

    // 汉字转拼音码
    public static String getFirstLetter(String chinese) {
        if (chinese == null || chinese.trim().length() == 0) {
            return "";
        }
        chinese = conversionStr(chinese, "GBK", "ISO8859-1");

        if (chinese.length() > 1) // 判断是不是汉字

        {
            int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
            int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
            li_SectorCode = li_SectorCode - 160;
            li_PositionCode = li_PositionCode - 160;
            int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码

            if (li_SecPosCode > 1600 && li_SecPosCode < 5590) {
                for (int i = 0; i < 23; i++) {
                    if (li_SecPosCode >= li_SecPosValue[i]
                            && li_SecPosCode < li_SecPosValue[i + 1]) {
                        chinese = lc_FirstLetter[i];
                        break;
                    }
                }
            } else // 非汉字字符,如图形符号或ASCII码

            {
                chinese = conversionStr(chinese, "ISO8859-1", "GBK");
                chinese = chinese.substring(0, 1);
            }
        }

        return chinese;
    }

    /**
     * 邓亮,将一个,相隔的字符串,转成ORACLE in 格式的字符串;
     *
     * @param str 输入 ,1,2,3,4,,
     * @return '1','2','3','4'
     */
    public static String changeStrToOralceInFormat(String str) {
        String[] slist = str.split("\\,");
        String result = "";
        for (int i = 0; i < slist.length; i++) {
            if ("".equals(slist[i])) {
                continue;
            } else {
                result = (result == "" ? ("'" + slist[i] + "'") : (result + ","
                        + " '" + slist[i] + "'"));
            }
        }
        return result;
    }


    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 格式化金额，保证小数点后留2位小数
     *
     * @param d
     * @return
     */
    public static String formatMoney(double d) {
        DecimalFormat r = new DecimalFormat();
        r.applyPattern("#0.00");
        return r.format(d);
    }

    public static String formatXml(String inXml) {
        return inXml.replaceAll("\n", "").replaceAll("  ", "")
                .replaceAll("    ", "");
    }

    public final static String formatString(String formatStr, double decimal) {
        NumberFormat format = new DecimalFormat(formatStr);
        return format.format(decimal);
    }

    public final static String formatString(String formatStr, int num) {
        DecimalFormat df = new DecimalFormat(formatStr);
        return df.format(num);
    }

    public final static String formatString(String formatStr, long num) {
        DecimalFormat df = new DecimalFormat(formatStr);
        return df.format(num);
    }

    private static int generateCount = 0;

    public static synchronized String getUniqueString() {

        if (generateCount > 999)

            generateCount = 0;

        String uniqueNumber = new SimpleDateFormat("yyyyMMddHHmmssS")
                .format(new Date()) + formatString("#000", generateCount);

        generateCount++;

        return uniqueNumber;

    }

    public static synchronized String getSequenceNum()

    {

        if (sequenceCount > 999999999)

            sequenceCount = 0;

        String uniqueNumber = formatString("#000000000", generateCount);

        sequenceCount++;

        return uniqueNumber;

    }

    private static long sequenceCount = 0L;

    /**
     * 反射赋值（以目标对象的字段为主）
     *
     * @param sourceObj
     * @param targetObj 目标对象已实例化
     * @return
     */
    public static void setBeanValueByTarget(Object sourceObj, Object targetObj) throws Exception {
        try {

            Field[] fields = targetObj.getClass().getDeclaredFields();

            String name = "";
            Class<?> targetFieldCls = null;
            Class<?> sourceFieldCls = null;

            for (Field targetField : fields) {

                // 忽略掉反射的字段
                if (targetField.isAnnotationPresent(DeclaredOmitField.class))
                    continue;

                name = targetField.getName();
                targetFieldCls = targetField.getType();

                // 从源对象中直接获取指定字段的数值
                Field sourceField = sourceObj.getClass().getDeclaredField(name);
                sourceFieldCls = sourceField.getType();
                sourceField.setAccessible(true);
                Object value = sourceField.get(sourceObj);

                // 不直接通过目标字段的类型去找寻set方法的原因是：对于日期，字段类型是String，而set方法的参数是Date类型
                Method cMethod = null;
                Class<?> filedCls = null;
                try {
                    // 根据源字段找寻set方法
                    cMethod = targetObj.getClass().getMethod(
                            "set" + name.substring(0, 1).toUpperCase()
                                    + name.substring(1), targetFieldCls);

                    filedCls = targetFieldCls;
                } catch (NoSuchMethodException e) {
                    // 如果数据的类型和目标字段的类型不一致，则以目标字段的类型去找寻set方法，并且把数据的类型直接转换为目标字段的类型
                    try {
                        cMethod = targetObj.getClass().getMethod(
                                "set" + name.substring(0, 1).toUpperCase()
                                        + name.substring(1), sourceFieldCls);

                        filedCls = sourceFieldCls;
                    } catch (NoSuchMethodException e1) {
                        Exception ex = e1;
                        throw new Exception(ex);
                    }
                }

                // 将null值根据字段类型替换成具体的类型
                String v = "";
                if (sourceFieldCls.isAssignableFrom(Date.class))
                    v = value == null ? "" : DateUtils.sdf.format(value);
                else
                    v = value == null ? "" : value.toString();

                if (filedCls.isAssignableFrom(Integer.class))
                    value = "".equals(v) ? new Integer(0) : Integer.valueOf(v);
                else if (filedCls.isAssignableFrom(Long.class))
                    value = "".equals(v) ? new Long(0) : Long.valueOf(v);
                else if (filedCls.isAssignableFrom(Double.class))
                    value = "".equals(v) ? new Double(0) : Double.valueOf(v);
                else if (filedCls.isAssignableFrom(BigDecimal.class))
                    value = "".equals(v) ? new BigDecimal(0)
                            : new BigDecimal(v);
                else if (filedCls.isAssignableFrom(String.class))
                    value = v;

                cMethod.invoke(targetObj, value);
            }
        } catch (IllegalAccessException e) {
            // 实例化目标对象时异常
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // 没有发现目标对象的字段
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * java bean映射
     *
     * @param sourceObj   源对象
     * @param targetObj   目标对象
     * @param mappingMode 映射模式（0-以源对象字段为主；1-以目标对象字段为主）
     * @param errIgnore   映射中找不到的字段错误忽略（0-不忽略；1-忽略）
     */
    public static void setBeanMapping(Object sourceObj, Object targetObj,
                                      String mappingMode, String errIgnore) {

        try {

            Field[] fields = null;
            if ("0".equals(mappingMode))
                fields = sourceObj.getClass().getDeclaredFields();
            else
                fields = targetObj.getClass().getDeclaredFields();

            String name = "";
            Class<?> targetFieldCls = null;

            for (Field field : fields) {

                // 忽略掉反射的字段
                if (field.isAnnotationPresent(DeclaredOmitField.class))
                    continue;

                name = field.getName();

                Object value = null;

                if ("0".equals(mappingMode)) {

                    field.setAccessible(true);
                    value = field.get(sourceObj);

                    // 从源对象中直接获取指定字段的数值
                    Field targetField = targetObj.getClass().getDeclaredField(
                            name);
                    targetFieldCls = targetField.getType();
                } else {
                    // 从源对象中直接获取指定字段的数值
                    Field sourceField = sourceObj.getClass().getDeclaredField(
                            name);
                    sourceField.setAccessible(true);
                    value = sourceField.get(sourceObj);

                    targetFieldCls = field.getType();
                }

                // 不直接通过目标字段的类型去找寻set方法的原因是：对于日期，字段类型是String，而set方法的参数是Date类型
                Method cMethod = targetObj.getClass().getMethod(
                        "set" + name.substring(0, 1).toUpperCase()
                                + name.substring(1), targetFieldCls);

                // 将null值根据字段类型替换成具体的类型
                String v = "";
                if (targetFieldCls.isAssignableFrom(Date.class))
                    v = value == null ? "" : DateUtils.sdf.format(value);
                else
                    v = value == null ? "" : value.toString();

                if (targetFieldCls.isAssignableFrom(Integer.class))
                    value = "".equals(v) ? new Integer(0) : Integer.valueOf(v);
                else if (targetFieldCls.isAssignableFrom(Long.class))
                    value = "".equals(v) ? new Long(0) : Long.valueOf(v);
                else if (targetFieldCls.isAssignableFrom(Double.class))
                    value = "".equals(v) ? new Double(0) : Double.valueOf(v);
                else if (targetFieldCls.isAssignableFrom(BigDecimal.class))
                    value = "".equals(v) ? new BigDecimal(0)
                            : new BigDecimal(v);
                else if (targetFieldCls.isAssignableFrom(String.class))
                    value = v;

                cMethod.invoke(targetObj, value);
            }
        } catch (IllegalAccessException e) {
            // 实例化目标对象时异常
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // 没有发现目标对象的字段
            if ("0".equals(errIgnore))
                throw new RuntimeException("反射bean时发生了错误，" + e.getMessage()
                        + "!");
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            if ("0".equals(errIgnore))
                throw new RuntimeException("反射bean时发生了错误，" + e.getMessage()
                        + "!");
        }
    }

    public static int getAge(Date birthDay) {

        Calendar cal = Calendar.getInstance();

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else
                age--;
        }

        return age;
    }

    /**
     * 日期相减
     *
     * @param date  日期
     * @param date1 日期
     * @return 返回相减后的天数
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((date.getTime() - date1.getTime()) / (24 * 3600 * 1000));
    }

    public static String bytesToString(byte[] b) {
        String str = "";
        for (int i = 0; i < b.length; i++) {
            str += String.valueOf(b[i]);
        }

        return str;
    }

    /**
     * get inetAddress from operation system.this is not relation to platform.
     *
     * @return instance of inetAddress if find InetAddress,return null if not
     * find InetAddress or throw exception
     */
    public static List<String> getLocalHostAddress() {

        List<String> list = new ArrayList<String>();
        try {
            for (Enumeration<NetworkInterface> nis = NetworkInterface
                    .getNetworkInterfaces(); nis.hasMoreElements(); ) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isLoopback() || ni.isVirtual() || !ni.isUp())
                    continue;
                for (Enumeration<InetAddress> ias = ni.getInetAddresses(); ias
                        .hasMoreElements(); ) {
                    InetAddress ia = ias.nextElement();
                    if (ia instanceof Inet6Address)
                        continue;

                    list.add(ia.getHostAddress());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static String getLocalHostTrueAddress() {

        List<String> list = BaseUtils.getLocalHostAddress();

        if (list == null || list.isEmpty())
            return "127.0.0.1";

        for (String str : list) {
            if (str.startsWith("10.15"))
                return str;
        }

        return list.get(0);
    }

}
