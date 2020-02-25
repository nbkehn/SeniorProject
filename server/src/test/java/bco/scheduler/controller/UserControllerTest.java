package bco.scheduler.controller;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;
import bco.scheduler.model.User;
import java.util.*;
import java.util.ArrayList;
import bco.scheduler.model.CommunicationType;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;

/**
 * Tests the User controller class.
 * Uses Mockiato for mocking of relevant pieces. 
 * 
 * @author Will Duke
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

    // A mock of the user controller class.
    @Mock
    private UserController userController = new UserController();

    /**
     * Tests the getting of all the users through the
     * getAllUsers method.
     */
    @Test
    public void getAllUsersTest() throws Exception {
     
        // An user object to use.
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");

        // An user object list to use.
        List<User> allUsers = Arrays.asList(testUser);
 
        // Response Entity representation of the desired output
        ResponseEntity<List<User>> re = ResponseEntity.ok(allUsers);

        // Ensure the users are null.
        assertNull(userController.getAllUsers());

        // Telling Mockiato how to handle the methods.
        when(userController.getAllUsers()).thenReturn(re);

        // Ensure the user is in there. 
        assertEquals(userController.getAllUsers(), re);
    }

    /**
     * Tests the getting of the users by their id through
     * the getUserById method.
     */
    @Test
    public void getUserByIdTest() throws Exception {

        // A user object to use.
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");

        // A user object list to use.
        List<User> allUsers = Arrays.asList(testUser);
 
        // Response Entity representation of the desired output
        ResponseEntity<User> re = ResponseEntity.ok(testUser);

        // Ensure there are no users when they aren't in there.
        assertNull(userController.getUserById((long) testUser.getId()));

        // Telling Mockiato how to handle the methods.
        when(userController.getUserById((long) testUser.getId())).thenReturn(re);

        // Ensuring the user can be found. 
        assertEquals(userController.getUserById((long) testUser.getId()), re);
        
    }

    /**
     * Tests that the user is created successfully through 
     * the create user method.
     */
    @Test
    public void createUserTest() throws Exception {
        
        // A user object to use.
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");

        // Response Entity representation of the desired output
        ResponseEntity<User> re = ResponseEntity.ok(testUser);

        // Telling Mockiato how to handle the methods.
        when(userController.createUser(testUser)).thenReturn(re);

        // Ensuring the user is created 
        assertEquals(userController.createUser(testUser), re);

    }

    /**
     * Tests that the users are updated properly through the
     * update user method.
     */
    @Test
    public void updateUserTest() throws Exception {
     
        // A user object to use.
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");

        // A user object to update to. 
        User testUserUpdated = new User("Jane", "Dhoe", "janedoe@gmail.com", "336-344-0576", "janedoe", "securejanepassword");

        // Response Entity representation of the desired output
        ResponseEntity<User> re = ResponseEntity.ok(testUser);

        // Response Entity representation of the desired output
        ResponseEntity<User> updatedRe = ResponseEntity.ok(testUserUpdated);

        // Telling Mockiato how to handle the methods.
        when(userController.createUser(testUser)).thenReturn(re);

        // Ensuring the user is create
        assertEquals(userController.createUser(testUser), re);

        // Telling Mockiato how to handle the methods.
        when(userController.updateUser(testUser.getId(), testUserUpdated)).thenReturn(updatedRe);

        // Ensure the user is updated correctly. 
        assertEquals(userController.updateUser(testUser.getId(), testUserUpdated), updatedRe);

    }

    /**
     * Tests that users are deleted using the delete user method.
     */
    @Test
    public void deleteUserTest() throws Exception {

        // A user object to use.
        User testUser = new User("John", "Doe", "jdoe@gmail.com", "919-515-3000", "jdoe", "securepassword");

        // A user object to update to. 
        User testUserUpdated = new User("Jane", "Dhoe", "janedoe@gmail.com", "336-344-0576", "janedoe", "securejanepassword");

        // Response Entity representation of the desired output
        ResponseEntity<User> re = ResponseEntity.ok(testUser);
        
        // Response Entity representation of the desired output
        ResponseEntity<User> deletedRe = ResponseEntity.ok(testUser);

        // Telling Mockiato how to handle the methods.
        when(userController.createUser(testUser)).thenReturn(re);

        // Telling Mockiato how to handle the methods.
        when(userController.deleteUser(testUser.getId())).thenReturn(deletedRe);

        // Ensure that the user is created. 
        assertEquals(userController.createUser(testUser), re);

        // Ensure the user is deleted. 
        assertEquals(userController.deleteUser(testUser.getId()), deletedRe);

    }

}
