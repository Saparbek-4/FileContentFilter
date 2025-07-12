package org.sapar;

/**
 * Utility class to detect whether a line is an integer, float, or string.
 */
public class DataTypeDetector {

    public static boolean isInteger(String line) {
        if (line == null || line.isBlank()) return false;
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    public static boolean isFloat(String line) {
        boolean flag;
        if (line == null || line.isBlank()) flag = false;
        try {
            Double.parseDouble(line);
            flag = line.contains(".") || line.toLowerCase().contains("e");
        } catch (NumberFormatException ignored) {
            flag = false;
        }

        return flag;
    }

}
