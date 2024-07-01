package pack;

public class Color {
    public static String setColor(String color) {
        String res = "";
        switch(color) {
            case("white"):
                res = (char)27 + "[37m";
                break;
            case("black"):
                res = (char)27 + "[30m";
                break;
            case("red"):
                res = (char)27 + "[31m";
                break;
            case("green"):
                res = (char)27 + "[32m";
                break;
            case("yellow"):
                res = (char)27 + "[33m";
                break;
            case("dark blue"):
                res = (char)27 + "[34m";
                break;
            case("purple"):
                res = (char)27 + "[35m";
                break;
            case("blue"):
                res = (char)27 + "[36m";
                break;
        }
        if(res.isEmpty()) {
            res = (char)27 + "[37m";
        }
        return res;
    }
}
