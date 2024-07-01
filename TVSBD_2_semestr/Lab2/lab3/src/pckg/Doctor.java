package pckg;

public class Doctor {
    private Integer id;
    private String speciality;
    private String name;
    private String surname;
    private String patronymic;
    private Boolean isNarrow;
    private Integer lotNumber;
    private String begTime;
    private String endTime;
    private Integer thicketNumber;
    private String allDuration;

    public Integer getId() {
        return id;
    }

    public String getSpeciality() {
        return speciality;
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

    public Boolean getIsNarrow() {
        return isNarrow;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }

    public String getBegTime() {
        return begTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Integer getTicketNumber() {
        return thicketNumber;
    }

    public String getAllDuration() {
        return allDuration;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public void setIsNarrow(Boolean isNarrow) {
        this.isNarrow = isNarrow;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public void setBegTime(String begTime) {
        this.begTime = begTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.thicketNumber = ticketNumber;
    }

    public void setAllDuration(String allDuration) {
        this.allDuration = allDuration;
    }
}
