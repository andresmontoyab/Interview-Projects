package co.ceiba.uploadVideo.uploadVideoService.dto;

public class ClaimDTO {

    String id;

    String idInsurance;
    String name;
    String duration;
    String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIdInsurance() {
        return idInsurance;
    }

    public void setIdInsurance(String idInsurance) {
        this.idInsurance = idInsurance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
