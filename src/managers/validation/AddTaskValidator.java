package managers.validation;

import configs.validation.FailureReason;
import configs.validation.RegEx;
import utils.Pair;

import java.util.regex.Pattern;

// [ AddTaskValidator 클래스 설명 ]
// - AddTaskValidator는 "업무등록"의 입력값에 대한 유효성 검사용 클래스입니다.

public class AddTaskValidator extends Validator {
    @Override
    public Pair<Boolean, String> check(String target) {
        // [1] 일차적으로 입력값의 구조를 점검 ("문자열1/문자열2/문자열3/문자열4")
        boolean isRightStructure = Pattern.matches(RegEx.ADD_TASK_STRUCTURE.getPattern(), target);
        if (!isRightStructure) {
            return new Pair<>(false, FailureReason.ADD_TASK_STRUCTURE.getReason());
        }
        // [2] 항목별 유효성 검사 진행
        String[] fields = target.split("/");

        boolean isRightName = Pattern.matches(RegEx.ADD_TASK_NAME.getPattern(), fields[0]);
        if (!isRightName) {
            return new Pair<>(false, FailureReason.ADD_TASK_NAME.getReason());
        }
        boolean isRightType = Pattern.matches(RegEx.ADD_TASK_TYPE.getPattern(), fields[1]);
        if (!isRightType) {
            return new Pair<>(false, FailureReason.ADD_TASK_TYPE.getReason());
        }
        boolean isRightAssignee = Pattern.matches(RegEx.ADD_TASK_ASSIGNEE.getPattern(), fields[2]);
        if (!isRightAssignee) {
            return new Pair<>(false, FailureReason.ADD_TASK_ASSIGNEE.getReason());
        }
        boolean isRightDue = Pattern.matches(RegEx.ADD_TASK_DUE.getPattern(), fields[3]);
        if (!isRightDue) {
            return new Pair<>(false, FailureReason.ADD_TASK_DUE.getReason());
        }

        // [3] 검증된 입력값을 Pair 클래스로 감싸 반환한다.
        return new Pair<>(true,target);
    }
}