package bco.scheduler.controller;

import javax.validation.Valid;
import bco.scheduler.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Assignment;

import java.util.List;

import bco.scheduler.controller.AssignmentController;

/**
 * Assignment Controller
 * @author Alex Rose
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController{

    @Autowired
    private AssignmentRepository assignmentRepository;

        /**
     * returns all assignments
     * 
     * @return assignments list
     */
    @GetMapping("/assignments")
    public List<Assignment> getAllAssignments() {
        List<Assignment> aps = assignmentRepository.findAll();
        return aps;
    }

    /**
     * gets an assignment by id
     * 
     * @param assignmentId id
     * @return assignment with id
     * @throws ResourceNotFoundException
     */
    @GetMapping("/assignments/{id}")
    public Assignment getAssignmentById(@PathVariable(value = "id") Long assignmentId)
            throws ResourceNotFoundException {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(
                () -> new ResourceNotFoundException("Assignment not found for this id :: " + assignmentId));
        return assignment;
    }

    /**
     * creates an assignment
     * 
     * @param assignment to create
     * @return created assignment
     */
    @PostMapping("/assignments")
    public Assignment createAssignment(@Valid @RequestBody Assignment assignment) {
        final Assignment newAssignment = assignmentRepository.saveAndFlush(assignment);
        assignmentRepository.refresh(newAssignment);
        return newAssignment;
    }

    /**
     * updates an assignment
     * 
     * @param assignmentId      assignment to update
     * @param assignmentDetails updated assignment
     * @return updated assignment
     * @throws ResourceNotFoundException
     */
    @PutMapping("/assignments/{id}")
    public Assignment updateAssignment(@PathVariable(value = "id") Long assignmentId,
            @Valid @RequestBody Assignment assignmentDetails) throws ResourceNotFoundException {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(
                () -> new ResourceNotFoundException("Assignment not found for this id :: " + assignmentId));
        assignment.setTechnicians(assignmentDetails.getTechnicians());
        assignment.setDayNumber(assignmentDetails.getDayNumber());
        return assignmentRepository.save(assignment);
    }

    /**
     * deletes an assignment
     * 
     * @param assignmentId assignment to remove
     * @return removed assignment id
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/assignments/{id}")
    public long deleteAssignment(@PathVariable(value = "id") Long assignmentId) throws ResourceNotFoundException {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(
                () -> new ResourceNotFoundException("Assignment not found for this id :: " + assignmentId));

        assignmentRepository.delete(assignment);
        return assignment.getId();
    }

}