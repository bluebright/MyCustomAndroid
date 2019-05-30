package kr.co.bluebright.www.myexperiment.common.util;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Converter {

    private static final int SIGNED_24_BIT_MIN = -8388608;

    /**
     * Integer min value -2147483648
     *
     * @see Integer
     */
    private static final int SIGNED_32_BIT_MIN = Integer.MIN_VALUE;

    //region Convert Double to String

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String double_To_String(double src) {
        return Double.toString(src);
    }

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String double_To_String2(double src) {
        return String.valueOf(src);
    }

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     * @deprecated Use {@link #double_To_String2(double)}
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public static String double_To_String3(double src) {
        return new Double(src).toString();
    }

    /**
     *
     */
    public static String double_To_String4(double src) {
        return src + "";
    }

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String doubleToStringFormat(@Nullable Locale locale, double src) {
        return String.format((locale == null) ? Locale.getDefault() : locale, "%f", src);
    }


    public static String double_To_StringDecimalFormat(double src) {
        return DecimalFormat.getNumberInstance().format(src);
    }

    /**
     * @param pattern a non-localized pattern string
     * @throws NullPointerException     if <code>pattern</code> is null
     * @throws IllegalArgumentException if the given pattern is invalid.
     * @see java.text.NumberFormat#getInstance
     * @see java.text.NumberFormat#getNumberInstance
     * @see java.text.NumberFormat#getCurrencyInstance
     * @see java.text.NumberFormat#getPercentInstance
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String double_To_StringDecimalFormat(String pattern, double src) {
        try {
            return (pattern == null || pattern.isEmpty())
                    ? double_To_StringDecimalFormat(src)
                    : new DecimalFormat(pattern).format(src);
        } catch (NullPointerException | IllegalArgumentException e) {
            return null;
        }
    }

    //endregion


    //region Convert Integer to byte

    /**
     * Convert integer value to byte(1) value
     *
     * @param src : Source, Integer value
     * @return Result, Byte value (Single)
     */
    public static byte int_To_Byte(int src) {
        return (byte) (src & 0x000000FF);
    }

    public static byte[] int_To_Byte3(int src) {
        byte[] result = new byte[3];
        result[0] = int_To_Byte((src >>> 16));
        result[1] = int_To_Byte((src >>> 8));
        result[2] = int_To_Byte((src));
        return result;
    }

    /**
     * Convert integer value to byte(4) value
     *
     * @param src : Source, Integer value
     * @return result : Result, Byte value (Quadruple)
     */
    public static byte[] int_To_Byte4(int src) {
        byte[] result = new byte[4];
        result[0] = (byte) (src >>> 24);
        result[1] = (byte) (src >>> 16);
        result[2] = (byte) (src >>> 8);
        result[3] = (byte) src;

        return result;
    }
    //endregion


    //region Convert byte to int

    /**
     * Convert byte(1) value to integer value
     *
     * @param src : Source, Single byte value
     * @return Result, Integer value
     */

    public static int byte_To_Int(byte src) {
        return src & 0xFF;
    }

    public static int byte3_To_Int(byte[] src) {
        int s1 = src[0] & 0x7F;
        int s2 = src[1] & 0xFF;
        int s3 = src[2] & 0xFF;

        int res = (s1 << 16) + (s2 << 8) + (s3);
        if ((src[0] & (byte) 0x80) == (byte) 0x80) {
            res += SIGNED_24_BIT_MIN;
        }

        return res;
    }

    public static int byte3_To_Int(byte src1, byte src2, byte src3) {
        int s1 = src1 & 0x7F;
        int s2 = src2 & 0xFF;
        int s3 = src3 & 0xFF;

        int res = (s1 << 16) + (s2 << 8) + (s3);
        if ((src1 & (byte) 0x80) == (byte) 0x80) {
            res += SIGNED_24_BIT_MIN;
        }
        return res;
    }

    public static int byte3_To_Int(int start, byte[] src) {
        int s1 = src[start] & 0x7F;
        int s2 = src[start + 1] & 0xFF;
        int s3 = src[start + 2] & 0xFF;

        int res = (s1 << 16) + (s2 << 8) + (s3);
        if ((src[start] & (byte) 0x80) == (byte) 0x80) {
            res += SIGNED_24_BIT_MIN;
        }
        return res;
    }

    public static int byte4_To_Int(int start, byte[] src) {
        int s1 = src[start] & 0xFF;
        int s2 = src[start + 1] & 0xFF;
        int s3 = src[start + 2] & 0xFF;
        int s4 = src[start + 3] & 0xFF;

        return (s1 << 24) + (s2 << 16) + (s3 << 8) + (s4);
    }

    public static int byte4_To_Int(byte[] src) {
        int s1 = src[0] & 0x7F;
        int s2 = src[1] & 0xFF;
        int s3 = src[2] & 0xFF;
        int s4 = src[3] & 0xFF;

        int res = (s1 << 24) + (s2 << 16) + (s3 << 8) + (s4);
        if ((src[0] & (byte) 0x80) == (byte) 0x80) {
            res -= SIGNED_32_BIT_MIN;
        }

        return res;
    }

    public static int byte4_To_IntWrapper(byte[] src) {
        return java.nio.ByteBuffer.wrap(src).getInt();
    }

    //endregion


    /**
     * Convert string value of hexadecimal value to byte array value
     *
     * @param src : String value written hexadecimal
     * @return Byte array value
     */
    public static byte[] hexString_To_ByteArray(String src) {

        try {
            int stringLength = src.length();
            byte[] data = new byte[stringLength / 2];

            for (int i = 0; i < stringLength; i += 2) {
                data[i / 2] = (byte) ((Character.digit(src.charAt(i), 16) << 4) + Character.digit(src.charAt(i + 1), 16));
            }

            return data;
        } catch (NullPointerException | IndexOutOfBoundsException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Byte> hexString_To_ByteList(String src) {
        try {
            int stringLength = src.length();

            if (stringLength % 2 == 1) {
                src += "0";
            }

            ArrayList<Byte> data = new ArrayList<>();

            for (int i = 0; i < stringLength; i += 2) {
                data.add((byte) ((Character.digit(src.charAt(i), 16) << 4) + Character.digit(src.charAt(i + 1), 16)));
            }

            return data;
        } catch (NullPointerException | IndexOutOfBoundsException | ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Convert {@code ArrayList<Byte>} to byte array
     * This method using library from "org.apache.commons.lang3".
     *
     * @param arrayList {@code ArrayList<Byte>} which convert to array
     * @return byte array
     * @see ArrayList
     * @see Byte
     * @see ArrayUtils

    public static byte[] byteArrayList_To_Array(ArrayList<Byte> arrayList) {
        return ArrayUtils.toPrimitive(arrayList.toArray(new Byte[0]));
    }
    */
}
