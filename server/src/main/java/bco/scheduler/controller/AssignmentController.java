package bco.scheduler.controller;

import javax.validation.Valid;

import bco.scheduler.cron.NotificationSender;
import bco.scheduler.model.TimeToSend;
import bco.scheduler.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Appointment;

import java.util.List;
import java.util.Map;

import bco.scheduler.controller.AppointmentController;

/**
 * Assignment Controller
 * @author Alex Rose
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController{

}