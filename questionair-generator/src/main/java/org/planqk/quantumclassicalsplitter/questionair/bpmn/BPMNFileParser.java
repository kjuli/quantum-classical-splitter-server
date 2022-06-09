package org.planqk.quantumclassicalsplitter.questionair.bpmn;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.util.function.Consumer;

/**
 * Class that handles the actual parsing of a BPMN
 * spec. The tasks should have a name.
 */
public class BPMNFileParser extends DefaultHandler {
    /** The XML element name for bpmn tasks */
    private static final String BPMN_TASK = "bpmn:task";
    private static final String BPMN_TASK_NAME = "name";

    private final List<String> tasks;
    private final Consumer<List<String>> taskConsumer;

    /**
     * Creates a new BPMN file parser instance.
     *
     * @param taskConsumer The consumer of the tasks that should be called when parsing has finished
     *                     (the end of document in the BPMN XML file has been reached).
     */
    public BPMNFileParser(final Consumer<List<String>> taskConsumer) {
        this.taskConsumer = Objects.requireNonNull(taskConsumer);
        this.tasks = new LinkedList<>();
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        tasks.clear();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        this.taskConsumer.accept(Collections.unmodifiableList(this.tasks));
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (BPMN_TASK.equalsIgnoreCase(qName)) {
            // Only regard those BPMN elements of type bpmn:task
            Optional.ofNullable(attributes.getValue(BPMN_TASK_NAME))
                    .ifPresent(this.tasks::add);
        }
        super.startElement(uri, localName, qName, attributes);
    }
}
