package tdd;

import java.util.Calendar;

import org.approvaltests.Approvals;
import org.junit.Test;

import tdd.util.TestResult;

import com.thoughtworks.xstream.XStream;

public class SimpleTextTest {

	@Test
	public void testString() throws Exception {
		String expected = "Does this work?";
		Approvals.approve(expected);
	}

	@Test
	public void testNewYear() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2012, Calendar.JANUARY, 1);
		String xml = new XStream().toXML(cal);
		Approvals.approve(xml);
	}

	@Test
	public void testResult() throws Exception {
		TestResult result = new TestResult();
		result.log("I expect this text");
		result.approve();
	}

}
