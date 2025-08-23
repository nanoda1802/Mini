package configs.message;

// [ Logs 개요 ]
// - LogRepo에 보관될 메세지들의 format을 보관할 Config 파일임다.
// - 호출할 때엔 "Logs.필드명.getFormat()"으로 format 문자열을 얻을 수 있습니다.

// [ 메모 ]
// - 필드의 작명 방식은 다음과 같슴다. "기록할기능"
//   ex) INVITE_MEMBER("|  새로운 팀원 [%s] 님을 초대했습니다.")
//   - 완성된 Log 메세지의 예시는 다음과 같슴다.
//   ex) "20240923 15:22  |  새로운 팀원 [홍길동] 님을 초대했습니다."

public enum Logs {
    INVITE_MEMBER("  |  새로운 팀원 [ %s ] 님을 초대했습니다."),
    UPDATE_MEMBER_INFO("  |  [ %s ] 님의 정보를 수정했습니다."),
    DISMISS_MEMBER("  |  [ %s ] 님을 팀에서 해임했습니다."),
    ADD_TASK("  |  신규 업무 [ %s ]을(를) 등록했습니다."),
    UPDATE_TASK_INFO("  |  [ %s ] 업무 정보를 수정했습니다."),
    REMOVE_TASK("  |  [ %s ] 업무 정보를 제거했습니다.");

    private String format;

    private Logs(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}