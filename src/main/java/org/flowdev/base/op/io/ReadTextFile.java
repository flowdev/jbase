package org.flowdev.base.op.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.flowdev.base.Getter;
import org.flowdev.base.Setter;
import org.flowdev.base.data.EmptyConfig;
import org.flowdev.base.op.Filter;


/**
 * This operation reads the content of a file as a UTF-8 text into a string.
 */
public class ReadTextFile<T> extends Filter<T, EmptyConfig> {
	public static class Params<T> {
		public Getter<T, String> getFileName;
		public Setter<T, String> setFileContent;
	}

	public static final Charset UTF8 = Charset.forName("UTF-8");

	private final Params<T> params;

	public ReadTextFile(Params<T> params) {
		this.params = params;
	}

	protected T filter(T data) {
		try {
			String fileName = params.getFileName.get(data);

			Path path = Paths.get(fileName);
			byte[] buf = Files.readAllBytes(path);
			String content = new String(buf, UTF8);

			params.setFileContent.set(data, content);
			return data;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
