package org.flowdev.base;

/**
 * Generic getter.
 * It gets something of type <U> from data.
 * It should be implemented as lambda expression.
 * Implementations allow configuration of generic components.
 *
 * @author ole
 */
public interface Getter<T, U> {
	U get(T data);
}
