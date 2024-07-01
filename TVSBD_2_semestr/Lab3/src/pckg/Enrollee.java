package pckg;
public class Enrollee {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    private Double certificateScore;
    private Integer firstExamScore;
    private Integer secondExamScore;
    private Integer thirdExamScore;
    private Double averageScore;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Double getCertificateScore() {
        return certificateScore;
    }

    public Integer getFirstExamScore() {
        return firstExamScore;
    }

    public Integer getSecondExamScore() {
        return secondExamScore;
    }

    public Integer getThirdExamScore() {
        return thirdExamScore;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setCertificateScore(Double certificateScore) {
        this.certificateScore = certificateScore;
        if(secondExamScore != null && thirdExamScore != null && firstExamScore != null) {
            setAverageScore();
        }
    }

    public void setFirstExamScore(Integer firstExamScore) {
        this.firstExamScore = firstExamScore;
        if(secondExamScore != null && thirdExamScore != null && certificateScore != null) {
            setAverageScore();
        }
    }

    public void setSecondExamScore(Integer secondExamScore) {
        this.secondExamScore = secondExamScore;
        if(firstExamScore != null && thirdExamScore != null && certificateScore != null) {
            setAverageScore();
        }
    }

    public void setThirdExamScore(Integer thirdExamScore) {
        this.thirdExamScore = thirdExamScore;
        if(secondExamScore != null && firstExamScore != null && certificateScore != null) {
            setAverageScore();
        }
    }

    private void setAverageScore() {
        averageScore = (certificateScore + firstExamScore + secondExamScore + thirdExamScore) / 4;
    }
}