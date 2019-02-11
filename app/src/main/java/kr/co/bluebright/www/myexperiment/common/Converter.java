package kr.co.bluebright.www.myexperiment.common;

import android.support.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.Locale;

public class Converter {

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String doubleToString(double src) {
        return Double.toString(src);
    }

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String doubleToString2(double src) {
        return String.valueOf(src);
    }

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     * @deprecated Use {@link #doubleToString2(double)}
     */
    @Deprecated
    public static String doubleToString3(double src) {
        return new Double(src).toString();
    }

    /**
     *
     */
    public static String doubleToString4(double src) {
        return src + "";
    }

    /**
     * @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String doubleToStringFormat(@Nullable Locale locale, double src) {
        return String.format((locale == null) ? Locale.getDefault() : locale, "%f", src);
    }


    public static String doubleToStringDecimalFormat(double src) {
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
     *  @see <a href="https://www.journaldev.com/18380/java-convert-double-to-string">JournalDev</a>
     */
    public static String doubleToStringDecimalFormat(String pattern, double src) {
        try {
            return (pattern == null || pattern.isEmpty())
                    ? doubleToStringDecimalFormat(src)
                    : new DecimalFormat(pattern).format(src);
        } catch (NullPointerException | IllegalArgumentException e) {
            return null;
        }
    }


}
