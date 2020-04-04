package softuni.exam.models.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.exam.models.entities.Car;

import java.time.LocalDateTime;

public class PictureSeedDto {

    @Expose
    private String name;

    @Expose
    private String dateAndTime;

    @Expose
    private Long car;

    public PictureSeedDto() {
    }

    @Length(min = 2, max = 19, message = "Must be between 2 to 20 exclusive")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
