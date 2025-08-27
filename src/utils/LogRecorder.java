package utils;

import configs.message.Ingredient;
import managers.ConverterManager;
import managers.messageBuild.MessageBuilder;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;

// [ LogRecorder 클래스 설명 ]
// - LogRecorder는 사용자의 프로그램 이용에 대한 기록을 관리하기 위한 클래스임다.

// [ 메모 ]
// - logRepo에 저장될 log의 개수는 30개로 제한합니다. -> add()에서 size()를 체크해 조절합니다.

public class LogRecorder {
    private static final LinkedList<String> logRepo = new LinkedList<>();

    public static void record(Ingredient type, String msg) {
        logRepo.addLast(String.format(type.getFormat(),ConverterManager.stringDateTime.convertFrom(LocalDateTime.now()),msg));
        if (logRepo.size() >= 30) logRepo.removeFirst();
    }

    public static String getRecentLogs() {
        return logRepo.stream().sorted(Comparator.reverseOrder()).limit(5).reduce(MessageBuilder::integrate).orElseGet(()->"기록된 활동이 없습니다!");
    }

}




