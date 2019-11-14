package bco.scheduler.controller;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Reminder;
import bco.scheduler.model.Template;
import bco.scheduler.model.TimeToSend;
import bco.scheduler.repository.AppointmentQueueRepository;
import bco.scheduler.repository.ReminderRepository;
import bco.scheduler.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ReminderController {
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private AppointmentQueueRepository appointmentQueueRepository;

    /**
     * Used for the GET call for all - returns the complete list of reminders from the DB
     */
    @GetMapping("/reminders")
    public ResponseEntity<List<Reminder>> getAllReminders() {
        return ResponseEntity.ok(reminderRepository.findAll());
    }

    /**
     * Used for the GET call for one - returns the reminder given the specific ID from the DB
     *
     * @param reminderId the ID of the reminder
     * @throws ResourceNotFoundException if the reminder is not found in the DB
     */
    @GetMapping("/reminders/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable(value = "id") Long reminderId)
            throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found for this id :: " + reminderId));
        return ResponseEntity.ok(reminder);
    }

    /**
     * Used for the POST call - creates a reminder and saves it to the DB
     *
     * @param reminder the reminder to add to the database (will be parsed from the Reminder object type)
     */
    @PostMapping("/reminders")
    public ResponseEntity<Reminder> createReminder(@Valid @RequestBody Reminder reminder) {
        reminder = reminderRepository.save(reminder);
        appointmentQueueRepository.addNewReminder(reminder.getId());
        return ResponseEntity.ok(reminder);
    }

    /**
     * Used for the PUT call - updates a reminder in the DB and saves the update to the database
     * @param reminderId the ID of the reminder to update
     * @param reminderDetails the reminder object that is populated with the updated data to save to the DB
     * @throws ResourceNotFoundException if the reminder is not found in the DB
     */
    @PutMapping("/reminders/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable(value = "id") Long reminderId,
                                                   @Valid @RequestBody Reminder reminderDetails) throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found for this id :: " + reminderId));

        reminder.setEmailTemplate(reminderDetails.getEmailTemplate());
        reminder.setTextTemplate(reminderDetails.getTextTemplate());
        reminder.setTimeToSend(reminderDetails.getTimeToSend());
        
        return ResponseEntity.ok(reminderRepository.save(reminder));
    }

    /**
     * Used for the DELETE call - deletes a reminder from the DB
     *
     * @param reminderId the ID of the reminder to delete
     * @throws ResourceNotFoundException if the reminder is not found in the DB
     */
    @DeleteMapping("/reminders/{id}")
    public ResponseEntity<Reminder> deleteReminder(@PathVariable(value = "id") Long reminderId)
            throws ResourceNotFoundException {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found for this id :: " + reminderId));

        reminderRepository.delete(reminder);
        return ResponseEntity.ok(reminder);
    }

    /**
     * Get possible times to send
     * @return array of times to send
     */
    @GetMapping("/reminders/timeToSend")
    public ResponseEntity<Map<Integer, String>> getTimesToSend() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (TimeToSend timeToSend : TimeToSend.values()) {
            map.put(timeToSend.getOffset(), timeToSend.getName());
        }
        return ResponseEntity.ok(map);
    }

    /**
     * Get templates
     * @return array of templates
     */
    @GetMapping("/reminders/template")
    public ResponseEntity<Map<Long, String>> getTemplates() {
        Map<Long, String> map = new HashMap<Long, String>();
        for (Template template : templateRepository.findAll()) {
            map.put(template.getId(), template.getTitle());
        }
        return ResponseEntity.ok(map);
    }
}