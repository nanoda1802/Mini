package managers;

import managers.conversion.Converter;
import managers.conversion.StringDateConverter;

public class ConverterManager extends Manager<Converter> {
	Converter sdc = new StringDateConverter();
	// converter 클래스 추가될 때마다 필드 추가
}
