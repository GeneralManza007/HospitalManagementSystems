import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class OR {

    public static boolean ORexists(String FileName) {

        File file = new File(FileName);
        return file.exists();
    }

    public static void CreateOR() {

        try{ 
                String FileName = "OperatingRoom.txt";
                File file = new File(FileName);
                file.createNewFile();


        } catch(IOException e) {}

    }

    public static boolean ORisEmpty(String FileName) {

        File file = new File(FileName);
        return file.length() == 0;

    }

    public static boolean CheckName(String FileName, String Name, int position) {

        File file = new File(FileName);

            try(Scanner scanner = new Scanner(file)) {

                while(scanner.hasNextLine()) {

                        String line = scanner.nextLine();
                        String [] attributes = line.split("; ");

                            if(attributes.length >= position && attributes[position].equalsIgnoreCase(Name)) {

                                    return true;

                            }

                }

            } catch(FileNotFoundException e) {


         }

            return false;

    }

    public static void AddToOR(String FileName, String OperatingRoom, String DoctorsName, String PaitentsName, String Condition, String Procedure) {
        
            File file = new File(FileName);

            try(FileWriter writer = new FileWriter(file, true)) {

                    writer.write(OperatingRoom + ", " + DoctorsName + ", " + PaitentsName + ", " + Condition + ", " + Procedure);


            } catch(IOException e){

            }
    }

    public static boolean isORinUse(String FileName, String OR, String DoctorsName, String Paitent) {

        try(Scanner scanner = new Scanner(new File(FileName))) {

            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String [] attributes = line.split(", ");
                if(attributes.length >= 3 && attributes[0].equalsIgnoreCase(OR) && attributes[1].equalsIgnoreCase(DoctorsName) && attributes[2].equalsIgnoreCase(Paitent)) {

                    return true;

                    }

                }

            } catch(IOException e) {

        }

        return false;
    }


    public static void RemoveORUsage(String FileName, String OR, String DoctorsName, String Paitent) {

        File inputFile = new File(FileName);
            File tempFile = new File("temp.txt");

            try(Scanner scanner = new Scanner(inputFile);
                    FileWriter writer = new FileWriter(tempFile)) {

                        while(scanner.hasNextLine()) {

                            String line = scanner.nextLine();
                            String[] attributes = line.split(", ");

                            if(attributes.length >= 2 && !attributes[0].equalsIgnoreCase(OR) &&
                            !attributes[1].equalsIgnoreCase(DoctorsName) &&
                            !attributes[3].equalsIgnoreCase(Paitent)) {

                                writer.write(line + System.lineSeparator());

                            }

                        }


                    } catch(IOException e) {

                    }
                    if(!tempFile.renameTo(inputFile)) {

                        System.out.println("Error renaming the file.");
                    }
            

        }

    
    

    

    public static void ViewOperatingRoom(String FileName){

        File file = new File(FileName);

        try(Scanner reader = new Scanner(file)) {

            while(reader.hasNextLine()) {

                String line = reader.nextLine();
                String [] attributes = line.split(", ");

                System.out.print("Operating room: " + attributes[0]);
                System.out.print(", ");
                System.out.print("Doctor proceding: " + attributes[1]);
                System.out.print(", ");
                System.out.print("Paitent: " + attributes[2]);
                System.out.print(", ");
                System.out.print("Condition: " + attributes[3]);
                System.out.print(", ");
                System.out.print("Procedure: " + attributes[4]);
                System.out.println();

            }

        } catch(IOException e) {}

    }

    public static void OperatingRoom() {

        Scanner scanner = new Scanner(System.in);
        boolean begining = true;

        while(begining) {

            try{
                    System.out.println();
                    System.out.println("Please select");
                    System.out.println();
                    System.out.println("1. View OR Board");
                    System.out.println("2. Add to OR Board");
                    System.out.println("3. Remove from OR Board");
                    System.out.println("4. Go back");

                    System.out.println("Choice: ");
                    int Choice = scanner.nextInt();
                    scanner.nextLine();

                    switch(Choice) {

                        case 1:

                                String FileName = "OperatingRoom.txt";
                                boolean CheckORexists = ORexists(FileName);

                                if(CheckORexists) {
                                    boolean isOREmpty = ORisEmpty(FileName);
                                        
                                        if(isOREmpty) {
                                            
                                            System.out.println("There is no one sceduled today. Please add them.");
                                            System.out.println();

                                        } else{
                                                System.out.println();
                                                ViewOperatingRoom(FileName);
                                        
                                    }

                                } else{System.out.println("You have no one sceduled. Please add them.");

                                                CreateOR();
                                            } 
                                break;
                        
                        case 2:
                                String Filename = "OperatingRoom.txt";
                                String Doctors = "Doctor.txt";
                                boolean checkForOR = ORexists(Filename);

                                if (checkForOR) {

                                } else{CreateOR();
                                
                                    }
                                boolean checken = true;

                                while(checken) {

                                    try{

                                            System.out.println();
                                            System.out.println("Please enter the consecutive infomation: ");
                                            System.out.println();

                                            System.out.println("The Operating room: ");
                                            String OR = scanner.nextLine();
                                            System.out.println();

                                            System.out.println("First name of doctor preforming the procedure: ");
                                            String DoctorsName = scanner.nextLine();
                                            if(CheckName(Doctors, DoctorsName, 1)){

                                            } else{System.out.println("Please try again with the correct doctor"); break;}
                                            System.out.println();

                                            System.out.println("Paitents name: ");
                                            String PaitentsName = scanner.nextLine();
                                            System.out.println();

                                            System.out.println("The Paitents condition: ");
                                            String Condition = scanner.nextLine();
                                            System.out.println();

                                            System.out.println("The procedure being used: ");
                                            String Procedure = scanner.nextLine();

                                            AddToOR(Filename, OR, DoctorsName, PaitentsName, Condition, Procedure);

                                            System.out.println();
                                            System.out.println("Thank you. You may go and check the board. ");
                                            break;


                                    } catch(InputMismatchException e) {


                                    }
                                }
                                break;
                                

                        case 3:
                                boolean checkIt = true;
                                while(checkIt) {
                                    try{
                                        String fileName = "OperatingRoom.txt";
                                        boolean Checkisempty = ORisEmpty(fileName);

                                        if(Checkisempty) {

                                                System.out.println(); 
                                                System.out.println("Please add a scedule");
                                                System.out.println();
                                                break;
                                        }

                                 System.out.println("Please enter the following infomation of the OR scedule you would like to remove.");
                                 System.out.println();

                                 System.out.println("Operating Room: ");
                                 String OR = scanner.nextLine();
                                 System.out.println();

                                 System.out.println("First Name of doctor: ");
                                 String DoctorsName = scanner.nextLine();
                                 System.out.println();

                                 System.out.println("Paitents Name");
                                 String PaitentsName = scanner.nextLine();
                                 System.out.println();

                                 if(isORinUse(fileName, OR, DoctorsName, PaitentsName)) {

                                        RemoveORUsage(fileName, OR, DoctorsName, PaitentsName);
                                        System.out.println("The selected scedule slot is now cleared.");

                                 } else{System.out.println("Please Verify your infomation again. Ensuring everything is exact");
                                
                                }
                                    break;
                                        } catch(InputMismatchException e){System.out.println("Please enter a suitable output"); scanner.nextLine();
                            
                                    }
                                    
                                }
                                break;

                        case 4:
                                System.out.println("Leaving Now!");
                                begining = false;
                                break;
                
                        default:
                                System.out.println("Invalid Choice. Please enter a valid option.");
                    }

                    
            } catch(InputMismatchException e){


            }
        }
            scanner.close();
    }

    public static void main(String[] args) {
        OperatingRoom();
       //Doctor.doctor();
    }
    
}
