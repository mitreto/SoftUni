package softuni.exam.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface LocalDateParser {

    LocalDate parseLocalDateFromString(String date, String pattern);

    LocalDateTime parseLocalDateTimeFromString(String dateTime, String pattern);
}
