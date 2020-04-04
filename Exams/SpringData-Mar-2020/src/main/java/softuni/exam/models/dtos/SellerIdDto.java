package softuni.exam.models.dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerIdDto {

    @XmlElement
    private Long id;

    public SellerIdDto() {
    }

    @NotNull(message = "Invalid seller")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
