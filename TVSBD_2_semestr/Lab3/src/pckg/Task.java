package pckg;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.SQLException;
public class Task {
    public static String result;

    public static void findResult(Integer placeCount) {
        try {
            Collection<Enrollee> buff = Storage.readAll();
            ArrayList<Enrollee> objects = new ArrayList<>();

            for(Enrollee enrollee: buff) {
                objects.add(enrollee);   
            }

            objects.sort(new EnrolleeComparator());

            StringBuilder str = new StringBuilder();
            for(int i = objects.size() - 1, j = 0; j < placeCount && i >= 0; i--) {
                Enrollee enrollee = objects.get(i);
                if(enrollee.getFirstExamScore() >= 4 && enrollee.getSecondExamScore() >= 4 && enrollee.getThirdExamScore() >= 4) {
                    str.append(enrollee.getId()).append(" ");
                    j++;
                }
            }
            result = str.toString();
        } catch(SQLException e) {
            result = "";
        }
    }
}
