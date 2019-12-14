package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Template object, holds information related to templates
 * 
 * @author Soumya Bagade
 */
@Entity
public class Template {

    /** template id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** template title */
    @Column(name = "title", nullable = false)
    private String title;
    /** template subject */
    @Column(name = "subject", nullable = true)
    private String subject;
    /** template content */
    @Column(name = "content", columnDefinition="TEXT", nullable = false)
    private String content;
    
    /** default constructor */
    public Template() {}

    /**
     * main constructor 
     * 
     * @param title the title of the template
     * @param subject the subject of the template
     * @param content the content of the template that would be sent out
     */
    public Template(String title, String subject, String content) {
        this.title = title;
        this.subject = subject;
        this.content = content;
    }

    /**
     * gets id
     * @return id
     */
    public long getId() {
        return id;
    }
    
    /**
     * sets id
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * gets title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets title
     * @param title title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets subject
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * sets subject
     * @param subject subject 
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * gets content
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * sets content
     * @param content content 
     */
    public void setContent(String content) {
        this.content = content;
    }
}