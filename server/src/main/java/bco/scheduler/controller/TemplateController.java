package bco.scheduler.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import bco.scheduler.model.Appointment;
import bco.scheduler.model.CommunicationType;
import bco.scheduler.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Template;
import bco.scheduler.repository.TemplateRepository;

/**
 * The REST API controller for the template object
 * @author Soumya Bagade
 * @author Connor J. Parke
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TemplateController {
    // the repository for the templates
    @Autowired
    private TemplateRepository templateRepository;

    /**
     * Used for the GET call for all - returns the complete list of templates from the DB
     */
    @GetMapping("/templates")
    public ResponseEntity<List<Template>> getAllTemplates() {
        return ResponseEntity.ok(templateRepository.findAll());
    }


    /**
     * Used for the GET call for one - returns the template given the specific ID from the DB
     * 
     * @param templateId the ID of the template
     * @throws ResourceNotFoundException if the template is not found in the DB
     */
    @GetMapping("/templates/{id}")
    public ResponseEntity<Template> getTemplateById(@PathVariable(value = "id") Long templateId)
            throws ResourceNotFoundException {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));
        return ResponseEntity.ok(template);
    }

    /**
     * Used for the POST call - creates a template and saves it to the DB
     * 
     * @param template the template to add to the database (will be parsed from the Template object type)
     */
    @PostMapping("/templates")
    public ResponseEntity<Template> createTemplate(@Valid @RequestBody Template template) {
        return ResponseEntity.ok(templateRepository.save(template));

    }

    /**
     * Used for the PUT call - updates a template in the DB and saves the update to the database
     * @param templateId the ID of the template to update
     * @param templateDetails the template object that is populated with the updated data to save to the DB
     * @throws ResourceNotFoundException if the template is not found in the DB
     */
    @PutMapping("/templates/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable(value = "id") Long templateId,
                                                   @Valid @RequestBody Template templateDetails) throws ResourceNotFoundException {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));

        template.setTitle(templateDetails.getTitle());
        template.setSubject(templateDetails.getSubject());
        template.setContent(templateDetails.getContent());
        
        return ResponseEntity.ok(templateRepository.save(template));
    }

    /**
     * Used for the DELETE call - deletes a template from the DB
     * 
     * @param templateId the ID of the template to delete
     * @throws ResourceNotFoundException if the template is not found in the DB
     */
    @DeleteMapping("/templates/{id}")
    public ResponseEntity<Template> deleteTemplate(@PathVariable(value = "id") Long templateId)
            throws ResourceNotFoundException {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));

        templateRepository.delete(template);
        return ResponseEntity.ok(template);
    }

    /**
     * Get template variables
     * @return array of template variables
     */
    @GetMapping("/templates/variables")
    public ResponseEntity<Map<String, String>> getTemplateVariables() {
        Map<String, String> customerTemplateVariables = Customer.getTemplateVariableDescriptions();
        Map<String, String> appointmentTemplateVariables = Appointment.getTemplateVariableDescriptions();
        Map<String, String> templateVariables = new HashMap<>();
        templateVariables.putAll(customerTemplateVariables);
        templateVariables.putAll(appointmentTemplateVariables);

        return ResponseEntity.ok(templateVariables);
    }
}