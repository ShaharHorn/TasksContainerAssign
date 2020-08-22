package com.taskscontainer.mappers;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapper {

    private final DateTimeFormatter dateFormmater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public OffsetDateTime stringConvertToUTCdate(String date) throws ParseException {
        LocalDateTime dateTime = LocalDateTime.parse(date, dateFormmater);
        OffsetDateTime offsetDateTime = dateTime.atZone(ZoneId.systemDefault()).
                withZoneSameInstant(ZoneOffset.UTC).toOffsetDateTime();
        return offsetDateTime;
    }

    public String offsetDateConvertToString(OffsetDateTime date)
    {
        return date.toString();
    }
}
