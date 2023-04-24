package ru.nsu.dyuganov.ris1;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputStreamInitializer {
    private static final Logger log
            = LoggerFactory.getLogger(InputStreamInitializer.class);

    public static InputStream getStream(Config configuration) throws IOException {
        Path inputPath = Path.of(configuration.getPath());
        InputStream inputStream = new BufferedInputStream(Files.newInputStream(inputPath));

        if (configuration.isArchive()) {
            try {
                inputStream =
                        new CompressorStreamFactory().createCompressorInputStream(inputStream);
            } catch (CompressorException e) {
                log.error("Can not create compress stream", e);
                throw new IOException("Can not unarchive " + configuration.getPath());
            }
        }
        return inputStream;
    }
}
