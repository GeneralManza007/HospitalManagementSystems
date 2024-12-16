import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;




public class Doctor {
        //private static final String FileName = "Doctor.txt";
        
        public static boolean CheckDoctor(String Username) {

        try{

            String fileName = "Doctor.txt";
            File file = new File(fileName);
        
        try(Scanner reader = new Scanner(file)) {

            while(reader.hasNext()) {

                String Doctor_UserName = reader.nextLine();

                String [] Doctor_UserName_Arr  = Doctor_UserName.split("; ");

                if (Doctor_UserName_Arr.length > 0 && Doctor_UserName_Arr[4].equals(Username)) {
                    return true;

                    } else{continue;}

                }

            } 
            
        }catch(FileNotFoundException e){}


            return false;
        }

        public static boolean CheckName(String name) {

        try{
            String FileName = "Doctor.txt";
            File file = new File(FileName);

            try(Scanner reader = new Scanner(file)) {

            while(reader.hasNext()) {

                String Name = reader.nextLine();
                
                String [] Name_Arr = Name.split("; ");

                if (Name_Arr.length > 0 && Name_Arr[1].equals(name)) {
                    return true;

                        } else{continue;}
                    }

                } 
        
            }catch(FileNotFoundException e){}

                return false;

        }

        public static boolean CheckPassword(String password, String username) {

        try{
            String FileName = "Doctor.txt";
            File file = new File(FileName);

            try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()) {

                        String Password = reader.nextLine();

                        String [] Password_Arr = Password.split("; ");

                        if(Password_Arr.length > 0 && Password_Arr[4].equals(username) && Password_Arr[5].equals(password))
                            {return true;
                            
                        } else{continue;
                        
                        }

                    }   

                }
            } catch(FileNotFoundException e) {}

            return false;
        }

        public static boolean CheckPatients(String name) {

             File file = new File(name+"Paitents.txt");
             return file.exists();

        }

        public static void CreatePatients(String name) {

            try{ 
                    String FileName = name + "Paitents.txt";
                    File file = new File(FileName);
                    file.createNewFile();


            } catch(IOException e) {}

        }

        public static boolean CheckPaitentsIsEmpty(String name) {

                 String FileName = name + "Paitents.txt";
                 File file = new File(FileName);
                 return file.length() == 0;

        
        }

        public static void ViewPatients(String FileName) {

                File file = new File(FileName);

                try(Scanner scanner = new Scanner(file)) {

                    while(scanner.hasNextLine()) {

                        String line = scanner.nextLine();

                        String [] attributes = line.split(", ");

                        System.out.println();
                        System.out.print("Fullname: " + attributes[0] + " " + attributes[1]);
                        System.out.print(", ");
                        System.out.print("Age: " + attributes[2]);
                        System.out.print(", ");
                        System.out.print("Gender: " + attributes[3]);
                        System.out.println(", ");
                        System.out.println();
                        System.out.print("Condition: " + attributes[4]);
                        System.out.print(", ");
                        System.out.print("Treatment: " + attributes[5]);
                        System.out.print(", ");
                        System.out.println("Next Meeting: " + attributes[6]);
                        System.out.println();
                        System.out.println();


                    }

                    


            } catch(FileNotFoundException e){}

        }

        public static boolean isPaitentInFile(String FileName, String firstNameToRemove, String surnameToRemove, int AgeToRemove, String Gender, String ConditionToRemove) {

            try(Scanner scanner = new Scanner(new File(FileName))) {

                    while(scanner.hasNextLine()) {

                        String line = scanner.nextLine();
                        String [] attributes = line.split(", ");
                        if(attributes.length >= 3 && attributes[0].equalsIgnoreCase(firstNameToRemove) && attributes[1].equalsIgnoreCase(surnameToRemove) && Integer.parseInt(attributes[2]) == AgeToRemove && attributes[3].equalsIgnoreCase(Gender) && attributes[4].equalsIgnoreCase(ConditionToRemove)) {

                            return true;

                        }

                    }

            } catch(IOException e) {

            }

            return false;
        }

        public static void removePatientFromFile(String FileName, String firstNameToRemove, String surnameToRemove, int ageToRemove, String GenderToRemove, String conditionToRemove) {

            File inputFile = new File(FileName);
            File tempFile = new File("temp.txt");

            try(Scanner scanner = new Scanner(inputFile);
                    FileWriter writer = new FileWriter(tempFile)) {

                        while(scanner.hasNextLine()) {

                            String line = scanner.nextLine();
                            String[] attributes = line.split(", ");

                            if(attributes.length >= 5 && !attributes[0].equalsIgnoreCase(firstNameToRemove) &&
                            !attributes[1].equalsIgnoreCase(surnameToRemove) &&
                            Integer.parseInt(attributes[2]) != ageToRemove &&
                            !attributes[3].equalsIgnoreCase(GenderToRemove) &&
                            !attributes[4].equalsIgnoreCase(conditionToRemove)) {

                                writer.write(line + System.lineSeparator());

                            }

                        }


                    } catch(IOException e) {

                    }
                    if(!tempFile.renameTo(inputFile)) {

                        System.out.println("Error renaming the file.");
                    }
            

        }



        public static void AddPaitent(String FileName, String FirstName, String Surname, String Age, String Gender, String condition, String treatment, String nextMeeting) {
            File file = new File(FileName);

            try(FileWriter writer = new FileWriter(file, true)) {

                writer.write(FirstName + ", " + Surname + ", " + Age + ", " + Gender + ", " + condition + ", " + treatment + ", " + nextMeeting + "\n");

                } catch(IOException e) {
             }
        }

        public static void EditPaitents(String fileName, String firstNameToEdit, String surnameToEdit, int ageToEdit, String GenderToEdit, String ConditionToEdit, String NewFirstName, String NewSurname, int NewAge, String NewGender, String newCondition, String newTreatement, String nextMeeting){

                File inputFile = new File(fileName);
                File tempFile = new File("temp.txt");

                try(Scanner reader = new Scanner(inputFile);
                    FileWriter writer = new FileWriter(tempFile)) {

                        while(reader.hasNextLine()) {

                            String Paitent = reader.nextLine();
                            String [] PaitentArr = Paitent.split(", ");

                            if(PaitentArr.length >= 6 && PaitentArr[0].equalsIgnoreCase(firstNameToEdit) && PaitentArr[1].equalsIgnoreCase(surnameToEdit) && Integer.parseInt(PaitentArr[2]) == ageToEdit && PaitentArr[3].equalsIgnoreCase(GenderToEdit) && PaitentArr[4].equalsIgnoreCase(ConditionToEdit)) {

                                StringBuilder editedPaitent = new StringBuilder();
                                editedPaitent.append(!NewFirstName.isEmpty() ? NewFirstName : PaitentArr[0]).append(", ");
                                editedPaitent.append(!NewSurname.isEmpty() ? NewSurname : PaitentArr[1]).append(", ");
                                editedPaitent.append(NewAge != -1 ? Integer.toString(NewAge) : PaitentArr[2]).append(", ");
                                editedPaitent.append(!NewGender.isEmpty() ? NewGender : PaitentArr[3]).append(", ");
                                editedPaitent.append(!newCondition.isEmpty() ? newCondition : PaitentArr[4]).append(", ");
                                editedPaitent.append(!newTreatement.isEmpty() ? newTreatement : PaitentArr[5]).append(", ");
                                editedPaitent.append(!nextMeeting.isEmpty() ? nextMeeting : PaitentArr[6]).append(", ");


                                writer.write(editedPaitent.toString() + System.lineSeparator());

                            } else{

                                    writer.write(Paitent + System.lineSeparator());
                                }

                            }

                        } catch(IOException e) {

                    }
                    if(!tempFile.renameTo(inputFile)) {

                          System.out.println("Error renaming the file");
            }
        }

        public static void doctor() {
            Scanner scanner = new Scanner(System.in);
            String Name = null;
            String Username = null;
            
            boolean begin = true;

            while(begin){

                try{ 
                    System.out.println("Welcome to the Doctor page!");
                    System.out.println();
                    System.out.println("Here are your options: ");
                    System.out.println("1. Login");
                    System.out.println("2. Go back");

                    System.out.println("Choice: ");
                    int Choice = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch(Choice){
                        
                    case 1:
                            boolean start = true;

                            while(start) {

                                try{ 
                                    System.out.println();
                                    System.out.println("Please enter your name sir");
                                    String name = scanner.next();
                                    scanner.nextLine();

                                    boolean isNameValid = CheckName(name);

                                    if(isNameValid) {

                                        Name = name;
                                        System.out.println("Thank you " + Name);
                                        break;
                                    
                                        } else{System.out.println("Please input a suitable name");
                                    }

                                } catch(InputMismatchException e){System.out.println("Please input a suitable name");
                                        scanner.nextLine();
                                 }

                            }

                            boolean Start = true;
                            while(Start) {

                                try{
                                    
                                    System.out.println();
                                    System.out.println("Please enter your username: ");
                                    String username = scanner.nextLine();
                                    
                                    boolean valid = CheckDoctor(username);

                                    if(valid) {

                                            System.out.println();
                                            System.out.println("Thank you " + Name);
                                            Username = username;
                                            break;

                                    } else{System.out.println("Please enter a valid username:");
                                
                                }




                            } catch(InputMismatchException e){}
                        }
                        
                        //boolean authenticate = Authentication.authenticatePassword(Name, FileName, 5 );

                        //if(authenticate) {
                    
                    boolean validated = true;

                    for(int attempt = 3; attempt >= 0; attempt--) {
                        
                        try{

                            System.out.println();
                            System.out.println("Please enter your passsword");
                            String Password = scanner.nextLine();

                            boolean valid = CheckPassword(Password, Username);

                            if(valid) {System.out.println("Welcome back " + Name); 
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

                        boolean options = true;

                        while (options) {

                            System.out.println();
                            System.out.println("Here are your options " + Name + ": ");
                            System.out.println();
                            System.out.println("1. View Patients");
                            System.out.println("2. View OR board");
                            System.out.println("3. View Medicine Stock");
                            System.out.println("4. View To-Do-List");
                            System.out.println("5. View Bookings");
                            System.out.println("6. Exit");

                            System.out.println("To-Go-To: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();

                            switch(choice) {

                                case 1:
                                        boolean chosen = true;
                                        while(chosen) {
                                            try{
                                                System.out.println("Please choose:");
                                                System.out.println();
                                                System.out.println("1. View Paitents");
                                                System.out.println("2. Add Paitents");
                                                System.out.println("3. Edit Paitents");
                                                System.out.println("4. Remove Paitents");
                                                System.out.println("5. Go back");
                                                
                                                System.out.println("Choose: ");
                                                int chosed = scanner.nextInt();
                                                scanner.nextLine();

                                                switch(chosed) {
                                                    
                                                    case 1:
                                                            boolean CheckPaitents = CheckPatients(Username);

                                                            if(CheckPaitents) {
                                                                boolean isfileemtpy = CheckPaitentsIsEmpty(Username);
                                                                    
                                                                    if(isfileemtpy) {
                                                                        
                                                                        System.out.println("You have no patients. Please add them.");
                                                                        System.out.println();

                                                                    } else{String FileName = Username + "Paitents.txt";
                                                                            ViewPatients(FileName);
                                                                    
                                                                }

                                                            } else{System.out.println("You have no patients. Please add them.");

                                                                            CreatePatients(Username);
                                                                        } 
                                                            break;
                                                    
                                                    
                                                    case 2:
                                                            boolean CheckForPaitents = CheckPatients(Username);
                                                            String nextMeeting = null;
                                                            int NewAge = 0;
                                                            
                                                            if(CheckForPaitents) {
                                                            
                                                            } else{CreatePatients(Username);}

                                                            while(true){

                                                                try{
                                                                    String FileName = Username + "Paitents.txt";

                                                                     System.out.println();
                                                                     System.out.println("Please enter the following infomation:");
                                                                     System.out.println();

                                                                     System.out.println("First Name:");
                                                                     String PaitentName = scanner.next();
                                                                     System.out.println();

                                                                     System.out.println("Surname:");
                                                                     String PaitentSurname = scanner.next();
                                                                     System.out.println();

                                                                     System.out.println("Age:");
                                                                     String Age = scanner.next();
                                                                     scanner.nextLine();

                                                                     System.out.println();
                                                                     System.out.println("Gender:");
                                                                     String Gender = scanner.next();
                                                                     scanner.nextLine();

                                                                     System.out.println();
                                                                     System.out.println("Paitents Condition:");
                                                                     String PatientCondition = scanner.nextLine();
                                                                     System.out.println();

                                                                     System.out.println("Paitents Treatment:");
                                                                     String PaitentTreatment = scanner.nextLine();
                                                                     System.out.println();

                                                            while(true) {
                                                                try{
                                                                     System.out.println("Next Meeting. If none then write null");
                                                                     String NextMeeting = scanner.nextLine();
                                                                     System.out.println();

                                                                     if(NextMeeting.equalsIgnoreCase("null")){
                                                                        nextMeeting = NextMeeting;
                                                                        break;

                                                                     }

                                                                    if(Bookings.CheckDate(NextMeeting)) {
                                                                        nextMeeting = NextMeeting;
                                                                        break;

                                                                    } else{
                                                                        
                                                                            System.out.println("Please enter a valid nextMeeting!");
                                                                    
                                                                        }
                                                                     

                                                                        } catch(InputMismatchException e) {}
                                                                    }
                                                                     
                                                                     AddPaitent(FileName, PaitentName, PaitentSurname, Age, Gender, PatientCondition, PaitentTreatment, nextMeeting);

                                                                     System.out.println("Thank you. You may go and view them.");
                                                                     System.out.println();
                                                                     break;
                                                                     
                                                                     

                                                    

                                                            } catch(InputMismatchException e){}
                                                        


                                                        }
                                                        break;

                                                    case 3:
                                                            String newNextMeeting = null;
                                                            boolean CheckThePaitents = CheckPatients(Username);

                                                            if(CheckThePaitents) {

                                                            } else {
                                                                        CreatePatients(Username);
                                                            }

                                                            boolean CheckisTheFileempty = CheckPaitentsIsEmpty(Username);
                                                            String fileName = Username + "Paitents.txt";

                                                            if(CheckisTheFileempty) {
                                                                System.out.println("Please add a paitent");
                                                                    System.out.println();
                                                                            break;
                                                            }

                                                            boolean EditPaitent = true;

                                                            while(EditPaitent) {

                                                                try{

                                                                System.out.println();
                                                                System.out.println("To Edit A Booking. Please enter the following infomation: ");
                                                                System.out.println();
                                                                
                                                                System.out.println("Firstname of paitent: ");
                                                                String FirstNameToEdit = scanner.nextLine();
                                                                System.out.println();

                                                                System.out.println("Surname of paitent: ");
                                                                String SurnameToEdit = scanner.nextLine();
                                                                System.out.println();

                                                                System.out.println("Age: ");
                                                                int AgeToEdit = scanner.nextInt();
                                                                scanner.nextLine();
                                                                if(AgeToEdit <= 0) {

                                                                    System.out.println("Please enter a valid age above 0");
                                                                    break;
                                                                }
                                                                System.out.println();

                                                                System.out.println("Gender: ");
                                                                String GenderToEdit = scanner.nextLine();
                                                                System.out.println();

                                                                System.out.println("Paitents Current Condition: ");
                                                                String ConditionToEdit = scanner.nextLine();
                                                                System.out.println();

                                                                if(isPaitentInFile(fileName, FirstNameToEdit, SurnameToEdit, AgeToEdit, GenderToEdit ,ConditionToEdit)) {

                                                                    System.out.println("Thank you. Please enter the infomation for the data you want to edit. For the rest: Leave them blank unless specified otherwise. ");
                                                                    System.out.println();

                                                                    System.out.println("Firstname: ");
                                                                    String NewFirstName = scanner.nextLine();
                                                                    System.out.println();

                                                                    System.out.println("Surname: ");
                                                                    String NewSurname = scanner.nextLine();
                                                                    System.out.println();

                                                                    while(true){
                                                                        try{
                                                                                System.out.println("Age or enter -1 to keep the same: ");
                                                                                int newAge = scanner.nextInt();
                                                                                scanner.nextLine();
                                                                                
                                                                                if(newAge >= -1) {
                                                                                    
                                                                                    NewAge = newAge;
                                                                                    System.out.println();
                                                                                    break;

                                                                                } else{

                                                                                        System.out.println("Please enter a valid age above -1");
                                                                                        System.out.println();
                                                                                }

                                                                            } catch(InputMismatchException e){
                                                                                    System.out.println("Please enter a valid age");
                                                                                    scanner.nextLine();
                                                                            }
                                                                        }
                                                                        
                                                                        System.out.println("Gender: ");
                                                                        String NewGender = scanner.nextLine();
                                                                        System.out.println();

                                                                        System.out.println("Condition: ");
                                                                        String newCondition = scanner.nextLine();
                                                                        System.out.println();

                                                                        System.out.println("Treatment: ");
                                                                        String newTreatment = scanner.nextLine();
                                                                        System.out.println(); 

                                                                        while(true) {

                                                                            try{    
                                                                                    System.out.println("Next meeting");
                                                                                    String newnextMeeting = scanner.nextLine();

                                                                                    if(newnextMeeting.isEmpty())
                                                                                    
                                                                                    {
                                                                                        newNextMeeting = newnextMeeting;
                                                                                        break;  

                                                                                    } else{
                                                                                        
                                                                                        if(Bookings.CheckDate(newnextMeeting)) {

                                                                                            newNextMeeting = newnextMeeting;
                                                                                            System.out.println();
                                                                                            break;

                                                                                        } else{

                                                                                                System.out.println("Please enter a valid date");
                                                                                                break;
                                                                                        }
                                                                                    
                                                                                    }

                                                                            } catch(InputMismatchException e) {}
                                                                        }

                                                                        EditPaitents(fileName, FirstNameToEdit, SurnameToEdit, AgeToEdit, GenderToEdit, ConditionToEdit, NewFirstName, NewSurname, NewAge, NewGender, newCondition, newTreatment, newNextMeeting);
                                                                        System.out.println();
                                                                        System.out.println("Thank you it has been edited");
                                                                        break;
                                                                
                                                                    } else{System.out.println("There seems to be an error. Please enter the correct infomation.");}

                                                                } catch(InputMismatchException e) {}

                                                            }
                                                            break;

                                                    
                                                    case 4: 
                                                            boolean CheckForThePaitents = CheckPatients(Username);

                                                            if(CheckForThePaitents) {
                                                            } 
                                                            
                                                            else{

                                                                CreatePatients(Username);

                                                            }
                                                            boolean checken = true;
                                                            while(checken) {
                                                                try{    
                                                                        boolean Checkisempty = CheckPaitentsIsEmpty(Username);
                                                                        String FileName = Username + "Paitents.txt";

                                                                        if(Checkisempty) {System.out.println("Please add a paitent");
                                                                            System.out.println();
                                                                            break;
                                                                        }
                                                                        
                                                                        System.out.println("Please enter the first name of the paitent you would like to remove:");
                                                                        String FirstNameToRemove = scanner.next();
                                                                        System.out.println();

                                                                        System.out.println("Please enter the second name of the paitent you would to remove: ");
                                                                        String surnameToRemove = scanner.next();
                                                                        System.out.println();

                                                                        System.out.println("Please enter the age of the paitent you would like to remove:");
                                                                        int AgeToRemove = scanner.nextInt();
                                                                        scanner.nextLine();
                                                                        System.out.println();

                                                                        System.out.println("Please enter the gender of the paitent you would like to remove: ");
                                                                        String GenderToRemove = scanner.nextLine();
                                                                        System.out.println();

                                                                        System.out.println("Please enter the condition of the paitent you would like to remove:");
                                                                        String ConditionToRemove = scanner.nextLine();
                                                                        System.out.println();

                                                                        if(isPaitentInFile(FileName, FirstNameToRemove, surnameToRemove, AgeToRemove, GenderToRemove,ConditionToRemove)) {
                                                                                removePatientFromFile(FileName, FirstNameToRemove, surnameToRemove, AgeToRemove,GenderToRemove,ConditionToRemove);
                                                                                System.out.println(FirstNameToRemove + " " + surnameToRemove + " has been removed.");
                                                                                break;

                                                                        } else {System.out.println("There is no paitent named " + FirstNameToRemove);
                                                                    
                                                                        }
                                                            
                                                                    } catch(InputMismatchException e){System.out.println("Please enter suitable input"); scanner.nextLine();
                                                                }
                                                            }
                                                            break;
                                                    case 5:
                                                            System.out.println("Leaving now!");
                                                            chosen = false;
                                                            break;
                                                            
                                            }
                                        
                                        } catch(InputMismatchException e){}
                                    }
                                    break;
                                
                                case 2:
                                       OR.OperatingRoom();
                                       break;
                                    
                                case 3:
                                        Stock stock = Stock.interactWithStock();
                                        stock.StockMainOptions(Name);
                                        break;


                                case 4:
                                        ToDoList.ToDo();
                                        break;
                                case 5:
                                        boolean OutPutDoctorName = false;
                                        String fileName = Username + "Bookings.txt";
                                        if(CheckPatients(Username)) {

                                        } else{
                                            CreatePatients(Username);
                                        
                                            }
                                        
                                        Bookings.ViewBookings(fileName, OutPutDoctorName);
                                        break;
                                
                                case 6:
                                        options = false;
                                        break;
                                
                                default:

                                        System.out.println("Invalid Choice. Please enter a valid option.");
                                        
                                        
                            }

                            }
                        //}

                    case 2:
                            System.out.println("Leaving now");
                            begin = false;
                            break;
                    
                    default:
                            System.out.println("Invalid Choice. Please enter a valid option.");
                    }

                } catch(NoSuchElementException e){

                }
            
       }
        scanner.close();
        
    }

    public static void main(String[] args) {

        doctor();

    }
    
}
