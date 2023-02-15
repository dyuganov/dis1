package ru.nsu.dyuganov.ris1;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputStreamInitializer {
    public static InputStream getInputStream(Config config) throws IOException, CompressorException {
        InputStream result = new BufferedInputStream(Files.newInputStream(Path.of(config.getPath())));
        if (config.isArchive()){
            result = new CompressorStreamFactory().createCompressorInputStream(result);
        }
        return result;
    }
}
