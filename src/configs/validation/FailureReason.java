package configs.validation;

// [ FailureReason 개요 ]
// - 검증 실패시 출력할 실패 사유 메세지들을 보관하기 위한 Config 파일임다.
// - 호출할 때엔 "FailureReason.필드명.getReason()"으로 사유 String을 얻을 수 있습니다.

// [ 메모 ]
// - 필드의 작명 방식은 다음과 같슴다. "사용될기능_실패한항목"
//   ex) ADD_TASK_NAME("업무명은 10 글자 이하의 한국어+영어+숫자만 입력 가능합니다.")
//   지금은 테스트를 위한 아무말 임시 필드가 작성돼있슴다...

public enum FailureReason {
    ADD_TASK_TEMP("검증 실패!");

    private String reason;

    private FailureReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}