package softuni.exam.models.dtos;

import softuni.exam.models.entities.Seller;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerSeedRootDto {

    @XmlElement(name = "seller")
    private List<SellerSeedDto> sellers;

    public SellerSeedRootDto() {
    }

    @NotNull
    public List<SellerSeedDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SellerSeedDto> sellers) {
        this.sellers = sellers;
    }
}
