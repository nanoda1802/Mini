package configs.validation;

// [ FailureReason 개요 ]
// - 검증 실패시 출력할 실패 사유 메세지들을 보관하기 위한 Config 파일임다.
// - 호출할 때엔 "FailureReason.필드명.getReason()"으로 사유 String을 얻을 수 있습니다.

// [ 메모 ]
// - 필드의 작명 방식은 다음과 같슴다. "사용될기능_실패한항목"
//   ex) ADD_TASK_NAME("업무명은 10 글자 이하의 한국어+영어+숫자만 입력 가능합니다.")
//   지금은 테스트를 위한 아무말 임시 필드가 작성돼있슴다...

public enum FailureReason {
    ADD_TASK_STRUCTURE("\"항목1 / 항목2 / 항목3 / 항목4\" 형태로 입력해주세요."),
    ADD_TASK_NAME("업무명은 15자 이하의 한국어,영어,숫자만 입력 가능합니다."),
    ADD_TASK_TYPE("유형은 숫자 1,2,3,4 중 하나만 입력 가능합니다."),
    ADD_TASK_ASSIGNEE("담당자ID는 m02, m33 등의 형태로만 입력 가능합니다. 보류하고 싶으시면 @을 입력해주세요."),
    ADD_TASK_DUE("마감일은 yyyymmdd 형태의 숫자만 입력 가능합니다. 보류하고 싶으시면 @을 입력해주세요."),
    UPDATE_TASK_INFO_STRUCTURE("\"항목1 / 항목2 / 항목3 / 항목4 / 항목5\" 형태로 입력해주세요."),
    UPDATE_TASK_INFO_TID("업무ID는 t02, t33 등의 형태로만 입력 가능합니다."),
    UPDATE_TASK_INFO_NAME("업무명은 15자 이하의 한국어,영어,숫자만 입력 가능합니다. 수정하지 않을 항목이면 @을 입력해주세요."),
    UPDATE_TASK_INFO_STATUS("상태는 숫자 1,2,3 중 하나만 입력 가능합니다. 수정하지 않을 항목이면 @을 입력해주세요."),
    UPDATE_TASK_INFO_ASSIGNEE("담당자ID는 m02, m33 등의 형태로만 입력 가능합니다. 수정하지 않을 항목이면 @을 입력해주세요."),
    UPDATE_TASK_INFO_DUE("마감일은 yyyymmdd 형태의 숫자만 입력 가능합니다. 수정하지 않을 항목이면 @을 입력해주세요."),
    BROWSE_TASKS_STRUCTURE("\"기준1,조건1 / 기준2,조건2 / ...\" 형태로 입력해주세요."),
    BROWSE_TASKS_CRITERIA("기준은 숫자 1,2,3 중 하나만 입력 가능합니다."),
    BROWSE_TASKS_DUPLICATED("하나의 기준을 여러 번 설정할 수 없습니다."),
    BROWSE_TASKS_CONDITION_TYPE("유형 조건은 숫자 1,2,3,4 중 하나만 입력 가능합니다."),
    BROWSE_TASKS_CONDITION_STATUS("상태 조건은 숫자 1,2,3 중 하나만 입력 가능합니다."),
    BROWSE_TASKS_CONDITION_MID("팀원ID는 m02, m33 등의 형태로만 입력 가능합니다.");

    private String reason;

    private FailureReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}