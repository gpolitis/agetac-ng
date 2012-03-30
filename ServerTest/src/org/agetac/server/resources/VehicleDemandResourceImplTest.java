package org.agetac.server.resources;

import org.agetac.common.dto.VehicleDemandDTO;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>VehicleDemandResourceImplTest</code> contains tests for the class <code>{@link VehicleDemandResourceImpl}</code>.
 *
 * @generatedBy CodePro at 3/28/12 2:40 AM
 * @author gp
 * @version $Revision: 1.0 $
 */
public class VehicleDemandResourceImplTest {
	/**
	 * Run the void add(VehicleDemand) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	@Test
	public void testAdd_1()
		throws Exception {
		VehicleDemandResourceImpl fixture = new VehicleDemandResourceImpl();
		VehicleDemandDTO vehicleDemand = new VehicleDemandDTO();

		fixture.add(vehicleDemand);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.agetac.server.resources.VehicleDemandResourceImpl.add(VehicleDemandResourceImpl.java:17)
	}

	/**
	 * Run the void add(VehicleDemand) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	@Test
	public void testAdd_2()
		throws Exception {
		VehicleDemandResourceImpl fixture = new VehicleDemandResourceImpl();
		VehicleDemandDTO vehicleDemand = new VehicleDemandDTO();

		fixture.add(vehicleDemand);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.agetac.server.resources.VehicleDemandResourceImpl.add(VehicleDemandResourceImpl.java:17)
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
		new org.junit.runner.JUnitCore().run(VehicleDemandResourceImplTest.class);
	}
}