package managers;

import managers.conversion.Converter;
import managers.conversion.StringDateConverter;

// [ ConverterManager 클래스 설명 ]
// - ConverterManager Converter류 클래스들을 일괄 관리하고 전역에서 호출하기 위한 클래스임다.
// - 차후 프로그램 Init 과정이 생긴다면, 필드들을 순회하며 null 체크를 해보는 등 안정성을 높일 수 있습니다.

// [ 메모 ]
// - 타입별 Converter 클래스 추가될 때마다 필드 추가 (ABValidator인 경우 필드명을 AB로 작명)
// - 필드 타입은 자식 Converter로 지정 (직접 활용해보니 매번 다운캐스팅을 해야 메서드를 실행할 수 있어서 번거로웠음)

public class ConverterManager extends Manager<Converter> {
    public static StringDateConverter stringDate = new StringDateConverter();
//  [예시] public static ABConverter AB = new ABConverter();
}
