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

    public static boolean DOUBLE(String string) {
        try {
            double d = Double.parseDouble(string.replaceAll(",", "."));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean INT(String string) {
        try {
            Integer d = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

     public static void continuar(String string) {
        if (string == null) {
            Double.parseDouble(string);
        }
    }

    public static void continuar(Integer string) {
        if (string == null) {
            string = string * 0;
        }
    }

    public static void continuar(Double doubles) {
        if (doubles == null) {
            doubles = doubles * 0;
        }
    }

    public static boolean CPF(String string) {
        try {
            Integer d = Integer.parseInt(string.replaceAll("-", ""));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
