package ru.nsu.dyuganov.ris1;

import org.apache.commons.cli.ParseException;
import org.apache.commons.compress.compressors.CompressorException;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws ParseException {
        Config config = CliParser.parse(args);
        if (config == null) {
            log.error("No args found");
            CliParser.printHelp();
            return;
        }

        try {
            log.debug("Trying to get input stream");
            var inputStream = InputStreamInitializer.getInputStream(config);
        } catch (IOException e) {
            log.error("Error while creating InputStream");
            throw new RuntimeException(e);
        } catch (CompressorException e) {
            log.error("Error while decompressing file");
            throw new RuntimeException(e);
        }
        log.info("File opened successfully");

        // node reader
    }
}
