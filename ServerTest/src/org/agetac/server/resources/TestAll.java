package org.agetac.server.resources;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The class <code>TestAll</code> builds a suite that can be used to run all
 * of the tests within its package as well as within any subpackages of its
 * package.
 *
 * @generatedBy CodePro at 3/28/12 2:40 AM
 * @author gp
 * @version $Revision: 1.0 $
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	VictimsResourceImplTest.class,
	VehicleDemandsResourceImplTest.class,
	MessagesResourceImplTest.class,
	InterventionsResourceImplTest.class,
	VictimResourceImplTest.class,
	VehicleDemandResourceImplTest.class,
	InterventionResourceImplTest.class,
	MessageResourceImplTest.class,
	TargetResourceImplTest.class,
	SourceResourceImplTest.class,
	SourcesResourceImplTest.class,
	TargetsResourceImplTest.class,
})
public class TestAll {

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 3/28/12 2:40 AM
	 */
	public static void main(String[] args) {
		JUnitCore.runClasses(new Class[] { TestAll.class });
	}
}
