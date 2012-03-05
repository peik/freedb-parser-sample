package tdd.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.approvaltests.Approvals;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDomDriver;

/**
 * A test helper class that collects the output of a test to be able to print it
 * in text form.<br>
 * Intended to collect data to be used with the {@link Approvals} tests library
 * (http://approvaltests.sourceforge.net/).
 * 
 * @author HiQ Finland Oy (9518174)
 * @version $Id: TestResult.java 8543 2011-12-16 16:23:58Z pas $
 */
public class TestResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private final List<String> output = new ArrayList<String>();
	private boolean logToConsole = false;

	/**
	 * Constructor
	 */
	public TestResult() {
	}

	/**
	 * Constructor with header text.
	 */
	public TestResult(String header) {
		log(header);
	}

	/**
	 * By default, the result is only logged to the output list, from which it
	 * can be retrieved at the end of the test using {@link #getOutputText()}.
	 * For debugging purposes, you might want to enable logging also to the
	 * console, which prints out the output to System.out as soon as it is
	 * created, not just at the end of the test.
	 */
	public void enableLogToConsole(boolean logAlsoToConsole) {
		this.logToConsole = logAlsoToConsole;
	}

	/**
	 * Log a new message to the result.
	 */
	public void log(String message) {
		if (logToConsole) {
			System.out.println(message);
		}
		output.add(message);
	}

	/**
	 * Log a new message to the result, formatting the arguments using
	 * {@link String#format(String, Object...)}.
	 */
	public void log(String formatMessage, Object... args) {
		log(String.format(formatMessage, args));
	}

	/**
	 * Log a collection of objects using their <code>#toString()</code> values.
	 */
	public void logCollection(Collection<?> objects) {
		for (Object object : objects) {
			log(String.valueOf(object));
		}
	}

	/**
	 * Append a part of a message to the end of the previous logged message. No
	 * spaces or padding is added, so this is up to the responsibility of the
	 * caller.
	 * <p>
	 * This method is useful when you want to build the message conditionally,
	 * and append more infor to the message only under certain condition, for
	 * instance after checking that some object to write to the output is not
	 * null.
	 */
	public void append(String messagePart) {
		if (logToConsole) {
			System.out.println("... " + messagePart);
		}
		int lastPos = output.size() - 1;
		String lastLoggedMessage = output.get(lastPos);
		output.set(lastPos, lastLoggedMessage + messagePart);
	}

	/**
	 * Append a part of a message to the end of the previous logged message,
	 * formatting the arguments using {@link String#format(String, Object...)}.
	 * 
	 * @see #append(String)
	 */
	public void append(String formatMessagePart, Object... args) {
		append(String.format(formatMessagePart, args));
	}

	public void logXML(Object object) {
		XStream xs = new XStream(new XppDomDriver());
		String xml = xs.toXML(object);
		log(xml);
	}

	public void logXMLCollection(Collection<?> objects) {
		XStream xs = new XStream(new XppDomDriver());
		for (Object object : objects) {
			String xml = xs.toXML(object);
			log(xml);
		}
	}

	/**
	 * Add the output of the given result to this one, joining the results
	 * together.
	 */
	public void addOutput(TestResult result) {
		this.output.addAll(result.output);
	}

	public List<String> getOutput() {
		return output;
	}

	public String getOutputText() {
		StringBuilder sb = new StringBuilder();
		for (String line : output) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Shortcut for <code>Approvals.approve(getOutputText());</code> which
	 * should be called at the end of an {@link Approvals} test.
	 */
	public void approve() throws Exception {
		Approvals.approve(getOutputText());
	}

}
