package pckg;

public class Ticket {
    private Integer id;
    private Integer doctorId;
    private String begTime;
    private String duration;
    private Boolean isFree;
    private String patientName;
    private String patientSurname;
    private String patienPatronymic;

    public Integer getId() {
        return id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public String getBegTime() {
        return begTime;
    }

    public String getDuration() {
        return duration;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getPatientPatronymic() {
        return patienPatronymic;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setBegTime(String begTime) {
        this.begTime = begTime;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public void setPatientPatronymic(String patientPatronymic) {
        this.patienPatronymic = patientPatronymic;
    }
}
