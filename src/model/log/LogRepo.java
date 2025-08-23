package model.log;

import controller.controllers.LogController;
import utils.Pair;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class LogRepo {
    private static final LogRepo _instance = new LogRepo();
    private Queue<Pair<LocalDateTime, String>> logs = new LinkedList<>();
    public LogController controller = new LogController(logs);

    public static LogRepo getInstance() {
        return _instance;
    }
}
