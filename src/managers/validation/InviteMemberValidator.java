package managers.validation;

import configs.validation.FailureReason;
import configs.validation.RegEx;
import utils.Pair;

import java.util.regex.Pattern;
// [InviteMemberValidator 클래스]
// - 팀원 초대 기능의 입력값에 대한 유효성 검사용 클래스입니다.


public class InviteMemberValidator extends Validator {
    @Override
    public Pair<Boolean, String> check(String target) {
        // [1] 입력값 구조 검사 (__/__)
        boolean isStructureValid = Pattern.matches(RegEx.MEMBER_INVITE_STRUCTURE.getPattern(), target);
        if(!isStructureValid) {
            return new Pair<>(false, FailureReason.MEMBER_INVITE_STRUCTURE.getReason());
        }
        // [2] 항목별 유효성 검사
        String[] fields = target.split("/");
        boolean isNameValid = Pattern.matches(RegEx.MEMBER_NAME.getPattern(), fields[0]);
        boolean isAuthValid = Pattern.matches(RegEx.MEMBER_AUTH.getPattern(), fields[1]);

        if (!isNameValid){
            return new Pair<>(false, FailureReason.MEMBER_NAME.getReason());
        }
        if (!isAuthValid) {
            return new Pair<>(false, FailureReason.MEMBER_AUTH.getReason());
        }

        // [3] 검사 무사 통과시 결과 반환
        return new Pair<>(true,target);
    }
}
