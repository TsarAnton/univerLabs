import by.vsu.mf.ai.ssd.strings.Job;

public class Updater implements Job {
    @Override
    public void perform(StringBuilder arg0) {
        boolean checked[] = new boolean[arg0.length()];
        for(int i = 0; i < arg0.length(); i++) {
            if(Functions.isRussianSymbol(arg0.charAt(i)) && !checked[i]) {
                Functions.check(arg0.charAt(i), checked, arg0);
                arg0.append(". ").append(Functions.countOfSymbol(arg0.charAt(i), arg0));
            }
        }
    }
    
}