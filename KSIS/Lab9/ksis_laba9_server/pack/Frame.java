package pack;

public class Frame {

    int width;
    int height;
    String[] text;
    int[] colorCorrect;

    public Frame(String str) {
        this.text = str.split("\n");
        this.colorCorrect = new int[text.length];
        int width = 0;
        for(int i = 0; i < text.length; i++) {
            for(int j = 0; j < text[i].length(); j++) {
                if(Character.compare(text[i].charAt(j), '[') == 0) {
                    colorCorrect[i] += 5;
                }
            }
            if(width < text[i].length()) {
                width = text[i].length();
            }
        }
        this.width = width + 4;
        this.height = text.length;
    }

    public String printFrame(String color) {
        StringBuilder str = new StringBuilder();
        str.append(Color.setColor(color));
        str.append(" ");
        for(int i = 0; i < width + 2; i++) {
            str.append("_");
        }
        str.append("\n|\\_");
        for(int i = 0; i < width - 2; i++) {
            str.append("_");
        }
        str.append("_/|\n");
        for(int i = 0; i < height; i++) {
            str.append(("| | " + Color.setColor("white") + text[i] + Color.setColor(color)));
            for(int j = 0; j < width - text[i].length() - 4 + colorCorrect[i]; j++) {
                str.append((" "));
            }
            str.append((" | |\n"));
        }
        str.append(("| |_"));
        for(int i = 0; i < width - 4; i++) {
            str.append(("_"));
        }
        str.append("_| |\n");
        str.append("|/_");
        for(int i = 0; i < width - 2; i++) {
            str.append("_");
        }
        str.append("_\\|\n" + Color.setColor("white"));
        return str.toString();
    }

    
}
