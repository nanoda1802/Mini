package managers.validation;

import configs.validation.FailureReason;
import configs.validation.RegEx;
import utils.Pair;

import java.util.regex.Pattern;

// [ BrowseTasksValidator 클래스 설명 ]
// - BrowseTasksValidator는 "업무정보조회"의 입력값에 대한 유효성 검사용 클래스입니다.

public class BrowseTasksValidator extends Validator {
    @Override
    public Pair<Boolean, String> check(String target) {
        // [1] 일차적으로 입력값의 구조를 점검 ("문자열1/문자열2/문자열3/문자열4")
        boolean isRightStructure = Pattern.matches(RegEx.BROWSE_TASKS_STRUCTURE.getPattern(), target);
        if (!isRightStructure) {
            return new Pair<>(false, FailureReason.BROWSE_TASKS_STRUCTURE.getReason());
        }

        // [2] 항목별 유효성 검사 진행
        String[] fields = target.split("/");

        // [메모] 기준 중복 체크용
       DuplicateCheck isDuplicate = new DuplicateCheck();

        for (String field : fields) {
            String[] entry = field.split(",");

            switch (entry[0]) {
                case "1":
                    if (isDuplicate.type) return new Pair<>(false, FailureReason.BROWSE_TASKS_DUPLICATED.getReason());
                    isDuplicate.type = true;
                    boolean isRightType = Pattern.matches(RegEx.BROWSE_TASKS_CONDITION_TYPE.getPattern(), entry[1]);
                    if (!isRightType) return new Pair<>(false, FailureReason.BROWSE_TASKS_CONDITION_TYPE.getReason());
                    break;
                case "2":
                    if (isDuplicate.status) return new Pair<>(false, FailureReason.BROWSE_TASKS_DUPLICATED.getReason());
                    isDuplicate.status = true;
                    boolean isRightStatus = Pattern.matches(RegEx.BROWSE_TASKS_CONDITION_STATUS.getPattern(), entry[1]);
                    if (!isRightStatus)
                        return new Pair<>(false, FailureReason.BROWSE_TASKS_CONDITION_STATUS.getReason());
                    break;
                case "3":
                    if (isDuplicate.mid) return new Pair<>(false, FailureReason.BROWSE_TASKS_DUPLICATED.getReason());
                    isDuplicate.mid = true;
                    boolean isRightMid = Pattern.matches(RegEx.BROWSE_TASKS_CONDITION_MID.getPattern(), entry[1]);
                    if (!isRightMid) return new Pair<>(false, FailureReason.BROWSE_TASKS_CONDITION_MID.getReason());
                    break;
                default:
                    return new Pair<>(false, FailureReason.BROWSE_TASKS_CRITERIA.getReason());
            }
        }

        // [3] 검증된 입력값을 Pair 클래스로 감싸 반환한다.
        return new Pair<>(true, target);
    }
}

// [메모] 기준 중복 체크용 간이 클래스
class DuplicateCheck {
    boolean type = false;
    boolean status = false;
    boolean mid = false;
}