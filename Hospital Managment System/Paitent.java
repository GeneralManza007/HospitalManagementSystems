import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class Paitent {
    private static final String FileName = "Paitents.txt";

    public static void createPaitents(){

        try {
                String Filename = "Paitents.txt";
                File file = new File(Filename);
                file.createNewFile();

        } catch(IOException e) {}

    }

    public static void Register(Scanner scanner) {

        int Age = 0;
        String Username = null;

        if(CheckPaitentsExists(FileName)) {

        } else{
                createPaitents();

        }

        System.out.println("Please enter your firstname: ");
        String Firstname = scanner.nextLine();
        System.out.println("Thank you " + Firstname);
        System.out.println();

        System.out.println("Please " + Firstname + ", enter your surname:");
        String Surname = scanner.nextLine();
        System.out.println("Thank you " + Firstname + " " + Surname);
        System.out.println();

        while(true) {

                try{

                        System.out.println("Please enter your age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        if(age > 0) {

                            Age = age;
                            System.out.println();
                            break;

                                    }
                                        
                                } catch(InputMismatchException e) {}
                            }

            System.out.println("Please enter your gender: ");
            String gender = scanner.nextLine();                       
            System.out.println();
                                    
             while(true) {

                 try{                       
                        System.out.println("Please enter a username: ");                        
                        String username = scanner.nextLine();                        

                        if(CheckUsernameORName(FileName, username, 4)) {
                                System.out.println("This username already exists. Please use another");            
                                                        
                        } else{                        
                                  Username = username;                  
                                  System.out.println();                  
                                  break;                 
                                 }
                        

                            } catch(InputMismatchException e) {}
                    }

            System.out.println("Please enter a password: ");
            String password = scanner.nextLine();
            System.out.println();

            AddPaitentToBase(FileName, Firstname, Surname, Age, gender, Username, password);
            System.out.println("Thank you. You are now registered. For security reasons. Please go and login");                                         
    }

    public static String IsNameValid(Scanner scanner, String filename) {

        String name = "";

        while(true) {

                System.out.println();                    
                System.out.println("Please enter your name");        
                name = scanner.nextLine();                                

                if(CheckUsernameORName(FileName, name, 0)) {
                    System.out.println();
                    break;

            } else{

                    System.out.println("Please enter a valid name");
                }
            }

            return name;
    }

    public static void PasswordAuth(Scanner scanner, String Username, String Name, String FileName) {
            boolean validated = true;

            for(int attempt = 3; attempt >= 0; attempt--) {
                                    
                try{

                    System.out.println();
                    System.out.println("Please enter your passsword");
                    String Password = scanner.nextLine();

                    boolean valid = CheckPassword(FileName, Username, Password);

                    if(valid) {
                                System.out.println("Welcome back " + Name); 
                                    System.out.println(); 
                                    validated = false;
                                    break;
                                    
                            } else{System.out.println("Please enter a valid password. You have " + attempt + " attempts left");
                                    
                        }

                    } catch(InputMismatchException e){

                        }

                }
                
                if(validated) {

                        System.out.println("Too many invalid attempts. Exiting the program.");
                        System.exit(0);
                    }

    }

    public static String isUsernameValid(Scanner scanner, String filename) {

        String Username = "";

        while(true) {

                System.out.println("Please enter your username: .");
                Username = scanner.nextLine();

                    if(CheckUsernameORName(FileName, Username, 4)) {
                        System.out.println();
                        break;
                } else{
                        System.out.println("Please enter the correct username");
            }
         }
         return Username;
    }

    public static boolean CheckPaitentsExists(String FileName) {

        File file = new File(FileName);
        return file.exists();
    }
    
    public static boolean CheckisPaitentsEmpty(String FileName) {

        File file = new File(FileName);

        return file.length() == 0;
        
    }

    public static void AddPaitentToBase(String FileName, String FirstName, String Surname, int Age, String gender, String Username, String Password) {

            File file = new File(FileName);

            try{
                    if(CheckPaitentsExists(FileName)) {

                    } else{createPaitents();
                    
                        }
                
                try(FileWriter writer = new FileWriter(file, true)) {

                    writer.write(FirstName + "; " + Surname + "; " + Age + "; " + gender + "; " + Username + "; " + Password + '\n');

                }


            } catch(IOException e) {}
    }


    public static boolean CheckUsernameORName(String FileName, String Username, int Position) {

        try{
                File file = new File(FileName);

                try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()) {

                        String PaitentInfo = reader.nextLine();
                        String [] PaitentInfoArr = PaitentInfo.split("; ");

                        if(PaitentInfoArr[Position].equalsIgnoreCase(Username)) {

                            return true;
                        }
                    }
                }

            } catch(FileNotFoundException e) {}

        return false;
    }

    public static boolean CheckPassword(String Filename, String username, String password) {

        try{
                File file = new File(Filename);

                try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()) {

                        String PaitentInfo = reader.nextLine();
                        String [] PaitentInfoArr = PaitentInfo.split("; ");

                        if(PaitentInfoArr.length > 0 && PaitentInfoArr[4].equals(username) && PaitentInfoArr[5].equals(password))

                            {
                                return true;
                            
                        }

                    }

                }


        } catch(IOException e) {}

        return false;
    }

    public static boolean CheckPaitentIsInBooking(String Filename, String FirstName, String Surname, int Age, String gender) {

    try{
        File file = new File(Filename);

        try(Scanner reader = new Scanner(file)){

            while(reader.hasNext()) {

                String CheckIn = reader.nextLine();

                String [] CheckInArr = CheckIn.split("; ");

                if(CheckInArr[0].equalsIgnoreCase(FirstName) && CheckInArr[1].equalsIgnoreCase(Surname) && Integer.parseInt(CheckInArr[2]) == Age && CheckInArr[3].equalsIgnoreCase(gender))  {

                            return true;

                }

            }

        }


        }catch (IOException e) {}

        return false;
    }

    public static void AddCheckInToPaitent(String Filename, String Firstname, String Surname, int Age, String gender){

    if(CheckPaitentIsInBooking(Filename, Firstname, Surname, Age, gender)) {
    
    try{
        File file = new File(Filename);
        File tempFile = new File("temp.txt");

        try(Scanner reader = new Scanner(file);
            FileWriter writer = new FileWriter(tempFile)){

                while(reader.hasNextLine()) {

                    String line = reader.nextLine();
                    String [] parts = line.split("; ");

                    if(parts.length > 8 && parts[0].equalsIgnoreCase(Firstname) && parts[1].equalsIgnoreCase(Surname) && Integer.parseInt(parts[2]) == Age && parts[3].equalsIgnoreCase(gender)) {

                        line = line + "; Checked In";

                    }
                    writer.write(line + System.lineSeparator());

                }

                
            }
            file.delete();
            tempFile.renameTo(file);


        }catch (IOException e) {e.printStackTrace();}

        }
    }

    public static boolean CheckAlreadyCheckedIn(String FileNameOfPaitents, String Filenameofbookings, String Username){

        String Firstname = null;
        String Surname = null;
        int Age = 0;
        String gender = null;

        try{    
                File file = new File(FileNameOfPaitents);
                File SecondFile = new File(Filenameofbookings);

                try(Scanner reader = new Scanner(file);
                    Scanner SecondReader = new Scanner(SecondFile)){

                    while(reader.hasNext()) {

                        String Checkin = reader.nextLine();
                        String [] CheckinArr = Checkin.split("; ");

                        if(CheckinArr[4].equalsIgnoreCase(Username)) {

                            Firstname = CheckinArr[0];
                            Surname = CheckinArr[1];
                            Age = Integer.parseInt(CheckinArr[2]);
                            gender = CheckinArr[3];

                        }

                    }

                    while(SecondReader.hasNext()){

                        String Booking = SecondReader.nextLine();
                        String [] BookingArr = Booking.split("; ");

                        if(BookingArr[0].equalsIgnoreCase(Firstname) && BookingArr[1].equalsIgnoreCase(Surname) && Integer.parseInt(BookingArr[2]) == Age && BookingArr[3].equalsIgnoreCase(gender)) {

                            if(BookingArr.length > 9 && BookingArr[10].equalsIgnoreCase("Checked In"))  {

                                return true;
                            }

                        }

                    }
                    
                }


        } catch(IOException e) {} catch(ArrayIndexOutOfBoundsException e) {return false;}

        return false;

    }

    public static void viewNextAppointment(String Filename, String paitentsFilename , String Username) {

            String firstname = null;
            String surname = null;
            int Age = 0;
            String gender = null;

            try{    
                    File file = new File(Filename);
                    File SecondFile = new File(paitentsFilename);

                    try(Scanner reader = new Scanner(file);
                            Scanner SecondReader = new Scanner(SecondFile)){
                                
                        while(SecondReader.hasNext()) {

                            String Paitent = SecondReader.nextLine();
                            String [] PaitentArr = Paitent.split("; ");

                            if(PaitentArr[4].equalsIgnoreCase(Username)) {

                                firstname = PaitentArr[0];
                                surname = PaitentArr[1];
                                Age = Integer.parseInt(PaitentArr[2]);
                                gender = PaitentArr[3];


                            }


                        }

                        while(reader.hasNext()) {

                            String booking = reader.nextLine();
                            String [] bookingArr = booking.split("; ");

                            if(bookingArr[0].equalsIgnoreCase(firstname) && bookingArr[1].equalsIgnoreCase(surname) && Integer.parseInt(bookingArr[2]) == Age && bookingArr[3].equalsIgnoreCase(gender)) {

                                System.out.println();
                                System.out.println("Your next appointment " + firstname + ", is on " + bookingArr[5] + " with " + bookingArr[8] + " " + bookingArr[9] + " at " + bookingArr[6] + ":" + bookingArr[7]);
                                System.out.println();
                                System.out.println("The Time Until Your Appointment is: ");
                                String dateTimeString = bookingArr[5] + " " + bookingArr[6] + ":" + bookingArr[7];
                                LocalDateTime appointmentDatetime = Bookings.parseAppointmentDateTime(dateTimeString);
                                Bookings.displayTimeUntilAppointment(appointmentDatetime);
                                System.out.println();

                            }
                        }

                    }


            } catch(IOException e) {}

    }

    public static void CheckIN(String FileNameToGet, String FileNameToWriteTo , String Username) {

        String Firstname = null;
        String Surname = null;
        int Age = 0;
        String gender = null;

        try{    
                File file = new File(FileNameToGet);

                try(Scanner reader = new Scanner(file)){

                    while(reader.hasNext()) {

                        String Checkin = reader.nextLine();
                        String [] CheckinArr = Checkin.split("; ");

                        if(CheckinArr[4].equalsIgnoreCase(Username)) {

                            Firstname = CheckinArr[0];
                            Surname = CheckinArr[1];
                            Age = Integer.parseInt(CheckinArr[2]);
                            gender = CheckinArr[3];
                            

                        }

                    }
                    AddCheckInToPaitent(FileNameToWriteTo, Firstname, Surname, Age, gender);
                }


        } catch(IOException e) {}
    }
    
    
    public static void paitent() {
            Scanner scanner = new Scanner(System.in);
            int Age = 0;
            String Username = null;
            String Name = null;
            String BookingsFilename = "Bookings.txt";

            System.out.println();
            System.out.println("Welecome to the paitents's page");
            System.out.println();
            System.out.println("If you are already registed with us. Please login!");
            System.out.println("If not. Please register. ");
            System.out.println();

            boolean Paitent = true;

            while(Paitent) {

                try{
                    System.out.println("1. Register: ");
                    System.out.println("2. Login: ");
                    System.out.println("3. Go Back");
                    System.out.println();

                    int Choice = scanner.nextInt();
                    scanner.nextLine();

                    switch(Choice) {


                            case 1:
                                    Register(scanner);

                            case 2:
                                    
                                if(!CheckPaitentsExists(FileName)) {

                                    createPaitents();
                                }

                                if(CheckisPaitentsEmpty(FileName)) {

                                    System.out.println("Please Register.");
                                    break;
                                }

                                Name = IsNameValid(scanner, FileName);
                                System.out.println(Name);
                                System.out.println();

                                Username = isUsernameValid(scanner, FileName);
                                System.out.println(Username);
                                System.out.println();

                                PasswordAuth(scanner, Username, Name, FileName);
                                System.out.println();
                                break;





                            case 3:
                                    System.out.println("Leaving now!");
                                   System.exit(0);
                                    
                    }

                boolean options = true;

                while(options) {

                    try{
                            System.out.println("Here are your options " + Name);
                            System.out.println();
                            System.out.println("1. Check in");   
                            System.out.println("2. View Next Appointment");
                            System.out.println("3. Leave");

                            int Chosen = scanner.nextInt();
                            scanner.nextLine();

                            switch(Chosen) {

                                case 1:
                                        System.out.println(Username);

                                        if(CheckAlreadyCheckedIn(FileName, BookingsFilename, Username)) {
                                            
                                            System.out.println();
                                            System.out.println("You have already checked in");
                                            System.out.println();
                                            break; 
                                        }

                                        System.out.println();
                                        CheckIN(FileName, BookingsFilename, Username);
                                        System.out.println("You are now checked in.");
                                        System.out.println();
                                        break;
                                        

                                case 2:
                                        viewNextAppointment(BookingsFilename, FileName, Username);
                                        scanner.nextLine();
                                        break;

                                case 3:
                                        System.out.println("Leaving now!");
                                        System.exit(0);

                                default:
                                        
                            }
                        
                    } catch(InputMismatchException e){}
                }
                


                } catch(InputMismatchException e) {

            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        paitent();
    }
}

