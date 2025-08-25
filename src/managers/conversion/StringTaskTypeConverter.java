package managers.conversion;

import configs.project.TaskType;
import model.team.Team;

public class StringTaskTypeConverter extends Converter<String, TaskType>{
    @Override
    public TaskType convertTo(String target) {
        // [메모] 입력은 1234로 받기 때문에, 인덱스로는 1을 감산해줘야 함
        return TaskType.values()[Integer.parseInt(target)-1];
    }

    @Override
    public String convertFrom(TaskType target) {
        return target.ordinal()+"";
    }
}
