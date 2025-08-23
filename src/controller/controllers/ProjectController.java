package controller.controllers;

import controller.*;
import model.project.Task;

import java.util.Collection;
import java.util.Map;

// [ ProjectController 클래스 설명 ]
// - ProjectController는 Task 인스턴스들에 대한 CRUD 조작을 처리하기 위한 Controller 기반의 클래스임다.
// - Project 클래스를 통해서만 인스턴스를 생성하고, Project의 HashMap 필드인 tasks를 참조하는 종속성을 갖슴다.

// [ 메모 ]
// - add() 메서드 실행 마다 TID를 생성하고, index 필드의 값을 1씩 implement 해야 합니다.
// - Task 인스턴스 생성 시 입력값이 보류된 필드에 대한 처리 방법의 고민이 필요합니다.
//      ex) "비품구매/4/@/20270722" -> 보류된 pic 필드에 null을 할당...? Optional.empty()...?
// - "업무조회" 기능 관련해, 보류된 필드에 대한 출력값 설정이 필요합니다.
//      ex) name=비품구매/type=기타/status=진행/pic=null" -> 콘솔화면에서 pic는 "미정"으로...?

public class ProjectController extends Controller implements Adder, Getter<Task>, Updater, Remover {
    private Map<String, Task> tasks;
    private long index = 1;

    public ProjectController(Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void add(String[] infos) {
    }

    @Override
    public Task get(String tid) {
        return null;
    }

    @Override
    public void update(String[] changes) {
    }

    @Override
    public void remove(String tid) {
    }

    public Collection<Task> getAll() {
        return this.tasks.values();
    }
}
