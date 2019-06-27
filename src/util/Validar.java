package util;

public class Validar {
    public static boolean STRING(String string){
        if(string.trim().length() > 1)
            return true;
        return false;
    }
}
