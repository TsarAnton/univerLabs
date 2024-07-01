package pack;

public class SymbolCount {

    public int countOfSmallChars(String str) {
        int result = 0;
        for(int i = 0; i < str.length(); i++) {

            if(Character.isLowerCase(str.charAt(i))) {
                result++;
            }
        }
        return result;
    }
    
}
