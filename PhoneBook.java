import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class PhoneBook implements Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Node head;
    class Node implements Serializable
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String firstname;
        String lastname;
        String contact;
        String name;
        Node next;
        Node(String name, String contact){
            this.name = name;
            this.contact = contact;
        }
        Node(String firstname, String lastname, String contact)
        {
            
            this.firstname = firstname; 
            this.lastname = lastname;                                   
            name = firstname + " " + lastname;                      
            this.contact = contact;             
            this.next = null;                
        }
        Node(String name){
            this.name = name;
        }
    }
                                                                 
    public void addContact(PhoneBook book, String name, String number) throws Exception{                                                  
        if(head == null){
            head = new Node(name,number);
            saveLink(book);
            return;
        }
        
        Node newNode = new Node(name,number);
        newNode.next = head;
        head = newNode;
        sort();
        saveLink(book);
    }
    public void sort()
    {

       for(Node x = head ; x != null ; x = x.next){ 
            for(Node y = head ; y !=  null ; y = y.next) {
                if(x.name.compareTo(y.name) < 0 ) { 
                    String temp = x.name; 
                    x.name = y.name;
                    y.name = temp;
                }
            }   
       }
     //  System.out.println("Sorted.");
    }
    public void Dis() 
    {
        Node curNode = head;
        while(curNode!=null)
        {
            System.out.print("Name: " + curNode.name
            + "\n" +"Contact Number: " + curNode.contact+"\n");
            curNode = curNode.next; 
        }   
    }
    public void search(String key) 
    {
        Node temp = head;
        while(temp != null){
            if(temp.name.equals(key)){
                System.out.println("Contact Information: ");     
                System.out.println("Contact Name: " + temp.name);
                System.out.println("Contact number: " + temp.contact);
            }
        }
        System.out.println("Information: Contact not Found.");
    }
    public void delContact(PhoneBook p,String name) throws Exception{
        Node temp = head;
        try{
        while(temp != null){
            if(name.equals(temp.name)){
                temp.next = null;
                saveLink(p);
                return;
            }
        }
        System.out.println("Contacts: Contact not found.");
        }catch(NullPointerException e){

        }   
    }
    public void saveLink(PhoneBook book) throws Exception{
        FileOutputStream output = new FileOutputStream("D:\\contacts.txt");
        ObjectOutputStream outputstream = new ObjectOutputStream(output);
        outputstream.writeObject(book);
        outputstream.close();
    }
    public static PhoneBook getLink() throws Exception{
        FileInputStream input = new FileInputStream("D:\\contacts.txt");
        ObjectInputStream get = new ObjectInputStream(input); 
        PhoneBook book = (PhoneBook)get.readObject();
        get.close();
        return book;
    }
    public static void main(String[] args) throws Exception
    {
    	
        Scanner input = new Scanner(System.in);
        int choice = 1;
        PhoneBook P = getLink();
        String name, contact;
        while(choice != 0){
        System.out.println("Hello This is Phone-Directory Made By Ali Raza");
        System.out.println("What do you want: ");
        System.out.println("0. Close.");
        System.out.println("1. Display Contacts");
        System.out.println("2. Search Contact");
        System.out.println("3. Add Contact");
        System.out.println("4.  Delete Contact");
        System.out.print("Enter your choice: ");
        choice = input.nextInt();
        switch(choice){
            case 0:{
                System.out.println("Contacts exited.");
                return;
             
            }
            case 1:{
                System.out.println("Display Contacts: \n");
                P.Dis();
            break;
            }
            case 2: {
            System.out.print("Enter name to Search: ");
            input.nextLine();
            name = input.nextLine();
            P.search(name);
            break;
            }
            case 3: {
            System.out.print("Enter name: ");
            input.nextLine();
            name = input.nextLine();
            System.out.print("Enter Phone Number: ");
            contact = input.nextLine();
            P.addContact(P, name, contact); 
            break;
            }
            case 4:{
            input.nextLine();
            System.out.print("Enter contact name to delete: ");        
            String contact2 = input.nextLine();
            P.delContact(P,contact2);         
            break;  
            }   
        }
    }
    }
    }
