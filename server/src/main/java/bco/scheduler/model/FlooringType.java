package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The flooring type class used to assign different types of flooring used for 
 * appointments. 
 * @author Noah Trimble, Will Duke
 */
@Entity
@Table(name = "flooringtype")
public class FlooringType {

    /**
     * An id generated for the flooring type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The actual string representation of the flooring type.
     * This is the name(category) of the type of flooring, not the style or color
     */
    @Column(name = "name")
    public String name;

    /**
     * Style name of the flooring type
     *
     */
     @Column(name = "style", columnDefinition = "varchar(255) default ''")
     public String style;

     /**
      * Color of the flooring type
      *
      */
     @Column(name = "color", columnDefinition = "varchar(255) default ''", nullable = true)
     public String color;

     /**
      * Company that makes the flooring type
      */
     @Column(name = "company", columnDefinition = "varchar(255) default ''")
     public String company;

     /**
      * Whether or not the sample has been checked out
      */
     @Column(name = "sampleChecked", columnDefinition = "boolean default 'false'")
     public Boolean sampleChecked;

     /**
      * To whom the sample as been checked out
      */
     @Column(name = "customer_identification")
     public long checkedTo;

     /**
      * Hash String for QR code
      */
     @Column(name = "hash_code", nullable = false)
     public String hash_code;

   /**
    * The blank, unused constructor for flooring type.
    */
   public FlooringType() {

   }

   /**
    * The actual flooring type constructor with a passed floor type to set to. 
    * @param name the type to set the floor object to.
    */
   public FlooringType(String name, String style, String color, String company) {
       String temp = name.toLowerCase();
       if(temp.equals("carpet")){
           this.name = name;
           this.style = style;
           this.color = "";
       } else {
           this.name = name;
           this.color = color;
           this.style = style;
       }

       if(company == null) {
          this.company = "";
       } else {
          this.company = company;
       }
       this.hash_code = Integer.toString(hashCode(name, style, color, company));
       this.sampleChecked = false;
       this.checkedTo = -1;

   }

     /**
    * The actual flooring type constructor with a passed floor type to set to. 
    * @param name the type to set the floor object to.
    */
    public FlooringType(String name, String style, String color, String company, boolean checked, Customer checkedTo) {
        String temp = name.toLowerCase();
        if(temp.equals("carpet")){
            this.name = name;
            this.style = style;
            this.color = "";
        } else {
            this.name = name;
            this.color = color;
            this.style = style;
        }
        if(company == null) {
            this.company = "";
        } else {
            this.company = company;
        }
        this.hash_code = Integer.toString(hashCode(name, style, color, company));
        this.sampleChecked = checked;
        this.checkedTo = -1;
    }

   /**
    * Gets the id of the flooring type as a long 
    * @return id a long representation of the id 
    */
   public long getId() {
       return id;
   }

   /**
    * Sets the id of the flooring type to the passed long param. 
    * @param id a long representation of the id to set the flooring type object to.
    */
   public void setId(long id) {
       this.id = id;
   }
 
   /**
    * Gets the flooring type of the object represented as a string
    * @return name string representation of the flooring type
    */
   public String getName() {
       return name;
   }

   /**
    * Sets the flooring type name parameter to the passed string name
    * @param name string representation of the flooring type to set to
    */
   public void setName(String name) {
       this.name = name;
   }

   /**
    * Retrieves the style of the current flooring type
    */
   public String getStyle() {
       return style;
   }

   /**
    * Sets the flooring type's style to the given value "style"
    * @param style string representation of the flooring type's style
    */
   public void setStyle(String style) {
       this.style = style;
   } 

/**
    * Retrieves the color of the current flooring type
    */
   public String getColor() {
       return color;
   }

/**
    * Sets the flooring type's color to the given value "color"
    * @param color string representation of the flooring type's color
    */
   public void setColor(String color) {
       this.color = color;
   }

   /**
    * @return the company
    */
   public String getCompany() {
       return company;
   }

   /**
    * @param company the company to set
    */
   public void setCompany(String company) {
       this.company = company;
   }

   /**
    * Checks a sample in. Assigns the sampleChecked value to false
    */
    public void checkIn(){
        this.sampleChecked = false;
        this.checkedTo = -1;
    }

    /**
     * Checks out a flooring sample and assigns it to the customer
     * who is checking it out
     *@param checker the person who is checking out this flooring sample
     */
    public void checkOut(Customer checker){
        this.sampleChecked = true;
        this.checkedTo = checker.getId();
    }

    
    public int hashCode(String name, String style, String color, String company){
        int hash = 7;
        hash = 31 * hash + name.hashCode();
        hash = 31 * hash + style.hashCode();
        hash = 31 * hash + color.hashCode();
        hash = 31 * hash + company.hashCode();
        return hash;    
    }
}
