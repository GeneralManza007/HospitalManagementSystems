import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Admin {
    private static final String AdminFilename = "Admin.txt";
    private static final String ReceptionistFilename = "Receptionist.txt";
    private static final String DoctorsFilename = "Doctor.txt";

    public static boolean UsernameCheck(String Username) {

        try{

            String fileName = "Admin.txt";
            File file = new File(fileName);
            Scanner reader =  new Scanner(file);

            String Admin_UserName = reader.nextLine();

            String [] Admin_UserName_arr  = Admin_UserName.split("; ");

                            //System.out.println(Admin_UserName_arr[0]);
            reader.close();

            if(Admin_UserName_arr[0].equals(Username)) {return true;}  

        } catch(FileNotFoundException e){}

        return false;      
    }

    public static boolean CheckPassword(String Password) {

            try{
                   String FileName = "Admin.txt";

                   File file = new File(FileName);
                   Scanner reader = new Scanner(file);

                   String Admin_Password = reader.nextLine();

                   String [] Admin_Password_Arr = Admin_Password.split("; ");

                   reader.close();

                   if(Admin_Password_Arr[1].equals(Password)) {return true;}
                    

            } catch(FileNotFoundException e) {}

            return false;
    }

    public static String formatNumberWithCommas(int number) {
    
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.UK);

            return numberFormat.format(number);

    }

    public static void ViewPaitents(String filename) {

        try{
                File file = new File(filename);

                try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()){

                        String Paitents = reader.nextLine();
                        String [] PaitentsArr = Paitents.split("; ");

                        System.out.println();
                        System.out.println("Fullname: " + PaitentsArr[0] + " " + PaitentsArr[1]);
                        System.out.println();
                        System.out.println("Age: " + PaitentsArr[2]);
                        System.out.println();
                        System.out.println("Gender: " + PaitentsArr[3]);
                        System.out.println();
                        System.out.println("Username: " + PaitentsArr[4]);
                        System.out.println();
                        System.out.println("Password: " + PaitentsArr[5]);
                        System.out.println();


                    }

                }

                
                

        } catch(IOException e) {}

    }

    public static String getName(String Filename){

        String name = null;

        try{    
                File file = new File(Filename);

                try(Scanner reader = new Scanner(file)) {

                    String Admin = reader.nextLine();
                    
                    String [] AdminArr = Admin.split("; ");

                    name = AdminArr[2];

                    return name;
                
                }

        } catch(IOException e) {} catch(ArrayIndexOutOfBoundsException e) {System.out.println("Please add a name to the file");}
        
        return name;
    }

    public static void viewDoctors(String filename){

        try{

                File file = new File(filename);

                try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()) {

                        String Doctors = reader.nextLine();

                        String [] DoctorsArr = Doctors.split("; ");

                        System.out.println();
                        System.out.println("Fullname: " + DoctorsArr[0] + " " + DoctorsArr[1] + " " + DoctorsArr[2]);
                        System.out.println();
                        System.out.println("Age: " + DoctorsArr[6]);
                        System.out.println();
                        System.out.println("Gender: " + DoctorsArr[7]);
                        System.out.println();
                        System.out.println("Username: " + DoctorsArr[4]);
                        System.out.println();
                        System.out.println("Password: " + DoctorsArr[5]);
                        System.out.println();
                        System.out.println("Salary: " + "£" + formatNumberWithCommas(Integer.parseInt(DoctorsArr[3])));
                        System.out.println();


                    }

                }

        } catch(IOException e){}
    }

    public static void viewReceptionists(String filename) {

        try{
                File file = new File(filename);

                try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()) {
                        
                        String Receptionists = reader.nextLine();
                        String [] ReceptionistArr = Receptionists.split("; ");

                        System.out.println();
                        System.out.println("Full name: " + ReceptionistArr[0] + " " + ReceptionistArr[1]);
                        System.out.println();
                        System.out.println("Age: " + ReceptionistArr[2]);
                        System.out.println();
                        System.out.println("Gender: " + ReceptionistArr[3]);
                        System.out.println();
                        System.out.println("Username: " + ReceptionistArr[5]);
                        System.out.println();
                        System.out.println("Password: " + ReceptionistArr[6]);
                        System.out.println();
                        System.out.println("Salary: " + "£" + formatNumberWithCommas(Integer.parseInt(ReceptionistArr[4])));
                        System.out.println();

                    }

                }


        } catch(IOException e){}
    }

    public static void PaitentSystem(Scanner scanner) {
        String PaitentsFile = "Paitents.txt";

        boolean Paitents = true;

        while(Paitents) {

            try{
                    System.out.println();
                    System.out.println("1.View Paitents Records");
                    System.out.println("2.View Doctors Paitents");
                    System.out.println("Go Back");

                    int Choice = scanner.nextInt();
                    scanner.nextLine();

                    switch(Choice) {

                        case 1: 
                                if(!Paitent.CheckPaitentsExists(PaitentsFile)) {

                                    Paitent.createPaitents();
                                }

                                if(Paitent.CheckisPaitentsEmpty(PaitentsFile)) {

                                    System.out.println();
                                    System.out.println("No Paitents present");
                                    System.out.println();
                                    break;

                                }

                                ViewPaitents(PaitentsFile);
                                break;


                        case 2:
                                String DoctorsUsername = null;

                                while(true) {
                                System.out.println("Please Enter The Username Of The Doctor You Would Like To View: ");
                                String doctorsUsername = scanner.nextLine();

                                if(Doctor.CheckDoctor(doctorsUsername)) {
                                     System.out.println();
                                     System.out.println("Thank you.");
                                     DoctorsUsername = doctorsUsername;
                                     break;

                                } else{

                                    System.out.println("Please enter a valid username");
                                }

                                }
                                System.out.println();
                                System.out.println("Here you go: ");

                                if(!Doctor.CheckPatients(DoctorsUsername)) {

                                    Doctor.CreatePatients(DoctorsUsername);
            
                                        }

                                if(Doctor.CheckPaitentsIsEmpty(DoctorsUsername)) {

                                    System.out.println("There are no paitents");
                                    break;

                                        }
                                
                                String DoctorsFilename = DoctorsUsername + "Paitents.txt";
                                Doctor.ViewPatients(DoctorsFilename);
                                break;
                        
                        case 3:
                                System.out.println("Leaving Now!");
                                Paitents = false;
                                break;

                        default:
                                System.out.println("Please enter a valid choice");

                    }



            } catch(InputMismatchException e) {}
        }

    }


    public static void AdminUser() {
        
        Scanner scanner = new Scanner(System.in);
        boolean Selection = true;   
       
        Financial Activities = new Financial(0,0);
        Medical medical = new Medical();
        Chemical chemical = new Chemical();
        Operating operating = new Operating();

        EquipmentInfo Medical_Total = medical.Equipment();
        int New_Total = Medical_Total.getTotal();
        String NewMedicalTotalOut = formatNumberWithCommas(New_Total);

        ChemicalInfo Chemical_Total = chemical.Chemicals();
        int Chem_Total = Chemical_Total.getTotal();
        String NewChemicalTotalOut = formatNumberWithCommas(Chem_Total);

        OperatingInfo Operating_Total = operating.Operatings();
        int Operatin_Total = Operating_Total.getTotal();
        String NewOperatingOut = formatNumberWithCommas(Operatin_Total);
        
        int Starting_Balance = Activities.Starting_Balance();
        String Starting_Balance_Out = formatNumberWithCommas(Starting_Balance);

        int GovermentPay = Activities.GovermentPay();
        String GovermentPay_Out = formatNumberWithCommas(GovermentPay);

        int Balance_After_Government_Pay = Starting_Balance + GovermentPay;
        String Balance_After_Government_Pay_Out = formatNumberWithCommas(Balance_After_Government_Pay);

        int InvestedAmount = Activities.Invested();
        String InvestedAmountOut = formatNumberWithCommas(InvestedAmount);
        
        int BalanceAfterGovernAndInvested = Balance_After_Government_Pay + InvestedAmount;
        String Balance_After_Government_And_Investment_PayOut = formatNumberWithCommas(BalanceAfterGovernAndInvested);

        int DoctorSalary = Activities.DoctorSalary();
        String DoctorSalaryOut = formatNumberWithCommas(DoctorSalary);

        int ReceptionistSalary = Activities.ReceptionistSalary(ReceptionistFilename);
        String ReceptionistSalaryOut = formatNumberWithCommas(ReceptionistSalary);

        int AdminSalary = Activities.AdminSalary(AdminFilename);
        String AdminSalaryOut = formatNumberWithCommas(AdminSalary);
        

        while(Selection) {
            
            try{    

                    System.out.println("Welcome to the admin page!");
                    System.out.println("Here are your options:");
                    System.out.println();
                    System.out.println("1.Login");
                    System.out.println("2.Go back");

                    System.out.println("Choice: ");
                    int Choice = scanner.nextInt();
                    scanner.nextLine();

                switch(Choice){

                    //Scanner scanner = new Scanner(System.in);
                case 1:
                        System.out.println("Please login below:");
                        System.out.println();
                        System.out.println("If you are a new admin the system will already have your details in it's database");
                        System.out.println();


                        boolean validate = true;

                        while (validate){

                        try{    
                            System.out.println("Please enter your admin username");
                            String Username = scanner.nextLine();

                            boolean Valid = UsernameCheck(Username);

                            if(Valid) {System.out.println("Thank you, " + Username); validate = false;
                        
                                } else{System.out.println("Now " + Username + " Please enter the right username!");}

                            }catch(InputMismatchException e){}

                        } 
                        

                        System.out.println();

                        validate = true;

                        for(int attempt = 3; attempt >= 0; attempt--) {
                            
                            try{

                                System.out.println("Please enter your passsword");
                                String Password = scanner.nextLine();

                                boolean valid = CheckPassword(Password);

                                if(valid) {System.out.println("Welcome back"); 
                                System.out.println();
                                validate = false;
                                break;
                            
                                } else{System.out.println("Please enter a valid password. You have " + attempt + " attempts left");
                            
                                }

                            } catch(InputMismatchException e){

                                }

                            }
                            if(validate) {
                                System.out.println("Too many invalid attempts. Exiting the program.");
                                System.exit(0);
                            }

                        
                        boolean options = true;

                        while (options){

                            System.out.println("This is the system overview");

                            //Print the systems overview in a certain phrase.
                            
                            System.out.println("1. Financial Activites");
                            System.out.println("2. View Stock");
                            System.out.println("3. View Doctors");
                            System.out.println("4. View Receptionists");
                            System.out.println("5. View Patients records");
                            System.out.println("6. View To-Do List");
                            System.out.println("7. Veiw OR Board");
                            System.out.println("8. Exit");

                            System.out.println();
                            System.out.println("To-go-to:");
                            int Chosen = scanner.nextInt();
                            scanner.nextLine();

                            switch(Chosen) {

                                case 1: 
                                        System.out.println();
                                        System.out.println("Money In:");
                                        System.out.println();
                                        System.out.println("The hospital started today with " + "£" + Starting_Balance_Out);
                                        System.out.println();
                                        System.out.println("The amount of money paid in by the goverment today was " + "£" + GovermentPay_Out);
                                        System.out.println();
                                        System.out.println("The amount we had after the payment made by the government was " + "£" + Balance_After_Government_Pay_Out);
                                        System.out.println();
                                        System.out.println("The amount of money invested today was " + "£" + InvestedAmountOut);
                                        System.out.println();
                                        System.out.println("Our Balance After Investments and Goverment Payment was " + "£" + Balance_After_Government_And_Investment_PayOut);
                                        System.out.println();
                                        System.out.println("Money Out:");
                                        System.out.println();
                                        System.out.println("The Total amount paid this month to Doctors was " + "£" + DoctorSalaryOut);
                                        System.out.println();
                                        System.out.println("The Total amount paid this month to Receptionists was " + "£" + ReceptionistSalaryOut);
                                        System.out.println();
                                        System.out.println("The Total amount paid this month to Admins was " + "£" + AdminSalaryOut);
                                        System.out.println();
                                        System.out.println("The amount we spent on new equipment today was " + "£" + NewMedicalTotalOut);
                                        System.out.println();
                                        System.out.println("This was spent on:");

                                        for(String Entry : Medical_Total.getKeys()) {
                                            
                                            System.out.println();
                                            System.out.println("New "+ Entry);
                                        }
                                        System.out.println();
                                        System.out.println("The amount we spent on purchasing new chemicals today was " + "£" + NewChemicalTotalOut);
                                        System.out.println();
                                        System.out.println("This was spent on:");

                                        for(String Entry : Chemical_Total.getKeys()) {

                                                System.out.println();
                                                System.out.println(Entry);

                                        }
                                        System.out.println();
                                        System.out.println("The amount we spent on operating/maintenance today was " + "£" + NewOperatingOut);
                                        System.out.println();
                                        System.out.println("This was spent on: ");
                                        
                                        for (String Entry : Operating_Total.getKeys()) {

                                            System.out.println();
                                            System.out.println(Entry);
                                        }
                                        System.out.println();

                                        scanner.nextLine();
                                        break;

                                case 2:
                                        Stock stock =  Stock.interactWithStock();
                                        stock.StockMainOptions(getName(AdminFilename));
                                        break;
                                        

                                case 3:
                                        viewDoctors(DoctorsFilename);
                                        scanner.nextLine();
                                        break;

                                case 4:
                                        viewReceptionists(ReceptionistFilename);
                                        scanner.nextLine();
                                        break;

                                case 5:
                                        PaitentSystem(scanner);
                                        break;
                                case 6:
                                        ToDoList.ToDo();
                                        break;
                                case 7:
                                        OR.OperatingRoom();
                                        break;          

                                case 8:
                                        System.out.println("leaving now!");
                                        options = false;
                                        break;
                                        
                                        
                                        

                            }
                            
                            

                        }
                        break;
                
                case 2:
                        System.out.println("Ok Leaving now!");
                        Selection = false;

                default:
                        System.out.println("Invalid choice please choose a valid one.");


                }
            } catch(InputMismatchException e){}
        
        }
        scanner.close();

    }

    public static void main(String[] args) {

        AdminUser();

    }
    
}
