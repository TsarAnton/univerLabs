package pack;

public class Updater {

    StringBuilder str;
    boolean checked[];

    public Updater(String str) {
        this.str = new StringBuilder(str);
        checked = new boolean[str.length()];
    }

    public void check(char a) {
        for(int i = 0; i < this.str.length(); i++) {
            if(this.str.charAt(i) == a) {
                checked[i] = true;
            }
        }
    }

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

    public int countOfSymbol(char s) {
        int result = 0;
        for(int i = 0; i < this.str.length(); i++) {
            if(this.str.charAt(i) == s) {
                result++;
            }
        }
        return result;
    }

    public StringBuilder new_string() {
        for(int i = 0; i < this.str.length(); i++) {
            if(Updater.isRussianSymbol(this.str.charAt(i)) && !this.checked[i]) {
                this.check(this.str.charAt(i));
                this.str.append(". ").append(this.countOfSymbol(this.str.charAt(i)));
            }
        }
        return this.str;
    }
}
