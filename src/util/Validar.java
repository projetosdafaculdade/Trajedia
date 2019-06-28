package util;

public class Validar {

    public static boolean STRING(String string) {
        if (string != null) {
            if (string.trim().length() > 1) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    static boolean DOUBLE(String string) {
        try {
            double numero = Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void continuar(String string) {
        if (string == null) {
            Double.parseDouble(string);
        }
    }

    public static void continuar(Double doubles) {
        if (doubles == null) {
          doubles = doubles * 0;
        }
    }

}
