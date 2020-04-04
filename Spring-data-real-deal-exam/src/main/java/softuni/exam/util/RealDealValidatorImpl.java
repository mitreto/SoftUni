package softuni.exam.util;

import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RealDealValidatorImpl {

    private ValidationUtil validationUtil;

    @Autowired
    public RealDealValidatorImpl(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }
}
