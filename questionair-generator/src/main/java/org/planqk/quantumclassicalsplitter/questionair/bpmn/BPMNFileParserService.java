package org.planqk.quantumclassicalsplitter.questionair.bpmn;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * The BPMN Schema can be found at https://www.omg.org/spec/BPMN/2.0/PDF
 */

/**
 * The BPMN File Parser services is able to parse an BPMN XML file.
 * The XML file should adhere to the official BPMN 2.0 spec by OMG.
 *
 * Currently, only simple tasks are supported.
 *
 * @author Julijan Katic
 * @version 1.0
 */
@Service
public class BPMNFileParserService {

    /**
     * Returns the tasks within a BPMN file.
     *
     * The parsing is very simple. It does neither regard the relationship between the tasks,
     * nor the type of the task.
     *
     * @param file The BPMN file
     * @return A list of tasks specified in the bpmn file.
     */
    public List<String> getBPMNTasks(final MultipartFile file) {
        final List<String> tasks = new LinkedList<>();
        try {
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser saxParser = factory.newSAXParser();
            final BPMNFileParser bpmnFileParser = new BPMNFileParser(tasks::addAll);
            saxParser.parse(file.getInputStream(), bpmnFileParser);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

}
