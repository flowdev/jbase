package org.flowdev.base;

/**
 * Generic setter. It sets subdata in data and returns something of type &lt;OutputType&gt;.
 * It should be implemented as lambda expression.
 * Implementations allow parameterization of generic components.
 * 
 * @author ole
 */
public interface Setter<ValueType, InputType, OutputType> {
    OutputType set(InputType data, ValueType subdata);
}
