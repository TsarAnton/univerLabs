public class BeatyString<Type> {
    public String get(Type element, int space) {
        StringBuilder str = new StringBuilder().append(element);
        if(str.length() < space) {
            int len = space - str.length();
            for(int i = 0; i < len; i++) {
                str.append(" ");
            }
        }
        return str.toString();
    }
}
