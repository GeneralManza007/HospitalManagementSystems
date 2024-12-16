import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Authentication {

    public static boolean CheckUserName(String userName, String FileName, int Position) {
    
        try{
             File file = new File(FileName);
 
         try(Scanner reader = new Scanner(file)) {
 
             while(reader.hasNext()) {
 
                 String ReceptionistUsername = reader.nextLine();
 
                 String [] ReceptionistUsernameArr = ReceptionistUsername.split(" ");
 
                 if(ReceptionistUsernameArr.length > 0 && ReceptionistUsernameArr[Position].equalsIgnoreCase(userName)) {
 
                     return true;
                 }
 
             }
 
         }
 
 
        } catch(FileNotFoundException e){}
 
        return false;
 
     }

     public static boolean CheckName(String Name, String fileName, int Position ) {

        try{
            File file = new File(fileName);

            try(Scanner reader = new Scanner(file)){

                while(reader.hasNext()){

                    String recName = reader.nextLine();

                    String [] recNameArr = recName.split(" ");

                    if(recNameArr.length > 0 && recNameArr[Position].equalsIgnoreCase(Name)){

                            return true;

                    }

                }

            }



        } catch(FileNotFoundException e) {

        }
        return false;

    }

     public static boolean CheckPassword(String password, String username, String FileName, int Position) {

        try{
            File file = new File(FileName);

            try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()) {

                        String Password = reader.nextLine();

                        String [] Password_Arr = Password.split(" ");

                        if(Password_Arr.length > 0 && Password_Arr[Position].equals(password))
                            {return true;
                            
                        } else{continue;
                        
                        }

                    }   

                }
            } catch(FileNotFoundException e) {}

            return false;
        }


    public static boolean authenticatePassword(String name, String username, String filename, int position) {

        Scanner scanner = new Scanner(System.in);

        boolean passwordCheck = true;

        for (int attempt = 3; attempt >= 0; attempt--) {
            try {
                System.out.println();
                System.out.println("Please enter your password " + name + ":");
                System.out.println();
                System.out.println("Password:");
                String password = scanner.nextLine();

                boolean isPasswordValid = CheckPassword(password, username, filename, position);

                if (isPasswordValid) {
                    System.out.println();
                    System.out.println("Welcome back " + name);
                    passwordCheck = false;
                    break;
                } else {
                    System.out.println("Please enter a valid password. You have " + attempt + " attempts left");
                }

            } catch (InputMismatchException e) {

            }
        }

        if (passwordCheck) {
            System.out.println("Too many invalid attempts. Exiting the program");
            System.exit(0);
        }

        scanner.close();
        return true;
    }

    public static void authenticateName(String filename, String Name , int position) {
        Scanner scanner = new Scanner(System.in);
        boolean NameCheck = true;

            while(NameCheck) {

                try{

                    System.out.println();
                    System.out.println("Please enter your name!");
                    System.out.println();

                    System.out.println("Name: ");
                    String name = scanner.next();
                    scanner.nextLine();

                    boolean isNameValid = CheckName(name, filename, position);

                    if(isNameValid) {

                        Name = name;
                        System.out.println();
                        System.out.println("Thank you " + Name);
                        NameCheck = false;

                            } else{
                                System.out.println("Please enter a suitable name"); 
                                    scanner.nextLine();
                                }


                            } catch(InputMismatchException e){

                            }
                            scanner.close();

                        }

                    }
    
    public static void authenticateUserName(String Name, String filename, int position) {

        Scanner scanner = new Scanner(System.in);
        boolean UserCheck = true; 
        while(UserCheck) {
            try{
                System.out.println();
                System.out.println("Please enter your login " + Name + ": ");
                String username = scanner.nextLine();
                boolean valid = CheckUserName(username, filename, position);
                if(valid) {

                    System.out.println(); 
                    System.out.println("Thank you " + Name);
                    UserCheck = false;
                    
                    } else{System.out.println("Please enter a valid username");}
            } catch(InputMismatchException e){

            }
        }
        scanner.close();

    }
    
}
