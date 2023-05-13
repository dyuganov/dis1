package ru.nsu.dyuganov.ris1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.dyuganov.ris1.Node.Node;
import ru.nsu.dyuganov.ris1.Node.NodeReader;
import ru.nsu.dyuganov.ris1.Node.XmlNodeReader;
import ru.nsu.dyuganov.ris1.Statistics.OsmStatistics;
import ru.nsu.dyuganov.ris1.Statistics.Statistics;

import javax.xml.stream.XMLStreamException;
import java.io.InputStream;

import ru.nsu.dyuganov.ris1.Statistics.StatisticsWriter;

public class Main {
    private static final Logger log
            = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Config config = CliParser.parse(args);
        if (config == null) {
            CliParser.printHelp();
            return;
        }

        InputStream inputStream = InputStreamInitializer.getStream(config);

        try {
            NodeReader nodeReader = new XmlNodeReader(inputStream);
            Statistics<Node> osmStatistic = new OsmStatistics();
            while (nodeReader.hasNext()) {
                osmStatistic.add(nodeReader.read());
            }
            StatisticsWriter statisticsWriter = stat -> {
                log.info(String.format("Osm statistic for %s:\n%s", config.getPath(), stat));
            };
            statisticsWriter.write(osmStatistic);
        } catch (XMLStreamException e) {
            log.error("Can not parse XML", e);
        }
    }
}
