package ru.nsu.dyuganov.ris1;

import org.apache.commons.cli.*;

public class CliParser {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CliParser.class);
    private static final String FILE_OPTION = "f";
    private static final String IS_ARCHIVE_OPTION = "u";


    public static Config parse(String[] args) throws ParseException {
        final Options options = new Options()
                .addOption(FILE_OPTION, true, "Path to osm data file")
                .addOption(IS_ARCHIVE_OPTION, false, "File is needed to be unpacked");
        CommandLine cmd = new DefaultParser().parse(options, args);
        log.info("Parsing args");
        if (!cmd.hasOption(FILE_OPTION)) {
            log.warn("Args does not contain file option");
            return null;
        }
        return new Config(cmd.getOptionValue(FILE_OPTION), cmd.hasOption(IS_ARCHIVE_OPTION));
    }

    public static void printHelp(){
        System.out.println("Config help: \n-f [file path] \nif file is archive, add -u");
    }
}
