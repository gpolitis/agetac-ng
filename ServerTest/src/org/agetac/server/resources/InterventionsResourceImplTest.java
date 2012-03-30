package org.agetac.server.resources;

import java.util.Collection;

import org.agetac.common.dto.InterventionDTO;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>InterventionsResourceImplTest</code> contains tests for the class <code>{@link InterventionsResourceImpl}</code>.
 *
 * @generatedBy CodePro at 3/28/12 2:40 AM
 * @author gp
 * @version $Revision: 1.0 $
 */
public class InterventionsResourceImplTest {
	/**
	 * Run the Collection<Intervention> retrieve() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	@Test
	public void testRetrieve_1()
		throws Exception {
		InterventionsResourceImpl fixture = new InterventionsResourceImpl();

		Collection<InterventionDTO> result = fixture.retrieve();

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
		InterventionResourceImpl fixture = new InterventionResourceImpl();
		InterventionDTO intervention = new InterventionDTO();

		fixture.add(intervention);
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
		new org.junit.runner.JUnitCore().run(InterventionsResourceImplTest.class);
	}
}