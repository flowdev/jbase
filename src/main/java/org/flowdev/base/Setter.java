package org.flowdev.base;

/**
 * Generic setter.
 * It sets <U> in <T>.
 * In future JDKs it should be implemented as lambda expression.
 * Implementations allow configuration of generic components.
 *
 * @author ole
 */
public interface Setter<T, U> {
	void set(T data, U subdata);
}
