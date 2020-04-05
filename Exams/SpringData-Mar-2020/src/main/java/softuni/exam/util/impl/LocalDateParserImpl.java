package softuni.exam.util.impl;

import org.springframework.stereotype.Component;
import softuni.exam.util.LocalDateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateParserImpl implements LocalDateParser {

//    LocalDateTime localDateTime =
//            LocalDateTime.parse("08/03/2020 00:09:02",
//                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH/mm/ss"));

    @Override
    public LocalDate parseLocalDateFromString(String date) {

        return LocalDate.parse(date,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public LocalDateTime parseLocalDateTimeFromString(String dateTime) {

        return LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH/mm/ss"));
    }


}
