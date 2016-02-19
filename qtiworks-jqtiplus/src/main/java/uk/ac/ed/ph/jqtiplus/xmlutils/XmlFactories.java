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
package uk.ac.ed.ph.jqtiplus.xmlutils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * The JAXP Specification (V 1.4) says:<br>
 *
 * <quote>It is expected that the newSAXParser method of a SAXParserFactory implementation,
 * the newDocumentBuilder method of a DocumentBuilderFactory and the newTransformer method
 * of a TransformerFactory will be thread safe without side effects. This means that an
 * application programmer should expect to be able to create transformer instances in
 * multiple threads at once from a shared factory without side effects or problems.</quote>
 *
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 */
public class XmlFactories {

    private static final Logger logger = LoggerFactory.getLogger(XmlFactories.class);

	/* Create the DOM Document that will be built up here */
    private static final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    static {
    	dbFactory.setNamespaceAware(true);
    }

    /* Create and configure SAX parser */
    private static final SAXParserFactory spFactory = SAXParserFactory.newInstance();

    static {
	    try {
			spFactory.setNamespaceAware(true);
			spFactory.setValidating(false);
			spFactory.setXIncludeAware(true);
			spFactory.setFeature("http://xml.org/sax/features/validation", false);
			spFactory.setFeature("http://xml.org/sax/features/external-general-entities", true);
			spFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", true);
			spFactory.setFeature("http://xml.org/sax/features/lexical-handler/parameter-entities", false);
		} catch (final SAXNotRecognizedException e) {
			logger.error("", e);
		} catch (final SAXNotSupportedException e) {
			logger.error("", e);
		} catch (final ParserConfigurationException e) {
			logger.error("", e);
		}
    }


    /**
     * Create a namespace aware document builder.
     *
     * @return
     * @throws ParserConfigurationException
     */
    public static final DocumentBuilder newDocumentBuilder()
    throws ParserConfigurationException {
    	return dbFactory.newDocumentBuilder();
    }

    /**
     * Create a SAXParser without validation, but external entities
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static final SAXParser newSAXParser()
    		throws ParserConfigurationException, SAXException {
    	return spFactory.newSAXParser();
    }

}
