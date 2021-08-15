package org.sparrow.framework.util;

import java.util.Locale;

public class FormatString {

    public static String getStringWithoutSpaceLowerCase(String string) {
        return string.toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
    }
}