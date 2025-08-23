package configs.validation;

// [ RegEx 개요 ]
// - 검증에 활용할 정규식 패턴들을 보관하기 위한 Config 파일임다.
// - 호출할 때엔 "RegEx.필드명.getPattern()"으로 정규식 String을 얻을 수 있습니다.

// [ 메모 ]
// - 필드의 작명 방식은 다음과 같슴다. "사용될기능_검증항목"
//   ex) ADD_TASK_NAME("^[가-힣a-zA-Z0-9]$") -> "업무등록" 입력값 중 "업무명"을 검증하는 정규식
//   지금은 테스트를 위한 일괄 검증용 필드가 작성돼있슴다...

public enum RegEx {
    ADD_TASK("^[가-힣a-zA-Z0-9]{1,10}/[1234]/([가-힣]{1,4}|@)/((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])|@)$");

    private String regEx;

    private RegEx(String regEx) {
        this.regEx = regEx;
    }

    public String getPattern() {
        return regEx;
    }
}
