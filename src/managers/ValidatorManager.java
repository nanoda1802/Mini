package managers;

import managers.validation.IdValidator;
import managers.validation.Validator;

public class ValidatorManager extends Manager<Validator> {
	Validator idv = new IdValidator();
	// validator 클래스 추가될 때마다 필드 추가
}
