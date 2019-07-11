package util;

public class ConverterDouble {

    public static double Converter(String double1) {
        try {
            return Double.parseDouble(double1);
        } catch (Exception e) {
            return 0;
        }
    }
}
