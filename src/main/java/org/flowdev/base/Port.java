package org.flowdev.base;

/**
 * Generic Flow-oriented port. It works on any type.
 * It should be implemented as a lambda expression.
 *
 * @author ole
 *
 * @param <T> the type of the data transported by this port.
 */
public interface Port<T> {
	void send(T data);
}
