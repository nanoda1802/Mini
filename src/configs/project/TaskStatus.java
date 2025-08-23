package configs.project;

// [ TaskStatus 개요 ]
// - Task 클래스의 status 필드를 담당하기 위한 Config 파일임다.
// - 호출할 때엔 "TaskStatus.필드명"으로 필드 객체를 얻을 수 있습니다.
// - 각 필드는 순서대로 0부터 2의 정수 값을 갖고 있습니다.

public enum TaskStatus {
    NOT_STARTED, IN_PROGRESS, COMPLETED
}
