package org.flowdev.base.op.io;

import org.flowdev.base.Getter;
import org.flowdev.base.data.NoConfig;
import org.flowdev.base.op.Consume;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.flowdev.base.op.io.ReadTextFile.UTF8;

public class WriteTextFileJava6<T> extends Consume<T, NoConfig> {
    public static class Params<T> {
        public Getter<T, String> getFileName;
        public Getter<T, String> getFileContent;
    }

    private final Params<T> params;

    public WriteTextFileJava6(Params<T> params) {
        this.params = params;
    }

    @Override
    protected void consume(T data) throws IOException {
        String fileName = params.getFileName.get(data);
        String fileContent = params.getFileContent.get(data);

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName), UTF8);
        osw.write(fileContent);
        osw.close();
    }

}
