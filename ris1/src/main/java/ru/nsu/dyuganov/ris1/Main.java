package ru.nsu.dyuganov.ris1;

import org.apache.commons.cli.ParseException;

import java.io.InputStream;

public class Main {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws ParseException {
        Config config = CliParser.parse(args);
        if (config == null) {
            CliParser.printHelp();
            return;
        }

    }
}
