package org.flowdev.base.op;

import org.flowdev.base.Port;


public abstract class Check<T, C> extends Configure<C> {
	private Port<T> out;
	private Port<T> errorOut;
	private final Port<T> in = new Port<T>() {
		@Override
		public void send(T data) {
			if (isOk(data)) {
				out.send(data);
			} else {
				errorOut.send(data);
			}
		}
	};

	protected abstract boolean isOk(T data);

	public Port<T> getIn() {
		return in;
	}

	public void setOut(Port<T> out) {
		this.out = out;
	}

	public void setErrorOut(Port<T> errorOut) {
		this.errorOut = errorOut;
	}
}
