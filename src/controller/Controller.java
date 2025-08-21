package controller;

public interface Controller<T> {
	void add(String[] args);

	T get(String id);

	void update(String[] args);

	void remove(String id);
}
