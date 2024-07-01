package pckg;
import java.util.Comparator;

public class EnrolleeComparator implements Comparator<Enrollee> {
    public int compare(Enrollee enrollee1, Enrollee enrollee2) {
        if(Double.compare(enrollee1.getAverageScore(), enrollee2.getAverageScore()) > 0) {
            return 1;
        } else if(Double.compare(enrollee1.getAverageScore(),  enrollee2.getAverageScore()) == 0) {
            if(Integer.compare(enrollee1.getFirstExamScore(), enrollee2.getFirstExamScore()) > 0) {
                return 1;
            } else if(Integer.compare(enrollee1.getFirstExamScore(),  enrollee2.getFirstExamScore()) == 0) {
                if(Integer.compare(enrollee1.getSecondExamScore(), enrollee2.getSecondExamScore()) > 0) {
                    return 1;
                } else if(Integer.compare(enrollee1.getSecondExamScore(),  enrollee2.getSecondExamScore()) == 0) {
                    if(Integer.compare(enrollee1.getThirdExamScore(), enrollee2.getThirdExamScore()) > 0) {
                        return 1;
                    } else if(Integer.compare(enrollee1.getThirdExamScore(),  enrollee2.getThirdExamScore()) == 0) {
                        if(Double.compare(enrollee1.getCertificateScore(), enrollee2.getCertificateScore()) > 0) {
                            return 1;
                        } else if(Double.compare(enrollee1.getCertificateScore(),  enrollee2.getCertificateScore()) == 0) {
                            return 0;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
