package managers.validation;

import configs.validation.FailureReason;
import configs.validation.RegEx;
import utils.Pair;

import java.util.regex.Pattern;

// [ AddTaskValidator 클래스 설명 ]
// - AddTaskValidator는 "업무등록"의 입력값에 대한 유효성 검사용 클래스입니다.

public class AddTaskValidator extends Validator {
    // [임시] 테스트 위해서 모든 필드 한 번에 검사했고, 실패 사유도 "검증 실패!"로 퉁쳐져있음
    @Override
    public Pair<Boolean, String> check(String target) {
        // [1] Pattern.matches() 를 통해 정규식과 검증대상을 비교합니다.
        boolean isValidate = Pattern.matches(RegEx.ADD_TASK.getPattern(), target);
        // [2] 검증 결과가 true면 대상 문자열을 그대로 할당, false면 검증 실패 사유를 할당합니다.
        String respond = isValidate ? target : FailureReason.ADD_TASK_TEMP.getReason(); //
        // [3] 검증 결과를 Pair 클래스로 감싸 반환한다.
        return new Pair<>(isValidate, respond);

    }
}