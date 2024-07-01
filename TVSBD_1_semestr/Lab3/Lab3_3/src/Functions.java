public class Functions {
    public static boolean isRussianSymbol(char s) {
        if(s == '\u0410' || s == '\u0415' || 
           s == '\u0418' || s == '\u041E' ||
           s == '\u0423' || s == '\u042B' ||
           s == '\u042D' || s == '\u042F' ||
           s == '\u0430' || s == '\u0435' ||
           s == '\u0438' || s == '\u043E' ||
           s == '\u0443' || s == '\u044B' ||
           s == '\u044D' || s == '\u044E' ||
           s == '\u044F' || s == '\u042E') {
            return true;
        }

        return false;
    }

    public static int countOfSymbol(char s, StringBuilder str) {
        int result = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == s) {
                result++;
            }
        }
        return result;
    }

    public static void check(char a, boolean checked[], StringBuilder str) {
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == a) {
                checked[i] = true;
            }
        }
    }
}
