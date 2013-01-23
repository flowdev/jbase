package org.flowdev.base;

/**
 * Generic getter.
 * It gets <U> from <T>.
 * In future JDKs it should be implemented as lambda expression.
 * Implementations allow configuration of generic components.
 *
 * @author ole
 */
public interface Getter<T, U> {
	U get(T data);
}
