package bco.scheduler.controller;

import java.util.List;

import javax.validation.Valid;

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

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TemplateController {
    @Autowired
    private TemplateRepository templateRepository;

    @GetMapping("/templates")
    public ResponseEntity<List<Template>> getAllTemplates() {
        return ResponseEntity.ok(templateRepository.findAll());
    }

    @GetMapping("/templates/{id}")
    public ResponseEntity<Template> getTemplateById(@PathVariable(value = "id") Long templateId)
            throws ResourceNotFoundException {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));
        return ResponseEntity.ok(template);
    }

    @PostMapping("/templates")
    public ResponseEntity<Template> createTemplate(@Valid @RequestBody Template template) {
        return ResponseEntity.ok(templateRepository.save(template));

    }

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

    @DeleteMapping("/templates/{id}")
    public ResponseEntity<Template> deleteTemplate(@PathVariable(value = "id") Long templateId)
            throws ResourceNotFoundException {
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found for this id :: " + templateId));

        templateRepository.delete(template);
        return ResponseEntity.ok(template);
    }
}