package org.flowdev.base.op.io;

import static org.flowdev.base.op.io.ReadTextFile.UTF8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.flowdev.base.Getter;
import org.flowdev.base.data.EmptyConfig;
import org.flowdev.base.op.Consumer;

public class WriteTextFile<T> extends Consumer<T, EmptyConfig> {
    public static class Params<T> {
	public Getter<T, String> getFileName;
	public Getter<T, String> getFileContent;
    }

    private final Params<T> params;

    public WriteTextFile(Params<T> params) {
	this.params = params;
    }

    @Override
    protected void consume(T data) {
	try {
	    String fileName = params.getFileName.get(data);
	    String fileContent = params.getFileContent.get(data);
	    Path path = Paths.get(fileName);

	    Files.write(path, fileContent.getBytes(UTF8));
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

    }
}