/* Copyright (c) 2012-2013, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTITools and MathAssessEngine.
 * QTITools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.jqtiplus.running;

import uk.ac.ed.ph.jqtiplus.node.test.AssessmentSection;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests navigation within a test containing severals {@link AssessmentSection}s
 * having non-linear navigation mode.
 *
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 */
public final class TestNonlinearNavigationMultipleSectionsTest extends SinglePartTestBase {

	private static final List<String> TEST_NODES = Arrays.asList(new String[] {
            "p",
                "s11",
                    "s111",
                        "i1111",
                        "i1112",
                    "i112",
                "s12",
                    "i121"
        });

    @Override
    protected String getTestFilePath() {
        return "running/test-nonlinear-multiple-sections.xml";
    }

	@Override
	protected List<String> testNodes() {
		return TEST_NODES;
	}

	@Test
    public void testNextItem() {
    	testSessionController.enterTest(testEntryTimestamp);
        testSessionController.enterNextAvailableTestPart(testPartEntryTimestamp);
        Assert.assertNull(testSessionState.getCurrentItemKey());
        //next
        testSessionController.selectFollowingItemNonLinear(testEntryTimestamp);
        Assert.assertEquals(getTestNodeKey("i1111"), testSessionState.getCurrentItemKey());
        testSessionController.selectFollowingItemNonLinear(testEntryTimestamp);
        Assert.assertEquals(getTestNodeKey("i1112"), testSessionState.getCurrentItemKey());
        testSessionController.selectFollowingItemNonLinear(testEntryTimestamp);
        Assert.assertEquals(getTestNodeKey("i112"), testSessionState.getCurrentItemKey());
        testSessionController.selectFollowingItemNonLinear(testEntryTimestamp);
        Assert.assertEquals(getTestNodeKey("i121"), testSessionState.getCurrentItemKey());


    }
}
