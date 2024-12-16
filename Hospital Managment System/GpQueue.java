import java.io.*;
import java.util.*;

class myNode{
    public String firstname;
    public String username;
    public myNode next;
    public myNode previous;
    



public myNode(String firstname, String username){

    this.username = username;
    this.firstname = firstname;
    this.next = null;
    this.previous = null;

}

public myNode(String firstname, String username , myNode next, myNode previous) { 
    this.firstname = firstname;
    this.username = username;
    this.next = next;
    this.previous = previous;

    }

}   
public class GpQueue {
    private static final String FILENAME = "GpQueue.java";
    private HashMap<String, myNode> usernameMap;
    private myNode front, back;

    public GpQueue(){
        this.usernameMap = new HashMap<>();
        this.front = null;
        this.back = null;
    }

    public void enqueue(String firstname, String username) {

        myNode node = new myNode(firstname, username);

        if(back == null) {

            front = node;

        } else{

            node.next = back;
            back.previous = node;

        }
        back = node;

        usernameMap.put(username, node);
    }

    public void writeToFile() {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {

            myNode current = front;

            while(current != null) {

                writer.write(current.firstname + ";" + current.username);
                writer.newLine();
                current = current.previous;
            }
        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    public boolean isEmpty() throws FileNotFoundException {

        File file = new File(FILENAME);
        return file.length() == 0;
    }

    public boolean exists() {

        File file = new File(FILENAME);
        return file.exists();
    }

    public int size() {

        int count = 0;
        myNode current = front;

        while(current != null) {

            count++;
            current = current.previous;

        }
        return count;
    }

    public void remove(String username) {

        if(usernameMap.containsKey(username)) {

            myNode nodeToRemove = usernameMap.get(username);

            if(nodeToRemove.previous != null) {

                nodeToRemove.previous.next = nodeToRemove.next;

            } else {

                front = nodeToRemove.next;
            }

            if(nodeToRemove.next != null) {
                
                nodeToRemove.next.previous = nodeToRemove.previous;

            } else {

                back = nodeToRemove.previous;
            }

            usernameMap.remove(username);

            writeToFile();
        } 

    }

    public void PrintLibrayQueue() {

        myNode current = front;

        System.out.println("The current GP queue consits of: ");

        while(current != null) {

            for(int i = 0; i < size(); i++) {

                System.out.println(i + ") " + current.firstname + ", Username: "  + current.username);
                current = current.previous;
            }
        }
    }

}



    

