package ru.nsu.dyuganov.ris1;

import org.apache.commons.cli.ParseException;
import org.apache.commons.compress.compressors.CompressorException;
import ru.nsu.dyuganov.ris1.Node.Node;
import ru.nsu.dyuganov.ris1.Node.NodeReader;
import ru.nsu.dyuganov.ris1.Node.NodeReaderException;
import ru.nsu.dyuganov.ris1.Node.XmlNodeReader;
import ru.nsu.dyuganov.ris1.Statistics.OsmStatistics;
import ru.nsu.dyuganov.ris1.Statistics.Statistics;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import ru.nsu.dyuganov.ris1.Statistics.StatisticsWriter;

public class Main {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Config config = null;
        try {
            config = CliParser.parse(args);
        } catch (ParseException e) {
            log.error("Parse error");
            throw new RuntimeException(e);
        }
        if (config == null) {
            log.error("No args found");
            CliParser.printHelp();
            return;
        }

        InputStream inputStream = null;
        try {
            inputStream = InputStreamInitializer.getStream(config);
        } catch (IOException e) {
            log.error("Getting input stream error");
            throw new RuntimeException(e);
        }

        try {
            NodeReader nodeReader = new XmlNodeReader(inputStream);
            Statistics<Node> osmStatistic = new OsmStatistics();
            while (nodeReader.hasNext()) {
                osmStatistic.add(nodeReader.read());
            }
            Config finalConfig = config;
            StatisticsWriter statisticsWriter = stat -> {
                log.info(String.format("Osm statistic for %s:\n%s", finalConfig.getPath(), stat));
            };
            statisticsWriter.write(osmStatistic);
        } catch (XMLStreamException e) {
            log.error("Can not parse XML", e);
        } catch (NodeReaderException e) {
            throw new RuntimeException(e);
        }
    }
}
