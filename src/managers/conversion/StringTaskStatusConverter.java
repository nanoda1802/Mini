package managers.conversion;

import configs.project.TaskStatus;

// [ StringTaskStatusConverter 클래스 설명 ]
// - StringTaskStatusConverter는 String과 TaskStatus 타입 간의 변환용 클래스입니다.

public class StringTaskStatusConverter extends Converter<String, TaskStatus>{
    @Override
    public TaskStatus convertTo(String target) {
        // [메모] 입력은 1234로 받기 때문에, 인덱스로는 1을 감산해줘야 함
        return TaskStatus.values()[Integer.parseInt(target)-1];
    }

    @Override
    public String convertFrom(TaskStatus target) {
        return target.ordinal()+"";
    }
}
