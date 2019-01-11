/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties_manager;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author yiwang
 */
public class XMLUtilitiesTest {
    
    public XMLUtilitiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateXMLDoc method, of class XMLUtilities.
     */
    @Test
    public void testValidateXMLDoc() {
        System.out.println("validateXMLDoc");
        String xmlDocNameAndPath = "";
        String xmlSchemaNameAndPath = "";
        XMLUtilities instance = new XMLUtilities();
        boolean expResult = false;
        boolean result = instance.validateXMLDoc(xmlDocNameAndPath, xmlSchemaNameAndPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadXMLDocument method, of class XMLUtilities.
     */
    @Test
    public void testLoadXMLDocument() throws Exception {
        System.out.println("loadXMLDocument");
        String xmlFile = "";
        String xsdFile = "";
        XMLUtilities instance = new XMLUtilities();
        Document expResult = null;
        Document result = instance.loadXMLDocument(xmlFile, xsdFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodeWithName method, of class XMLUtilities.
     */
    @Test
    public void testGetNodeWithName() {
        System.out.println("getNodeWithName");
        Document doc = null;
        String tagName = "";
        XMLUtilities instance = new XMLUtilities();
        Node expResult = null;
        Node result = instance.getNodeWithName(doc, tagName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChildNodeWithName method, of class XMLUtilities.
     */
    @Test
    public void testGetChildNodeWithName() {
        System.out.println("getChildNodeWithName");
        Node parent = null;
        String tagName = "";
        XMLUtilities instance = new XMLUtilities();
        Node expResult = null;
        Node result = instance.getChildNodeWithName(parent, tagName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChildNodesWithName method, of class XMLUtilities.
     */
    @Test
    public void testGetChildNodesWithName() {
        System.out.println("getChildNodesWithName");
        Node parent = null;
        String tagName = "";
        XMLUtilities instance = new XMLUtilities();
        ArrayList<Node> expResult = null;
        ArrayList<Node> result = instance.getChildNodesWithName(parent, tagName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodeInSequence method, of class XMLUtilities.
     */
    @Test
    public void testGetNodeInSequence() {
        System.out.println("getNodeInSequence");
        Document doc = null;
        String tagName = "";
        int index = 0;
        XMLUtilities instance = new XMLUtilities();
        Node expResult = null;
        Node result = instance.getNodeInSequence(doc, tagName, index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumNodesOfElement method, of class XMLUtilities.
     */
    @Test
    public void testGetNumNodesOfElement() {
        System.out.println("getNumNodesOfElement");
        Document doc = null;
        String tagName = "";
        XMLUtilities instance = new XMLUtilities();
        int expResult = 0;
        int result = instance.getNumNodesOfElement(doc, tagName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
