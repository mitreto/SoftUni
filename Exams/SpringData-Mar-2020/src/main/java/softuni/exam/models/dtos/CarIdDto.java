package softuni.exam.models.dtos;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarIdDto {

    @XmlElement(name = "id")
    private Long id;

    public CarIdDto() {
    }

    @NotNull(message = "Invalid car")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
