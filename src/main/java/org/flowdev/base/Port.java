package org.flowdev.base;


/**
 * This is the generic version of an EBC port. It works on any type.
 * 
 * @author ole
 * 
 * @param <T> the type of the data transported by this port.
 */
public interface Port<T> {
	void send(T data);
}
