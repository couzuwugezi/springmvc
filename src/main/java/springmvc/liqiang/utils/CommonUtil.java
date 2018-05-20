package springmvc.liqiang.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class CommonUtil {

    public static int toInt(Object str) {
        if (str == null) {
            return 0;
        }
        if (str instanceof Integer) {
            Integer n = (Integer) str;
            return n.intValue();
        } else if (str instanceof Long) {
            Long n = (Long) str;
            return n.intValue();
        } else if (str instanceof Double) {
            Double n = (Double) str;
            return n.intValue();
        } else if (str instanceof BigDecimal) {
            BigDecimal n = (BigDecimal) str;
            return n.intValue();
        }
        try {
            return Integer.parseInt(str.toString().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public static String toString(Object o) {
        return toString(o, "");
    }

    public static String toStringTrim(Object o) {
        return toString(o).replaceAll(" ", "");
    }

    public static String toString(Object o, String defaultStr) {
        if (o == null) {
            return defaultStr;
        }
        String str = o.toString();
        if ("".equals(str.trim()))
            return defaultStr;
        return str;
    }

    public static long toLong(Object str) {
        if (str == null) {
            return 0;
        }
        if (str instanceof Integer) {
            Integer n = (Integer) str;
            return n.longValue();
        } else if (str instanceof Long) {
            Long n = (Long) str;
            return n.longValue();
        } else if (str instanceof Double) {
            Double n = (Double) str;
            return n.longValue();
        } else if (str instanceof BigDecimal) {
            BigDecimal n = (BigDecimal) str;
            return n.longValue();
        }
        try {
            return Long.parseLong(str.toString().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 将Unicode转换为UTF8
     *
     * @param instr String
     * @return String 返回UTF-8字符串
     */
    public static String convertUnicode2UTF8Byte(String instr) {
        if (instr == null)
            return "";
        int len = instr.length();
        byte[] abyte = new byte[len << 2];
        int j = 0;
        String str = "";
        for (int i = 0; i < len; i++) {
            char c = instr.charAt(i);
            if (c < 0x80) {
                abyte[j++] = (byte) c;
                str += c;
            } else if (c < 0x0800) {
                abyte[j++] = (byte) (((c >> 6) & 0x1F) | 0xC0);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
                abyte[j++] = (byte) ((c & 0x3F) | 0x80);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
            } else if (c < 0x010000) {
                abyte[j++] = (byte) (((c >> 12) & 0x0F) | 0xE0);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
                abyte[j++] = (byte) (((c >> 6) & 0x3F) | 0x80);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
                abyte[j++] = (byte) ((c & 0x3F) | 0x80);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
            } else if (c < 0x200000) {
                abyte[j++] = (byte) (((c >> 18) & 0x07) | 0xF8);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
                abyte[j++] = (byte) (((c >> 12) & 0x3F) | 0x80);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
                abyte[j++] = (byte) (((c >> 6) & 0x3F) | 0x80);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
                abyte[j++] = (byte) ((c & 0x3F) | 0x80);
                str += "%" + Integer.toHexString(abyte[j - 1]).substring(6);
            }
        }
        return str;
    }

    public static boolean isNew(Map<?, ?> map) {
        return "isNew".equals(map.get("_rowcheck"));
    }

    public static double toDouble(Object str) {
        if (str == null) {
            return 0;
        }
        if (str instanceof Integer) {
            Integer n = (Integer) str;
            return n.doubleValue();
        } else if (str instanceof Long) {
            Long n = (Long) str;
            return n.doubleValue();
        } else if (str instanceof Double) {
            Double n = (Double) str;
            return n.doubleValue();
        } else if (str instanceof BigDecimal) {
            BigDecimal n = (BigDecimal) str;
            return n.doubleValue();
        }

        try {
            return Double.parseDouble(str.toString().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public static Double toDoubleObj(Object str) {
        if (str == null) {
            return new Double(0);
        }
        if (str instanceof Double) {
            return (Double) str;
        }
        return new Double(CommonUtil.toDouble(str));
    }

    public static Integer toIntegerObj(Object str) {
        if (str == null) {
            return new Integer(0);
        }
        if (str instanceof Integer) {
            return (Integer) str;
        }
        return new Integer(CommonUtil.toInt(str));
    }

    public static Long toLongObj(Object str) {
        if (str == null) {
            return new Long(0);
        }
        if (str instanceof Long) {
            return (Long) str;
        }
        return new Long(CommonUtil.toLong(str));
    }

    public static String java2sqlName(Object obj) {
        if (obj == null)
            return null;
        String s = obj.toString();
        StringBuffer sqlBuffer = new StringBuffer(s.length() * 2);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                sqlBuffer.append('_');
            sqlBuffer.append(s.charAt(i));
        }
        return sqlBuffer.toString().toLowerCase();
    }

    public static String sql2JavaName(Object obj) {
        if (obj == null)
            return null;
        String s = obj.toString();
        StringBuffer strBuffer = new StringBuffer(s.length());
        boolean bf = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '_') {
                bf = true;
                continue;
            }
            if (bf) {
                strBuffer.append(Character.toUpperCase(s.charAt(i)));
                bf = false;
            } else {
                strBuffer.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        return strBuffer.toString();
    }

    public static String stringNoNull(Object obj) {
        if (obj == null)
            return "";
        return obj.toString();
    }

    public static String stringNoNull(Object obj, String defaultStr) {
        if (obj == null)
            return defaultStr;
        return obj.toString();
    }

    public static void main(String args[]) {

        System.out.println(CommonUtil.formatNumber(3, "000"));
    }

    public static String formatNumber(Object d) {
        if (d == null)
            return "0.00";
        String retStr = "0.00";
        try {
            DecimalFormat dFormat = new DecimalFormat("#0.00");
            if (d instanceof String) {
                retStr = dFormat.format(Double.valueOf(d.toString()));
            } else {
                retStr = dFormat.format(d);
            }
            return retStr;
        } catch (Exception e) {
            return retStr;
        }
    }

    public static String formatNumber(Object d, String format) {
        if (d == null)
            d = new Double(0);
        String retStr = "0";
        try {
            DecimalFormat dFormat = new DecimalFormat(format);
            if (d instanceof String) {
                retStr = dFormat.format(Double.valueOf(d.toString()));
            } else {
                retStr = dFormat.format(d);
            }
            return retStr;
        } catch (Exception e) {
            return retStr;
        }
    }

    public static String formatDate() {
        return CommonUtil.formatDate("yyyy-MM-dd");
    }

    public static String formatDate(Object o) {
        if (o instanceof String) {
            return CommonUtil.formatDate(new Date(), o.toString());
        } else {
            return CommonUtil.formatDate(o, "yyyy-MM-dd");
        }
    }

    public static String formatDate(Object o, String format) {
        String retStr = "";
        try {
            Date dt = null;
            if (o instanceof java.util.Calendar) {
                java.util.Calendar cal = (java.util.Calendar) o;
                dt = cal.getTime();
            } else if (o instanceof Date) {
                dt = (Date) o;
            } else {
                return "Error Object";
            }
            DateFormat dFormat = new SimpleDateFormat(format);
            retStr = dFormat.format(dt);
            return retStr;
        } catch (Exception e) {
            e.printStackTrace();
            return retStr;
        }
    }

    public static String formatNumber(double d) {
        String retStr = "0.00";
        try {
            DecimalFormat dFormat = new DecimalFormat("#0.00");
            retStr = dFormat.format(d);
            return retStr;
        } catch (Exception e) {
            e.printStackTrace();
            return retStr;
        }
    }

    public static String formatNumber(double d, String format) {
        String retStr = "0";
        try {
            DecimalFormat dFormat = new DecimalFormat(format);
            retStr = dFormat.format(d);
            return retStr;
        } catch (Exception e) {
            return retStr;
        }
    }

    public static Date toDate(Object strDate) {
        return CommonUtil.toDate(strDate, "yyyy-MM-dd");
    }

    public static Date toDate(Object strDate, String format) {
        if (strDate == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setDefault(Map<String, String> map, String key, String defaultValue) {
        Object obj = map.get(key);
        if (obj == null || obj.equals("")) {
            map.put(key, defaultValue);
        }
    }

    /**
     * Pads a String <code>s</code> to take up <code>n</code> characters,
     * padding with char <code>c</code> on the left (<code>true</code>) or on
     * the right (<code>false</code>). Returns <code>null</code> if passed a
     * <code>null</code> String.
     **/
    public static String paddingString(String s, int n, char c, boolean paddingLeft) {
        if (s == null) {
            return s;
        }
        int add = n - s.length(); // may overflow int size... should not be a
        // problem in real life
        if (add <= 0) {
            return s;
        }
        StringBuffer str = new StringBuffer(s);
        char[] ch = new char[add];
        Arrays.fill(ch, c);
        if (paddingLeft) {
            str.insert(0, ch);
        } else {
            str.append(ch);
        }
        return str.toString();
    }

}
