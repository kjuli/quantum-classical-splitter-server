package org.planqk.quantumclassicalsplitter.questionair.bpmn;

import org.planqk.quantumclassicalsplitter.questionair.dto.Task;
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
    private static final String BPMN_TASK = "bpmn2:task"; // TODO: Be flexible in namespace
    private static final String BPMN_TASK_NAME = "name";
    private static final String BPMN_TASK_ID = "id";

    private final List<Task> tasks;
    private final Consumer<List<Task>> taskConsumer;

    /**
     * Creates a new BPMN file parser instance.
     *
     * @param taskConsumer The consumer of the tasks that should be called when parsing has finished
     *                     (the end of document in the BPMN XML file has been reached).
     */
    public BPMNFileParser(final Consumer<List<Task>> taskConsumer) {
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
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        if (BPMN_TASK.equalsIgnoreCase(qName)) {
            // Only regard those BPMN elements of type bpmn:task
            final String id = attributes.getValue(BPMN_TASK_ID);
            final String name = attributes.getValue(BPMN_TASK_NAME);
            this.tasks.add(new Task(id, name));
        }
        super.startElement(uri, localName, qName, attributes);
    }
}
