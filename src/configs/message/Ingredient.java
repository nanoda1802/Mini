package configs.message;

// [ Ingredient 개요 ]
// - 재료 메세지 제작 단계에서 활용할 문자열 format들을 보관하는 enum입니다.
// - 호출할 때엔 "Ingredient.필드명.getFormat()"으로 format 문자열을 얻을 수 있습니다.

// [ 메모 ]
//   - 완성된 Log 메세지의 예시는 다음과 같슴다.
//   ex) "20240923 15:22  |  새로운 팀원 [홍길동] 님을 초대했습니다."

public enum Ingredient {
    OVERVIEW("""
            [업무상태] 완료 %s | 진행 %s | 대기 %s | 전체 %s
            [분업평가] %s
                     ( ■ = 담당업무 보유자 | □ = 담당업무 미보유자 )
            """),
    TASK_LIST("""
            = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
             No.%s [ %s ]
              유형 : %s  |  상태 : %s  |  담당자 : %s
              기간 : %s ~ %s
            """),
    TASK_LIST_FAILED("\" 조건에 부합하는 Task가 존재하지 않습니다! \""),
    LOG_INVITE_MEMBER("  |  새로운 팀원 [ %s ] 님을 초대했습니다."),
    LOG_UPDATE_MEMBER_INFO("  |  [ %s ] 님의 정보를 수정했습니다."),
    LOG_DISMISS_MEMBER("  |  [ %s ] 님을 팀에서 해임했습니다."),
    LOG_ADD_TASK("  |  신규 업무 [ %s ]을(를) 등록했습니다."),
    LOG_UPDATE_TASK_INFO("  |  [ %s ] 업무 정보를 수정했습니다."),
    LOG_REMOVE_TASK("  |  [ %s ] 업무 정보를 제거했습니다.");

    private String format;

    private Ingredient(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
