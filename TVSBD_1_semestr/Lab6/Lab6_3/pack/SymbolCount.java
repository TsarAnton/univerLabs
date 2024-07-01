package pack;

public class SymbolCount {

    static int countOfSmallChars(String str) {
        int result = 0;
        for(int i = 0; i < str.length(); i++) {

            if(Character.isLowerCase(str.charAt(i))) {
                result++;
            }
        }
        return result;
    }

    static String toString(String[] array) {
        StringBuilder buff = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            buff.append(array[i]).append("\n");
        }
        return buff.toString();
    }
    
}
