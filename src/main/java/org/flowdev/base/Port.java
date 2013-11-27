package org.flowdev.base;


/**
 * This is the generic version of a Flow-oriented port. It works on any type.
 * It should be implemented as lambda expression.
 *
 * @author ole
 *
 * @param <T> the type of the data transported by this port.
 */
public interface Port<T> {
	void send(T data);
}
