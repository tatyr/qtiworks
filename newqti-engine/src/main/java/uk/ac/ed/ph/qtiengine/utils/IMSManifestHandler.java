package uk.ac.ed.ph.qtiengine.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Trivial SAX handler that hunts out <tt>resource</tt> and <tt>file</tt> elements
 * within IMS Content Package manifest files and records details about their
 * <tt>type</tt> and <tt>href</tt>. 
 * <p>
 * This only does the minimum required by MathAssessEngine so probably isn't
 * much use to anyone else.
 * <p>
 * Instances of this class are not thread safe but are serially reusable.
 * 
 * @author  David McKain
 * @version $Revision: 2518 $
 */
public class IMSManifestHandler extends DefaultHandler {
    
    private boolean isCommonCartridge;
    private final Set<String> resourceTypeSet;
    private final Map<String, List<String>> resourceHrefsByTypeMap;
    private final Set<String> resourceHrefSet;
    private final Set<String> fileHrefSet;
    private boolean doneDocumentElement;
    
    public static final String CP_NS_URI = "http://www.imsglobal.org/xsd/imscp_v1p1";
    public static final String COMMON_CARTRIDGE_CP_NS_URI = "http://www.imsglobal.org/xsd/imscc/imscp_v1p1";
    
    public IMSManifestHandler() {
        this.resourceTypeSet = new HashSet<String>();
        this.resourceHrefsByTypeMap = new HashMap<String, List<String>>();
        this.resourceHrefSet = new HashSet<String>();
        this.fileHrefSet = new HashSet<String>();
    }
    
    public boolean isCommonCartridge() {
        return isCommonCartridge;
    }
    
    public Set<String> getResourceTypes() {
        return resourceTypeSet;
    }
    
    public Set<String> getResourceHrefs() {
        return resourceHrefSet;
    }
    
    public Set<String> getFileHrefs() {
        return fileHrefSet;
    }

    public Map<String, List<String>> getResourceHrefsByTypeMap() {
        return resourceHrefsByTypeMap;
    }
    
    /** (Convenience computation method) */
    public List<String> getResourceHrefsForTypes(String... types) {
        List<String> result = new ArrayList<String>();
        for (String type : types) {
            List<String> hrefs = resourceHrefsByTypeMap.get(type);
            if (hrefs!=null) {
                result.addAll(hrefs);
            }
        }
        return result;
    }
    
    //--------------------------------------------------------------------
    
    public void reset() {
        resourceHrefsByTypeMap.clear();
        fileHrefSet.clear();
        doneDocumentElement = false;
        isCommonCartridge = false;
    }

    @Override
    public void startDocument() {
        reset();
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (!doneDocumentElement) {
            /* Decide whether we're doing a normal CP or a CC CP */
            if (COMMON_CARTRIDGE_CP_NS_URI.equals(uri)) {
                isCommonCartridge = true;
            }
            else if (!CP_NS_URI.equals(uri)) {
                throw new ContentPackageException("Document element was expected to be in namespace "
                        + CP_NS_URI + " or " + COMMON_CARTRIDGE_CP_NS_URI);
            }
            if (!"manifest".equals(localName)) {
                throw new ContentPackageException("Document element was expected to be 'manfest', not '" + localName + "'");
            }
            doneDocumentElement = true;
        }
        if ("resource".equals(localName)) {
            String type = attributes.getValue("type");
            resourceTypeSet.add(type);
            String href = attributes.getValue("href");
            if (href!=null) {
                List<String> resourceHrefsForTypeList = resourceHrefsByTypeMap.get(type);
                if (resourceHrefsForTypeList==null) {
                    resourceHrefsForTypeList = new ArrayList<String>();
                    resourceHrefsByTypeMap.put(type, resourceHrefsForTypeList);
                }
                resourceHrefsForTypeList.add(href);
                resourceHrefSet.add(href);
            }
        }
        else if ("file".equals(localName)) {
            String href = attributes.getValue("href");
            fileHrefSet.add(href);
        }
    }
}