package configs.conversion;

import java.time.format.DateTimeFormatter;

public enum DateFormat {
    yearToMinute (DateTimeFormatter.ofPattern("yyMMdd HH:mm")),
    yearToDay (DateTimeFormatter.ofPattern("yyyyMMdd")),
    yearToMonth (DateTimeFormatter.ofPattern("yyyyMM"));

    private DateTimeFormatter format;

    private DateFormat(DateTimeFormatter format) {
        this.format = format;
    }

    public DateTimeFormatter getFormat() {
        return format;
    }
}
