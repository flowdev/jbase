package org.flowdev.base;

/**
 * Generic factory.
 * Creates a data object of class T.
 * In future JDKs it should be implemented as lambda expression.
 * Implementations allow configuration of generic components.
 *
 * @author ole
 */
public interface Creator<T> {
	T create();
}
