package org.sapar;

public class DataTypeDetector {
    public static boolean isInteger(String line) {
        if (line == null || line.isEmpty()) return false;

        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isFloat(String line){
        if (line == null || line.isEmpty()) return false;

        try {
            Float.parseFloat(line);
            return line.contains(".") || line.toLowerCase().contains("e");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isString(String line){
        return !isInteger(line) && !isFloat(line);
    }
}
