package softuni.exam.models.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class CarSeedDto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Integer kilometers;

    @Expose
    private String registeredOn;

    public CarSeedDto() {
    }


    @Length(min = 2, max = 19, message = "Must be between 2 to 20 exclusive")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Length(min = 2, max = 19, message = "Must be between 2 to 20 exclusive")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @PositiveOrZero
    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }
}
