package ru.nsu.dyuganov.ris1.Node;

public class NodeReaderException extends Exception {
    public NodeReaderException(Throwable cause) {
        super(cause);
    }

    public NodeReaderException(String message) {
        super(message);
    }

    public NodeReaderException() {
        super();
    }
}
