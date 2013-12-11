package org.flowdev.base.op.io;

import org.flowdev.base.Getter;
import org.flowdev.base.Setter;
import org.flowdev.base.data.EmptyConfig;
import org.flowdev.base.op.Transform;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * This operation reads the content of a file as a UTF-8 text into a string.
 */
public class ReadTextFileJava6<T, U> extends Transform<T, U, EmptyConfig> {
    public static class Params<T, U> {
        public Getter<T, String> getFileName;
        public Setter<String, T, U> setFileContent;
    }

    public static final Charset UTF8 = Charset.forName("UTF-8");

    private final Params<T, U> params;

    public ReadTextFileJava6(Params<T, U> params) {
        this.params = params;
    }

    protected void transform(T data) throws IOException {
        String fileName = params.getFileName.get(data);

        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), UTF8);
        String content = readContent(isr);
        isr.close();

        U transformedData = params.setFileContent.set(data, content);
        outPort.send(transformedData);
    }

    private String readContent(Reader reader) throws IOException {
        StringBuilder content = new StringBuilder(80192);
        char[] buf = new char[2048];

        for (int count = reader.read(buf); count >= 0; count = reader.read(buf)) {
            content.append(buf, 0, count);
        }
        return content.toString();
    }
}
