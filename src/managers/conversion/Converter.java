package managers.conversion;

public abstract class Converter<T, R> {
	public abstract T convertTo(R target);

	public abstract R convertFrom(T target);
}
