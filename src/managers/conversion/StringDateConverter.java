package managers.conversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// [ StringDateConverter 클래스 설명 ]
// - StringDateConverter는 String과 LocalDateTime 타입 간의 변환용 클래스입니다.

public class StringDateConverter extends Converter<String, LocalDateTime> {
    // [임시] 변환에 활용할 Formatter 필드들
    private final DateTimeFormatter yearMinute = DateTimeFormatter.ofPattern("yyMMdd HH:mm");
    private final DateTimeFormatter yearDay = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final DateTimeFormatter yearMonth = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public LocalDateTime convertTo(String target) {
        return null;
    }

    @Override
    public String convertFrom(LocalDateTime target) {
        return null;
    }

}