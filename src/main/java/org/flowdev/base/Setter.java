package org.flowdev.base;

/**
 * Generic setter. It sets <S> in <T> and returns <U>. In future JDKs it should
 * be implemented as lambda expression. Implementations allow parameterization
 * of generic components.
 * 
 * @author ole
 */
public interface Setter<ValueType, InputType, OutputType> {
    OutputType set(InputType data, ValueType subdata);
}
