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
package uk.ac.ed.ph.jqtiplus.node.expression.operator;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 *
 * @author Stéphane Rossé
 */
@RunWith(Parameterized.class)
public class ToleranceModeTest {

    @Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { ToleranceMode.EXACT, 14.923d, 14.923d, 0d, 0d, false, false, true },
                { ToleranceMode.EXACT, 14.923d, 14d, 0d, 0d, false, false, false },
                { ToleranceMode.EXACT, 14.923d, -14.923d, 0d, 0d, false, false, false },
                { ToleranceMode.EXACT, 14.915d, 14.9150000d, 0d, 0d, false, false, true },
                { ToleranceMode.EXACT, 1.23e-2d, 0.0123d, 0d, 0d, false, false, true },

                { ToleranceMode.ABSOLUTE, 14.923d, 14.916d, 0.008d, 0.002d, true, true, true },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.914d, 0.008d, 0.002d, true, true, false },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.925d, 0.008d, 0.002d, true, true, true },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.926d, 0.008d, 0.002d, true, true, false },

                { ToleranceMode.ABSOLUTE, 14.923d, 14.916d, 0.008d, 0.002d, false, false, true },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.915d, 0.008d, 0.002d, true, true, true },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.914d, 0.008d, 0.002d, false, false, false },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.924d, 0.008d, 0.002d, false, false, true },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.925d, 0.008d, 0.002d, false, false, false },
                { ToleranceMode.ABSOLUTE, 14.923d, 14.926d, 0.008d, 0.002d, false, false, false },

                { ToleranceMode.ABSOLUTE, 14.9923923d, 14.9923915d, 0.0000008d, 0.0000002d, true, true, true },
                { ToleranceMode.ABSOLUTE, 14.9923923d, 14.9923914d, 0.0000008d, 0.0000002d, true, true, false },
                { ToleranceMode.ABSOLUTE, 14.9923923d, 14.9923915d, 0.0000008d, 0.0000002d, false, false, false },
        });
    }

	private final ToleranceMode mode;
	private final double firstNumber;
	private final double secondNumber;
	private final double tolerance1;
	private final double tolerance2;
	private final boolean includeLowerBound;
	private final boolean includeUpperBound;
	private final boolean match;

	public ToleranceModeTest(final ToleranceMode mode, final double firstNumber, final double secondNumber,
			final double tolerance1, final double tolerance2, final boolean includeLowerBound, final boolean includeUpperBound,
			final boolean match) {
		this.mode = mode;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.tolerance1 = tolerance1;
		this.tolerance2 = tolerance2;
		this.includeLowerBound = includeLowerBound;
		this.includeUpperBound = includeUpperBound;
		this.match = match;
	}

    @Test
    public void test() throws Exception {
    		final boolean equal = mode.isEqual(firstNumber, secondNumber, tolerance1, tolerance2, includeLowerBound, includeUpperBound);
    		Assert.assertEquals(match, equal);
    }
}
