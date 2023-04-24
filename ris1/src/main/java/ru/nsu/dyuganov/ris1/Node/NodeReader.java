package ru.nsu.dyuganov.ris1.Node;

import javax.xml.stream.XMLStreamException;

public interface NodeReader {
    Node read() throws NodeReaderException;
    boolean hasNext() throws XMLStreamException;
}
