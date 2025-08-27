package managers.validation;

import configs.validation.FailureReason;
import configs.validation.RegEx;
import utils.Pair;

import java.util.regex.Pattern;

// [UpdateMemberInfoValidator 클래스]
// - 팀원 수정 기능의 입력값에 대한 유효성 검사용 클래스입니다.

public class UpdateMemberInfoValidator extends Validator {

    @Override
    public Pair<Boolean, String> check(String target) {
        // target = Mid / Name / Auth / Tid
        // [1] 입력값 구조 검사 (문자열/문자열/문자열/문자열)
        boolean isStructureValid = Pattern.matches(RegEx.MEMBER_UPDATE_STRUCTURE.getPattern(),  target);
        if (!isStructureValid) {
            return new Pair<>(false,FailureReason.MEMBER_UPDATE_STRUCTURE.getReason());
        }
        // [2] 항목별 유효성 검사
        String[] fields = target.split("/");
        boolean isMidValid =  Pattern.matches(RegEx.MEMBER_MID.getPattern(),  fields[0]);
        boolean isNameValid = "@".equals(fields[1]) || Pattern.matches(RegEx.UPDATE_TASK_INFO_NAME.getPattern(), fields[1]);
        boolean isAuthValid = "@".equals(fields[2]) || Pattern.matches(RegEx.MEMBER_AUTH.getPattern(),  fields[2]);

        String[] tids =  fields[3].split(",");
        boolean isTidValid = true;
        for(String tid : tids) {
            isTidValid = "@".equals(tid) || Pattern.matches(RegEx.TASK_TID.getPattern(), tid);
            if (!isTidValid) {
                return new Pair<>(false,FailureReason.TASK_TID.getReason());
            }
        }
        if (!isMidValid) {
            return new Pair<>(false,FailureReason.MEMBER_MID.getReason());
        }
        if (!isNameValid) {
            return new Pair<>(false,FailureReason.MEMBER_NAME.getReason());
        }
        if (!isAuthValid) {
            return new Pair<>(false,FailureReason.MEMBER_AUTH.getReason());
        }


        // [3] 검사 무사 통과시 결과 반환
        return new Pair<>(true,target);
    }
}
