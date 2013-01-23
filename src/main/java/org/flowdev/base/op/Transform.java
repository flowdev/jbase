package org.flowdev.base.op;

import org.flowdev.base.Port;


public abstract class Transform<T, U, C> extends Configure<C> {
	private Port<U> out;
	private final Port<T> in = new Port<T>() {
		@Override
		public void send(T data) {
			U result = transform(data);

			if (result != null) {
				out.send(result);
			}
		}
	};

	protected abstract U transform(T data);

	/** Called during initialization phase. */
	public Port<T> getIn() {
		return in;
	}

	/** Called during initialization phase. */
	public void setOut(Port<U> out) {
		this.out = out;
	}
}
