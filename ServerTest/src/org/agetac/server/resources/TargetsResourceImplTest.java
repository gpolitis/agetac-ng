package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.TargetDTO;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>TargetsResourceImplTest</code> contains tests for the class <code>{@link TargetsResourceImpl}</code>.
 *
 * @generatedBy CodePro at 3/28/12 2:40 AM
 * @author gp
 * @version $Revision: 1.0 $
 */
public class TargetsResourceImplTest {
	/**
	 * Run the Collection<Target> retrieve() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	@Test
	public void testRetrieve_1()
		throws Exception {
		TargetsResourceImpl fixture = new TargetsResourceImpl();

		Collection<TargetDTO> result = fixture.retrieve();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.agetac.server.resources.TargetsResourceImpl.retrieve(TargetsResourceImpl.java:18)
		assertNotNull(result);
	}

	/**
	 * Run the Collection<Target> retrieve() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	@Test
	public void testRetrieve_2()
		throws Exception {
		TargetsResourceImpl fixture = new TargetsResourceImpl();

		Collection<TargetDTO> result = fixture.retrieve();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.agetac.server.resources.TargetsResourceImpl.retrieve(TargetsResourceImpl.java:18)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(TargetsResourceImplTest.class);
	}
}