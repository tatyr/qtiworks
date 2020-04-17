package uk.ac.ed.ph.jqtiplus.internal.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * 
 * @author srosse, stephane.rosse@frentix.com, https://www.frentix.com
 *
 */
@RunWith(Parameterized.class)
public class StringUtilitiesTest {
	
    /**
     * Creates test data for this test.
     *
     * @return test data for this test
     */
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "test" },
                { "test " },
                { " test " },
                { "test \u00A0" },
                { "\u2007\u00A0test \u00A0" }
        });
    }
    
    private final String string;
    
    public StringUtilitiesTest(String string) {
    	this.string = string;
    }

    @Test
    public void testTrim() {
        assertEquals("test", StringUtilities.trim(string));
    }

}
