package controller.controllers;

import controller.Adder;
import controller.Controller;
import controller.Remover;
import utils.Pair;

import java.time.LocalDateTime;
import java.util.Queue;

// [ LogController 클래스 설명 ]
// - LogController는 사용자의 프로그램 이용에 대한 기록을 관리하기 위한 클래스임다.
// - LogRepo 클래스를 통해서만 인스턴스를 생성하고, LogRepo의 Queue 필드인 logs를 참조하는 종속성을 갖슴다.

// [ 메모 ]
// - Pair 클래스에 대한 설명은 utils.Pair.java 파일에 있습니다!
// - Pair의 value인 String 값은 configs.message.Logs의 format들을 기반으로 합니다.
// - Queue에 보관할 Pair의 개수는 30개로 제한합니다. -> add()에서 size()를 체크해 조절합니다.

public class LogController extends Controller implements Adder, Remover {

    private Queue<Pair<LocalDateTime, String>> logs; // 최대 30개 보관

    public LogController(Queue<Pair<LocalDateTime, String>> logs) {
        this.logs = logs;
    }

    @Override
    public void add(String[] args) {
    }

    @Override
    public void remove(String id) {
    }

}
