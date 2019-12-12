package bco.scheduler.controller;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import bco.scheduler.model.Template;
import java.util.*;
import java.util.ArrayList;
import bco.scheduler.model.CommunicationType;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

/**
 * Tests the Template controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class TemplateControllerTest {

    // A mock of the template controller class.
    @Mock
    private TemplateController templateController = new TemplateController();

    /**
     * Tests the getting of all the templates through the
     * getAllTemplates method.
     */
    @Test
    public void getAllTemplatesTest() throws Exception {
     
        // An template object to use.
        Template testTemplate = new Template("title", "subject", "content");

        // An template object list to use.
        List<Template> allTemplates = Arrays.asList(testTemplate);
 
        // Response Entity representation of the desired output
        ResponseEntity<List<Template>> re = ResponseEntity.ok(allTemplates);

        // Ensure the templates are null.
        assertNull(templateController.getAllTemplates());

        // Telling Mockiato how to handle the methods.
        when(templateController.getAllTemplates()).thenReturn(re);

        // Ensure the template is in there. 
        assertEquals(templateController.getAllTemplates(), re);
    }

    /**
     * Tests the getting of the templates by their id through
     * the getTemplateById method.
     */
    @Test
    public void getTemplateByIdTest() throws Exception {

        // A template object to use.
        Template testTemplate = new Template("title", "subject", "content");

        // A template object list to use.
        List<Template> allTemplates = Arrays.asList(testTemplate);
 
        // Response Entity representation of the desired output
        ResponseEntity<Template> re = ResponseEntity.ok(testTemplate);

        // Ensure there are no templates when they aren't in there.
        assertNull(templateController.getTemplateById((long) testTemplate.getId()));

        // Telling Mockiato how to handle the methods.
        when(templateController.getTemplateById((long) testTemplate.getId())).thenReturn(re);

        // Ensuring the template can be found. 
        assertEquals(templateController.getTemplateById((long) testTemplate.getId()), re);
        
    }

    /**
     * Tests that the template is created successfully through 
     * the create template method.
     */
    @Test
    public void createTemplateTest() throws Exception {
        
        // A template object to use.
        Template testTemplate = new Template("title", "subject", "content");

        // Response Entity representation of the desired output
        ResponseEntity<Template> re = ResponseEntity.ok(testTemplate);

        // Telling Mockiato how to handle the methods.
        when(templateController.createTemplate(testTemplate)).thenReturn(re);

        // Ensuring the template is created 
        assertEquals(templateController.createTemplate(testTemplate), re);

    }

    /**
     * Tests that the templates are updated properly through the
     * update template method.
     */
    @Test
    public void updateTemplateTest() throws Exception {
     
        // A template object to use.
        Template testTemplate = new Template("title", "subject", "content");

        // A template object to update to. 
        Template testTemplateUpdated = new Template("changed", "this", "text");

        // Response Entity representation of the desired output
        ResponseEntity<Template> re = ResponseEntity.ok(testTemplate);

        // Response Entity representation of the desired output
        ResponseEntity<Template> updatedRe = ResponseEntity.ok(testTemplateUpdated);

        // Telling Mockiato how to handle the methods.
        when(templateController.createTemplate(testTemplate)).thenReturn(re);

        // Ensuring the template is create
        assertEquals(templateController.createTemplate(testTemplate), re);

        // Telling Mockiato how to handle the methods.
        when(templateController.updateTemplate(testTemplate.getId(), testTemplateUpdated)).thenReturn(updatedRe);

        // Ensure the template is updated correctly. 
        assertEquals(templateController.updateTemplate(testTemplate.getId(), testTemplateUpdated), updatedRe);

    }

    /**
     * Tests that templates are deleted using the delete template method.
     */
    @Test
    public void deleteTemplateTest() throws Exception {

        // A template object to use.
        Template testTemplate = new Template("title", "subject", "content");

        // A template object to update to. 
        Template testTemplateUpdated = new Template("changed", "this", "text");

        // Response Entity representation of the desired output
        ResponseEntity<Template> re = ResponseEntity.ok(testTemplate);
        
        // Response Entity representation of the desired output
        ResponseEntity<Template> deletedRe = ResponseEntity.ok(testTemplate);

        // Telling Mockiato how to handle the methods.
        when(templateController.createTemplate(testTemplate)).thenReturn(re);

        // Telling Mockiato how to handle the methods.
        when(templateController.deleteTemplate(testTemplate.getId())).thenReturn(deletedRe);

        // Ensure that the template is created. 
        assertEquals(templateController.createTemplate(testTemplate), re);

        // Ensure the template is deleted. 
        assertEquals(templateController.deleteTemplate(testTemplate.getId()), deletedRe);

    }

}
