package utils;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

// [ Pair 클래스 설명 ]
// - Pair는 key와 value 단 둘의 필드를 가집니다.
// - Pair는 제너릭 클래스기 때문에, 여러 용도로 활용될 예정입니다.

// 1. MessageBuilder의 재료 매개변수로 활용
// - key에는 format으로 활용할 String이 담깁니다.
// - value에는 %d, %s 등의 재료로 쓰일 값들을 넣은 List<Object>가 담깁니다.
//      List<Object> 에는 여러 타입의 값을 동시에 보관할 수 있습니다.
//      대신 꺼내어 쓸 땐 다운캐스팅이 필요합니다.

// 2. 유효성 검사 지표로 활용
// - key에는 검증 성공 여부에 대한 Boolean이 담깁니다.
// - value에는 분기에 따라 달라지는 String이 담깁니다.
//     (성공) -> 검사 대상이었던 String 원본
//     (실패) -> 실패 사유에 대한 String

// 3. LogMessageBuilder의 재료 매개변수로 활용
// - key에는 Log 발생 시점의 LocalDateTime이 담깁니다. (추후 "yyyyMMdd HH:mm" 형태의 String으로 convert해 활용합니다.)
// - value에는 Log 내용에 대한 String이 담깁니다. value의 format은 configs.message.Logs를 참고합니다.
// - 이 유형의 Pair 인스턴스들은 model.log.LogRepo 클래스의 logs 필드에 보관됩니다.


