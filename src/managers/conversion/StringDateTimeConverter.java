package managers.conversion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// [ StringDateTimeConverter 클래스 설명 ]
// - StringDateTimeConverter는 String과 LocalDateTime 타입 간의 변환용 클래스입니다.

public class StringDateTimeConverter extends Converter<String, LocalDateTime> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");

    @Override
    public LocalDateTime convertTo(String target) {
        return LocalDateTime.parse(target, formatter);
    }
    @Override
    public String convertFrom(LocalDateTime target) {
        return formatter.format(target);
    }
}