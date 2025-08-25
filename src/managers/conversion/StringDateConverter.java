package managers.conversion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// [ StringDateConverter 클래스 설명 ]
// - StringDateConverter는 String과 LocalDate 타입 간의 변환용 클래스입니다.

public class StringDateConverter extends Converter<String, LocalDate> {
    public LocalDate convertTo(String target,DateTimeFormatter format) {
        return LocalDate.parse(target,format);
    }

    public String convertFrom(LocalDate target,DateTimeFormatter format) {
        return target.format(format);
    }

    // [문제] format용 매개변수가 필요해버려서 상속의 의미가 없어져버린...
    @Override
    public LocalDate convertTo(String target) {
        return null;
    }
    @Override
    public String convertFrom(LocalDate target) {
        return null;
    }
}