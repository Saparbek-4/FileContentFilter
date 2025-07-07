package org.sapar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterManager {
    private BufferedWriter intWriter = null;
    private BufferedWriter floatWriter = null;
    private BufferedWriter stringWriter = null;

    private String basePath;
    private String prefix;
    private boolean appendMode;

    public FileWriterManager(String basePath, String prefix, boolean appendMode) {
        this.basePath = basePath != null && !basePath.isEmpty() ? basePath : ".";
        this.prefix = prefix != null ? prefix : "";
        this.appendMode = appendMode;
    }

    private BufferedWriter getWriter(String type) throws IOException {
        String fileName = prefix + type + ".txt";
        File file = new File(basePath, fileName);
        return new BufferedWriter(new FileWriter(file, appendMode));
    }

    public void writeInteger(String line) throws IOException {
        if (intWriter == null) {
            intWriter = getWriter("integers");
        }
        intWriter.write(line);
        intWriter.newLine();
    }

    public void writeFloat(String line) throws IOException {
        if (floatWriter == null) {
            floatWriter = getWriter("floats");
        }
        floatWriter.write(line);
        floatWriter.newLine();
    }

    public void writeString(String line) throws IOException {
        if (stringWriter == null) {
            stringWriter = getWriter("strings");
        }
        stringWriter.write(line);
        stringWriter.newLine();
    }

    public void closeAll() {
        try {
            if (intWriter != null) intWriter.close();
            if (floatWriter != null) floatWriter.close();
            if (stringWriter != null) stringWriter.close();
        } catch (IOException e) {
            System.err.println("Error closing writers: " + e.getMessage());
        }
    }
}
