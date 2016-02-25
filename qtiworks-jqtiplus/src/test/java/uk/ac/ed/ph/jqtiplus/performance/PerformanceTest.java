
package uk.ac.ed.ph.jqtiplus.performance;

import org.junit.Test;

/**
 * Used for to test how a test with 7500 assessmentItems is loaded.
 *
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 */
public class PerformanceTest {

	@Test
	public void openBigTest_memory() {
		/*
		final File resourceFile = new File("/HotCoffee/QTI/20160217-122351/test9c173d5c-beca-4c70-8e16-2837bb5984c0.xml");
		final QtiXmlReader qtiXmlReader = UnitTestHelper.createUnitTestQtiXmlReader();
		final ResourceLocator fileResourceLocator = new FileResourceLocator();

        final AssessmentObjectXmlLoader assessmentObjectXmlLoader = new AssessmentObjectXmlLoader(qtiXmlReader, fileResourceLocator);
        final ResolvedAssessmentTest resolvedTest = assessmentObjectXmlLoader.loadAndResolveAssessmentTest(resourceFile.toURI());
        Assert.assertNotNull(resolvedTest);
        final AssessmentTest test = resolvedTest.getTestLookup().extractIfSuccessful();
        Assert.assertNotNull(test);

        final AssessmentItemRef itemRef = resolvedTest.getAssessmentItemRefs().get(0);
        final ResolvedAssessmentItem resolvedItem = resolvedTest.getResolvedAssessmentItem(itemRef);
        Assert.assertNotNull(resolvedItem);
        final AssessmentItem item = resolvedItem.getRootNodeLookup().extractIfSuccessful();
        Assert.assertNotNull(item);
        Assert.assertEquals(1, item.getItemBody().findInteractions().size());
        */
	}

	/*
	@Test
	public void openBigTest() {

		long totalTime = 0;
		for(int i=0; i<10; i++) {
			totalTime += openBigTest_sub();
		}

        System.out.println("Takes (ms): " + (totalTime / 10000000));
	}

	@Test
	public void openBigTest_twice() {
		final long time = openBigTest_sub();
        System.out.println("Takes (ms): " + (time / 1000000));
		//openBigTest_sub();

		try {
			System.gc();
			System.gc();

			Thread.sleep(120000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}


	public long openBigTest_sub() {
		final File resourceFile = new File("/HotCoffee/QTI/20160219-180424/testfa908329-ab44-4821-a20d-ca634b6afb06.xml");
		final QtiXmlReader qtiXmlReader = UnitTestHelper.createUnitTestQtiXmlReader();
		final ResourceLocator fileResourceLocator = new FileResourceLocator();

		final long start = System.nanoTime();
        final AssessmentObjectXmlLoader assessmentObjectXmlLoader = new AssessmentObjectXmlLoader(qtiXmlReader, fileResourceLocator);
        final ResolvedAssessmentTest resolvedTest = assessmentObjectXmlLoader.loadAndResolveAssessmentTest(resourceFile.toURI());
        Assert.assertNotNull(resolvedTest);
        final AssessmentTest test = resolvedTest.getTestLookup().extractIfSuccessful();
        Assert.assertNotNull(test);
        final long time = (System.nanoTime() - start);

        final AssessmentItemRef itemRef = resolvedTest.getAssessmentItemRefs().get(0);
        final ResolvedAssessmentItem resolvedItem = resolvedTest.getResolvedAssessmentItem(itemRef);
        Assert.assertNotNull(resolvedItem);
        final AssessmentItem item = resolvedItem.getRootNodeLookup().extractIfSuccessful();
        Assert.assertNotNull(item);
        Assert.assertEquals(1, item.getItemBody().findInteractions().size());
        return time;
	}
	*/

}
