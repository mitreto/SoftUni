package softuni.exam.util.impl;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String dateTime) throws Exception {

        return LocalDateTime
                .parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH/mm/ss"));
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {

        return localDateTime.toString();
    }
}
