import java.io.*;
import java.util.*;


public class Receptionist {

    public static void CreateReceptionist() {

        try{
                String Filename = "Receptionist.txt";

                File file = new File(Filename);

                file.createNewFile();

        } catch(IOException e) {}

    }

    public static void Menu(Scanner scanner, String Name) {

        boolean Options = true;
                        
                        while(Options) {

                            //try{

                            System.out.println("Here are your options");
                            System.out.println();
                            System.out.println("1. Book in a paitent");
                            System.out.println("2. View the To-Do List");
                            System.out.println("3. View Stock");
                            System.out.println("4. Go back");

                            
                            System.out.println("Choice:");
                            int Choice = scanner.nextInt();
                            // scanner.nextLine();

                            switch(Choice) {

                                case 1:
                                        Bookings.bookings();
                                        break;


                                case 2:
                                        ToDoList.ToDo();
                                        break;

                                case 3:
                                        Stock stock = Stock.interactWithStock();
                                        stock.StockMainOptions(Name);
                                        break;

                                case 4:
                                        System.out.println("Leaving now");
                                        Options = false;
                                        break;

                            }



                           // } catch(Exception e) {
                                //e.getStackTrace();
                                //if(scanner.hasNextLine()) {
                                   // scanner.nextLine();
                                    //} 
                                //}
                        }

    }

    public static boolean CheckReceptionistExists(String filename) {

        File file = new File(filename);

        return file.exists();

    }

    public static boolean ReceptionistIsEmpty(String filename) {

        File file = new File(filename);

        return file.length() == 0;

    }
    
    //private static final String Filename = "Receptionist.txt";


    public static boolean CheckUserName(String userName) {
    
       try{ String FileName = "Receptionist.txt";
            File file = new File(FileName);

        try(Scanner reader = new Scanner(file)) {

            while(reader.hasNext()) {

                String ReceptionistUsername = reader.nextLine();

                String [] ReceptionistUsernameArr = ReceptionistUsername.split("; ");

                if(ReceptionistUsernameArr.length > 0 && ReceptionistUsernameArr[5].equals(userName)) {

                    return true;
                }

            }

        }


       } catch(FileNotFoundException e){}

       return false;

    }

    public static boolean CheckName(String Name) {

        try{
            String fileName = "Receptionist.txt";
            File file = new File(fileName);

            try(Scanner reader = new Scanner(file)){

                while(reader.hasNext()){

                    String recName = reader.nextLine();

                    String [] recNameArr = recName.split("; ");

                    if(recNameArr.length > 0 && recNameArr[0].equals(Name)){

                            return true;

                    }

                }

            }



        } catch(FileNotFoundException e) {

        }
        return false;

    }

    public static boolean CheckPassword(String Password, String Username) {

        try{    
                String fileName = "Receptionist.txt";
                File file = new File(fileName);

            try(Scanner reader = new Scanner(file)){

                while(reader.hasNext()) {

                    String Receptionists = reader.nextLine();

                    String [] receptionistsArr = Receptionists.split("; ");

                    if(receptionistsArr.length > 0 && receptionistsArr[5].equals(Username) && receptionistsArr[6].equals(Password)) {

                        return true;

                    }


                }

            }



        } catch(FileNotFoundException e){

        }
            return false;

    }

    public static void receptionist() {
        String Name = null;
        String Username = null;
        int Choice;
        boolean start = true;
        Scanner scanner = new Scanner(System.in);

        while(start) {

            try{

            System.out.println("Welcome to the receptionist page!");
            System.out.println();
            System.out.println("1. Login");
            System.out.println("2. Go back");

            System.out.println("Select: ");
            Choice = scanner.nextInt();
            //scanner.nextLine();

            switch(Choice) {

                case 1: 
                        boolean NameCheck = true;

                        while(NameCheck) {

                            try{

                                System.out.println();
                                System.out.println("Please enter your name!");
                                System.out.println();

                                System.out.println("Name: ");
                                String name = scanner.next();
                                scanner.nextLine();

                                boolean isNameValid = CheckName(name);

                                if(isNameValid) {

                                    Name = name;
                                    System.out.println();
                                    System.out.println("Thank you " + Name);
                                    break;

                                } else{
                                    System.out.println("Please enter a suitable name"); 
                                    scanner.nextLine();
                                }


                            } catch(InputMismatchException e){}


                        }

                        boolean UserCheck = true;
                        while(UserCheck) {
                            try{
                                System.out.println();
                                System.out.println("Please enter your login " + Name + ": ");
                                String username = scanner.nextLine();
                                boolean valid = CheckUserName(username);
                                if(valid) {

                                    System.out.println(); 
                                    System.out.println("Thank you " + Name);
                                    Username = username;
                                    UserCheck = false;
                                    
                                    } else{System.out.println("Please enter a valid username");}
                            } catch(InputMismatchException e){}
                        }

                        //Boolean authenticate = Authentication.authenticatePassword(Name, Filename, 4);

                        //if(authenticate) {}


                        //System.out.println();

                        boolean PasswordCheck = true;
                        for(int attempt = 3; attempt >= 0; attempt--) {

                            try{

                                    System.out.println();
                                    System.out.println("Please enter your password " + Name + ":");
                                    System.out.println();
                                    System.out.println("Password:");
                                    String Password = scanner.nextLine();

                                    boolean IsPasswordValid = CheckPassword(Password,Username);

                                    if(IsPasswordValid) {

                                            System.out.println();
                                            System.out.println("Welcome back " + Name);
                                            PasswordCheck = false;
                                            break;
                                   
                                     } else {

                                                System.out.println("Please enter a valid password. You have " + attempt + " attempts left");

                                     }



                            } catch(InputMismatchException e) {

                            }

                        }
                        if(PasswordCheck) {

                            System.out.println("Too many invalid attempts. Exiting the program");
                            System.exit(0);

                        }

                        System.out.println();

                        Menu(scanner, Username);
                        /*boolean Options = true;

                        while(Options) {
                            try{

                            

                            System.out.println("Here are your options");
                            System.out.println();
                            System.out.println("1. Book in a paitent");
                            System.out.println("2. View the To-Do List");
                            System.out.println("3. View Stock");
                            System.out.println("4. Go back");

                            
                            System.out.println("Choice:");
                            Choice = scanner.nextInt();
                            scanner.nextLine();

                            switch(Choice) {

                                case 1:
                                        Bookings.bookings();
                                        break;


                                case 2:
                                        ToDoList.ToDo();
                                        break;

                                case 3:
                                        Stock stock = Stock.interactWithStock();
                                        stock.StockMainOptions(Name);
                                        break;

                                case 4:
                                        System.out.println("Leaving now");
                                        Options = false;
                                        break;

                            }



                            } catch(NoSuchElementException e) {
                                e.getStackTrace();
                                if(scanner.hasNextLine()) {
                                    scanner.nextLine();
                                    } 
                                }

                        }*/
                        break;


                case 2:
                        System.out.println("Leaving now!");
                        start = false;
                        break;

                default:

            } 

                } catch(InputMismatchException e) {
            }

        }
        scanner.close();
    }

    public static void main(String [] args) {

        receptionist();

    }
    
}
