import java.io.*;
import java.util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Duration;


public class Bookings {

    private static final String AllDoctorsFileName = "Doctor.txt";
    private static final String FileName = "Bookings.txt";

    public static boolean BookingExists() {

        String Filename = "Bookings.txt";
        File file = new File(Filename);
        return file.exists();
        
    }

    public static boolean BookingIsEmpty(String FileName) {


        File file = new File(FileName);
        return file.length() == 0;

    }

    public static void CreateBookings() {

        try{

            String fileName = "Bookings.txt";
            File file = new File(fileName);
            file.createNewFile();

        } catch(IOException e) {}



    }

    public static boolean DoctorsBookingsExists(String Username) {

        String Filename = Username + "Bookings.txt";
        File file = new File(Filename);
        return file.exists();

    }

    public static void CreateDoctorsBookings(String Username) {

        try {
            String Filename = Username + "Bookings.txt";
            File file = new File(Filename);

            file.createNewFile();

        } catch(IOException e) {

        }

    }

    public static String GetDoctorsUserName(String Filename, String FirstName, String SecondName) {

        String Username = null;

        try{

            File file = new File(Filename);

            if(file.exists())  {


            } else{

                    file.createNewFile();

            }

                try(Scanner reader = new Scanner(file)){

                    while(reader.hasNext()){

                        String DoctorInfo = reader.nextLine();
                        String [] DoctorInfoArr = DoctorInfo.split("; ");

                        if(DoctorInfoArr.length >= 5 && DoctorInfoArr[1].equalsIgnoreCase(FirstName) && DoctorInfoArr[2].equalsIgnoreCase(SecondName)) {

                            Username = DoctorInfoArr[4];
                            return Username;

                        }
        
                    }


                }

            } catch(IOException e) {}

            return Username;
    }

    public static void ViewBookings(String fileName, boolean OutPutDoctorName) {

        File file = new File(fileName);

        try(Scanner reader = new Scanner(file)){
            
            while(reader.hasNext()) {

                String Booking = reader.nextLine();
                String [] BookingArr = Booking.split("; ");

                if(BookingArr.length >= 8) {
                System.out.println();
                System.out.print("Full Name: " + BookingArr[0] + " " + BookingArr[1]);
                System.out.print(", ");
                System.out.print("Age: " + BookingArr[2]);
                System.out.print(", ");
                System.out.println("Gender: " + BookingArr[3]);
                System.out.println();
                System.out.print("Described condition: " + BookingArr[4]);
                System.out.print(", ");
                System.out.print("Date of appointment: " + BookingArr[5]);
                System.out.print(", ");
                System.out.println();

                if(OutPutDoctorName) {

                     System.out.println();
                     System.out.println("Doctors Full Name: " + BookingArr[8] + " " + BookingArr[9] + ",");
                }
            
                System.out.println();
                String formattedTime = String.format("%02d:%02d", Integer.parseInt(BookingArr[6]), Integer.parseInt(BookingArr[7]));
                System.out.println("Time of appointment: " + formattedTime + ",");

                if(BookingArr.length > 10) {
                
                    System.out.println();
                    System.out.println("Checked in: Yes");
                    System.out.println();

                } else {

                    System.out.println();
                    System.out.println("Checked in: No");
                    System.out.println();

                }
                String dateTimeString = BookingArr[5] + " " + BookingArr[6] + ":" + BookingArr[7];
                LocalDateTime appointmentDatetime = parseAppointmentDateTime(dateTimeString);

                System.out.println("Time until appointment: ");
                displayTimeUntilAppointment(appointmentDatetime);
                System.err.println();

                } else {

                        System.out.println("Invalid booking format");
                }

            }


        } catch(FileNotFoundException e){} catch(ArrayIndexOutOfBoundsException e) {System.out.println(); System.out.println("Checked In: No");
            System.out.println();
        }

    }

    public static boolean CheckDoctorExists(String DoctorsFirstName, String DoctorsSurname) {

        String FileName = "Doctor.txt";

        try{
                File file = new File(FileName);

                try(Scanner reader = new Scanner(file)) {

                    while(reader.hasNext()) {

                            String Doctors = reader.nextLine();
                            String [] DoctorsArr = Doctors.split("; ");

                            String CurrentDoctorsFirstName = DoctorsArr[1];
                            String CurrentDoctorsSurname = DoctorsArr[2];

                            if(CurrentDoctorsFirstName.equalsIgnoreCase(DoctorsFirstName) && CurrentDoctorsSurname.equalsIgnoreCase(DoctorsSurname)) {

                                    return true;

                            }
                        }

                    }

                 } catch(FileNotFoundException e) {

            }

        return false;

    }

    public static boolean CheckTime(String timeOfAppoitment) {

        if(timeOfAppoitment.contains(":")) {

                            String [] timeOfAppointmentSeperate = timeOfAppoitment.split(":");
                        
                            if(timeOfAppointmentSeperate.length == 2) {
                            try{
                                int Hour = Integer.parseInt(timeOfAppointmentSeperate[0]);
                                int Minute = Integer.parseInt(timeOfAppointmentSeperate[1]);

                                if(Hour >= 0 && Hour <= 23 && Minute >= 0 && Minute <= 59) {

                                    return true;

                                } else{
                                        return false;

                                }
                                
                            } catch(NumberFormatException e) {

                                    return false;

                                }

                            } else {
                                        return false;
                        } 
                            
                    } else{
                                return false;

            }


    }

    public static boolean CheckOverLap(String filename, int hour, int minute, String DoctorsFirstName, String DoctorsSurname, String DateOfAppointment, String Firstname, String Surname){

        try{
                File file = new File(filename);

            try(Scanner reader = new Scanner(file)) {

                while(reader.hasNext()){

                    String booking = reader.nextLine();
                    String [] booking_Arr = booking.split("; ");

                    String CurrentDateOfAppointment = booking_Arr[5];
                    int Current_hour = Integer.parseInt(booking_Arr[6]);
                    int Current_minutes = Integer.parseInt(booking_Arr[7]);
                    String CurrentDoctorsFirstName = booking_Arr[8];
                    String CurrentDoctorsSurname = booking_Arr[9];
                    int maxAppointmentTime = Current_minutes + 15;
                    int MinAppointmentTime = Current_minutes - 15;
                    String Current_FirstName = booking_Arr[0];
                    String Current_Surname = booking_Arr[1];
                    
                    if(Current_FirstName.equalsIgnoreCase(Firstname) && (Current_Surname.equalsIgnoreCase(Surname)) && CurrentDateOfAppointment.equalsIgnoreCase(DateOfAppointment) && CurrentDoctorsFirstName.equalsIgnoreCase(DoctorsFirstName) && CurrentDoctorsSurname.equalsIgnoreCase(DoctorsSurname)) {

                        continue;
                    }

                    if((CurrentDateOfAppointment.equalsIgnoreCase(DateOfAppointment) && CurrentDoctorsFirstName.equalsIgnoreCase(DoctorsFirstName) && CurrentDoctorsSurname.equalsIgnoreCase(DoctorsSurname)) && ((hour == Current_hour && (minute >= Current_minutes && minute < maxAppointmentTime || minute >= MinAppointmentTime && minute < Current_minutes)) || (hour == Current_hour && Current_minutes == minute))) {

                        return true;


                    }


                }


            }

        } catch(IOException e){}
        return false;
    }

    public static boolean CheckDate(String inputDate) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{        
                LocalDate date = LocalDate.parse(inputDate, dateFormatter);
            
                return true;

        } catch(DateTimeParseException e) {

            return false;

        }


    }

    public static LocalDateTime parseAppointmentDateTime(String inputedDateTime) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try{
                return LocalDateTime.parse(inputedDateTime, dateTimeFormatter);


        } catch(Exception e){

            return null;

        }
    }

    public static void displayTimeUntilAppointment(LocalDateTime appointmentDateTime) {

        if(appointmentDateTime != null) {

            LocalDateTime currentDateTime = LocalDateTime.now();
            Duration timeUntilAppointment = Duration.between(currentDateTime, appointmentDateTime);

            long days = timeUntilAppointment.toDays();
            long hours = timeUntilAppointment.toHoursPart();
            long minutes = timeUntilAppointment.toMinutesPart();

            System.out.println();
            System.out.println("Days: " + days);
            System.out.println("Hours: " + hours);
            String formattedMinutes = String.format("%02d", minutes);
            System.out.println("Minutes: " + formattedMinutes);



        } else{System.out.println("Please enter a valid appointment date and or time format");
    
        }


    }

    public static void AddBooking(String fileName, String Firstname, String Surname, int Age, String gender , String condition, String dayOfAppointment, int hourOfAppointment, int minuteOfAppointment, String DoctorsFirstName, String DoctorsSecondName){

        String SecondFileName = GetDoctorsUserName(AllDoctorsFileName, DoctorsFirstName, DoctorsSecondName) + "Bookings.txt";
        File file = new File(fileName);
        File Secondfile = new File(SecondFileName);
        String formattedMinute = String.format("%02d", minuteOfAppointment);

        try{

            if(Secondfile.exists()) {
            
            } else{

                    Secondfile.createNewFile();
            }  

        } catch(IOException e) {}

        try(FileWriter writer = new FileWriter(file, true)){

            writer.write(Firstname + "; " + Surname + "; " + Age + "; " + gender + "; " + condition + "; " + dayOfAppointment + "; " + hourOfAppointment + "; " + formattedMinute + "; " + DoctorsFirstName + "; " + DoctorsSecondName + "\n");


        } catch(IOException e){}

        try(FileWriter writer = new FileWriter(Secondfile, true)){

            writer.write(Firstname + "; " + Surname + "; " + Age + "; " + gender + "; " + condition + "; " + dayOfAppointment + "; " + hourOfAppointment + "; " + formattedMinute + "; " + "\n");


        } catch(IOException e){}


    }

    public static boolean CheckBookingIsInFile(String fileName, String Firstname, String Surname, int Age, String gender,String dayOfAppointment) {

        try(Scanner scanner = new Scanner(new File(fileName))) {

                    while(scanner.hasNextLine()) {

                        String Booking = scanner.nextLine();
                        String [] BookingArr = Booking.split("; ");
                        if(BookingArr.length >= 4 && BookingArr[0].equalsIgnoreCase(Firstname) && BookingArr[1].equalsIgnoreCase(Surname) && Integer.parseInt(BookingArr[2]) == Age && BookingArr[3].equalsIgnoreCase(gender) && BookingArr[5].equalsIgnoreCase(dayOfAppointment)) {

                            return true;

                        }

                    }

            } catch(IOException e) {

            }

            return false;
        }

        public static boolean CheckValueIsEmpty(String Value) {

            if(Value.isEmpty()) {

                return true;
            }

            return false;
        }

        public static boolean CheckValueIsNegativeOne(int value) {

            if (value == -1) {
                return true;
            }
            else{
                return false;
            
            }
        }

        public static int GetValueOfTime(String filename, String Firstname, String LastName, int Age, String gender, String dayOfAppointment, int Position) {

            int ReturnValue = 0;

            try{
                File file = new File(filename);

                    try(Scanner reader = new Scanner(file)) {

                        while(reader.hasNext()) {

                            String booking = reader.nextLine();
                            String [] bookingsArr = booking.split("; ");
                            
                            if(bookingsArr[0].equalsIgnoreCase(Firstname) && bookingsArr[1].equalsIgnoreCase(LastName) && Integer.parseInt(bookingsArr[2]) == Age && bookingsArr[3].equalsIgnoreCase(gender) && bookingsArr[5].equalsIgnoreCase(dayOfAppointment)) {

                                    ReturnValue = Integer.parseInt(bookingsArr[Position]);
                                    return ReturnValue;

                            }

                        }

                    } 

            } catch(FileNotFoundException e) {}

            return ReturnValue;
        }

        public static void RemoveBooking(String FileName, String firstNameToRemove, String surnameToRemove, int ageToRemove, String genderToRemove, String dayOfAppointment) {

            File inputFile = new File(FileName);
            File tempFile = new File("temp.txt");

            try(Scanner scanner = new Scanner(inputFile);
                    FileWriter writer = new FileWriter(tempFile)) {

                        while(scanner.hasNextLine()) {

                            String Booking = scanner.nextLine();
                            String[] BookingArr = Booking.split(";");

                            if(BookingArr.length >= 8 && BookingArr[0].trim().equalsIgnoreCase(firstNameToRemove) &&
                            BookingArr[1].trim().equalsIgnoreCase(surnameToRemove) &&
                            Integer.parseInt(BookingArr[2].trim()) == ageToRemove && BookingArr[3].trim().equalsIgnoreCase(genderToRemove) &&
                            BookingArr[5].trim().equalsIgnoreCase(dayOfAppointment)) {

                                continue;

                            }

                            writer.write(Booking + System.lineSeparator());

                        }


                    } catch(IOException e) {

                    }
                    if(!tempFile.renameTo(inputFile)) {

                        System.out.println("Error renaming the file.");
                    }
            

    }


    
    public static void EditBooking(String fileName, String firstNameToEdit, String SurnameToEdit ,Integer AgeToEdit, String genderToEdit, String dayOfAppointment, String newFirstName,String newSecondName, int newAge, String newGender, String newCondition, String newDayOfAppointment, int newHourofAppointment, int newMinuteOfAppointment, String newDoctorsFirstName, String newDoctorsSurname, boolean ViewDoctor) {

            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");

            try(Scanner reader = new Scanner(inputFile);
                FileWriter writer = new FileWriter(tempFile)){

                    while(reader.hasNextLine()) {

                        String booking = reader.nextLine();
                        String [] bookingArr = booking.split("; ");

                        if(bookingArr.length >= 8 && bookingArr[0].equalsIgnoreCase(firstNameToEdit) && bookingArr[1].equalsIgnoreCase(SurnameToEdit) && Integer.parseInt(bookingArr[2]) == AgeToEdit && bookingArr[3].equalsIgnoreCase(genderToEdit) && bookingArr[5].equalsIgnoreCase(dayOfAppointment)) {

                            String formattedMinutes = String.format("%02d", newMinuteOfAppointment);

                            StringBuilder editedBooking = new StringBuilder();
                            editedBooking.append(!newFirstName.isEmpty() ? newFirstName : bookingArr[0]).append("; ");
                            editedBooking.append(!newSecondName.isEmpty() ? newSecondName : bookingArr[1]).append("; ");
                            editedBooking.append(newAge != -1 ? Integer.toString(newAge) : bookingArr[2]).append("; ");
                            editedBooking.append(!newGender.isEmpty() ? newGender : bookingArr[3]).append("; ");
                            editedBooking.append(!newCondition.isEmpty() ? newCondition : bookingArr[4]).append("; ");
                            editedBooking.append(!newDayOfAppointment.isEmpty() ? newDayOfAppointment : bookingArr[5]).append("; ");
                            editedBooking.append(newHourofAppointment != -1 ? Integer.toString(newHourofAppointment) : bookingArr[6]).append("; ");
                            editedBooking.append(newMinuteOfAppointment != -1 ? formattedMinutes : bookingArr[7]).append("; ");
                            
                            if(ViewDoctor) {
                            editedBooking.append(!newDoctorsFirstName.isEmpty() ? newDoctorsFirstName : bookingArr[8]).append("; ");
                            editedBooking.append(!newDoctorsSurname.isEmpty() ? newDoctorsSurname : bookingArr[9]);

                            }

                            writer.write(editedBooking.toString() + System.lineSeparator());

                        } else {
                                writer.write(booking + System.lineSeparator());
                        }

                    }


                } catch(IOException e){

                }
                if(!tempFile.renameTo(inputFile)) {

                    System.out.println("Error renaming the file");

                }

    }


    



    public static void bookings() {
        Scanner scanner = new Scanner(System.in);
        String FirstName = null;
        String Surname = null;
        int Age = 0;
        String Gender = null;
        String Condition = null;
        String dayOfAppointment = null;
        int hour = 0;
        int minute = 0;
        String DoctorsFirstName = null;
        String DoctorsSurname = null;

        boolean booking = true;

        while(booking) {

            try{

            System.out.println();
            System.out.println("Please select: ");
            System.out.println();
            System.out.println("1. View Bookings");
            System.out.println("2. Add Booking");
            System.out.println("3. Remove Booking");
            System.out.println("4. Edit booking");
            System.out.println("5. Go back");

            int Choice = scanner.nextInt();
            scanner.nextLine();

            switch(Choice) {

                case 1:
                        if(BookingExists()) {

                        } else {CreateBookings();}

                        if(BookingIsEmpty(FileName)) {

                                System.out.println();
                                System.out.println("There are no bookings. Please add them");
                        } else{ 
                                boolean DoctorOutputName = true;
                                ViewBookings(FileName, DoctorOutputName);
                        }
                        break;




                case 2:
                        if(BookingExists()) {


                        } else{CreateBookings();}

                        System.out.println();
                        System.out.println("Please enter the following:");

                    while(true) {
                        try{
                        System.out.println();
                        System.out.println("First Name: ");
                        String firstName = scanner.next();
                        FirstName = firstName;
                        break;
                        } catch(InputMismatchException e) {}
                    }
                    
                    while(true) {
                        try{
                        System.out.println();
                        System.out.println("Surname: ");
                        String surname = scanner.next();
                        scanner.nextLine();
                        Surname = surname;
                        break;
                        } catch(InputMismatchException e) {}
                    }
                    
                    while(true) {
                        try{
                        System.out.println();
                        System.out.println("Age:");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        
                        if(age > 0) {

                            Age = age;
                            break;

                        } else{
                                System.out.println("Please input a valid age above 0");
                                System.out.println();
                            }
                        } catch(InputMismatchException e) {System.out.println("Please input a suitable age:"); scanner.nextLine();}
                    }

                    while(true) {
                        try{
                        System.out.println();
                        System.out.println("Gender:");
                        String gender = scanner.nextLine();
                        Gender = gender;
                        break;
                        } catch(InputMismatchException e){}
                    }

                    while(true) {
                        try{
                        System.out.println();
                        System.out.println("Supposed Symptoms/Condition: ");
                        String condition = scanner.nextLine();
                        Condition = condition;
                        break;

                        } catch(InputMismatchException e){}
                    }

                    while(true) {
                        try{
                        System.out.println();
                        System.out.println("Date of appointment: ");
                        String DayOfAppointment = scanner.nextLine();

                        if(CheckDate(DayOfAppointment)) {

                            dayOfAppointment = DayOfAppointment;
                            break;

                            } else {System.out.println("Please enter a valid date.");}

                        } catch(NullPointerException e) {System.out.println("Please enter a valid date");}
                    }
                        
                    while(true) {
                        try{
                            System.out.println();
                            System.out.println("TimeOfAppointment: ");
                            String timeOfAppoitment = scanner.nextLine().trim();  
                            boolean CheckTheTime = CheckTime(timeOfAppoitment);

                            if(CheckTheTime) {

                                String [] timeOfAppointmentSeperate = timeOfAppoitment.split(":");
                                int Hour = Integer.parseInt(timeOfAppointmentSeperate[0]);
                                int Minute = Integer.parseInt(timeOfAppointmentSeperate[1]);
                                hour = Hour;
                                minute = Minute;

                            } else{
                                System.out.println("Please enter a valid hour/minute, making sure it is in the format HH:mm");
                                    continue;
                                }
                       

                    
                                System.out.println();
                                System.out.println("Doctors FirstName: ");
                                String doctorsFirstName = scanner.nextLine();
                                DoctorsFirstName = doctorsFirstName;

                                System.out.println();
                                System.out.println("Doctors SecondName: ");
                                String DoctorsSecondName = scanner.nextLine();
                                DoctorsSurname = DoctorsSecondName;
                                System.out.println();

                                if(CheckDoctorExists(DoctorsFirstName, DoctorsSurname)) {
                                } else{
                                    
                                    System.out.println("Please enter a practicing doctor");
                                    continue;
                            }

                                if(CheckOverLap(FileName, hour, minute, DoctorsFirstName, DoctorsSecondName, dayOfAppointment, FirstName, Surname)) {

                                    System.out.println("This time is unavailable. Please choose another");
                                    continue;

                                } else{
                                    
                                    break;
                            
                            }


                         } catch(InputMismatchException e) {}
                    }
                          
                    AddBooking(FileName, FirstName, Surname, Age, Gender ,Condition, dayOfAppointment, hour, minute, DoctorsFirstName, DoctorsSurname);
                    System.out.println();
                    System.out.println("Thank you. It has been added. You may go and check it now");
                    break;
                        
                        


                case 3:
                        String DoctorsFileName = null;
                        if(BookingExists()) {

                        } else {CreateBookings();}

                        if(BookingIsEmpty(FileName)) {

                                System.out.println();
                                System.out.println("There are no bookings to remove. Please add them");
                        } else{

                            System.out.println("To remove a booking. Please enter the following: ");
                            System.out.println();

                            System.out.println("First Name: ");
                            String firstNameToRemove = scanner.nextLine();
                            System.out.println();

                            System.out.println("Surname: ");
                            String surnameToRemove = scanner.nextLine();
                            System.out.println();

                            System.out.println("Age: ");
                            int ageToRemove = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println();

                            System.out.println("Gender: ");
                            String gendertoRemove = scanner.nextLine();
                            System.out.println();

                            System.out.println("Date of appointment: ");
                            String DayOfAppointmenttoremove = scanner.nextLine();
                            System.out.println();

                        while(true) {
                            try{
                            System.out.println("Doctors FirstName: ");
                            String doctorsFirstName = scanner.nextLine();
                            DoctorsFirstName = doctorsFirstName;
                            System.out.println();
                            System.out.println("Doctors Surname: ");
                            String doctorsSurname = scanner.nextLine();
                            DoctorsSurname = doctorsSurname;
                            if(CheckDoctorExists(doctorsFirstName, doctorsSurname)) {
                            String doctorsFileName = GetDoctorsUserName(AllDoctorsFileName,DoctorsFirstName, DoctorsSurname) + "Bookings.txt";
                            System.out.println(doctorsFileName);
                            DoctorsFileName = doctorsFileName;
                            System.out.println();
                            break;
                            } else{System.out.println("Please enter a valid doctors name");}

                            } catch(InputMismatchException e) {}
                        }

                            if (CheckBookingIsInFile(FileName, firstNameToRemove, surnameToRemove, ageToRemove, gendertoRemove, DayOfAppointmenttoremove) && CheckBookingIsInFile(DoctorsFileName, firstNameToRemove, surnameToRemove, ageToRemove, gendertoRemove, DayOfAppointmenttoremove)) {
                                
                                // Removes the booking
                                RemoveBooking(FileName, firstNameToRemove, surnameToRemove, ageToRemove, gendertoRemove, DayOfAppointmenttoremove);
                                RemoveBooking(DoctorsFileName, firstNameToRemove, surnameToRemove, ageToRemove, gendertoRemove, DayOfAppointmenttoremove);

                                System.out.println("It has been removed. Thank you");

                            } else{System.out.println("It seems there was an error. Please enter the correct infomation.");}
                        }
                        break;

                case 4:
                        String newDayOfAppointment = "";
                        int AgeToEdit = 0;
                        String FirstNameOfDoctorToEdit = null;
                        String SurnameOfDoctorToEdit = null;
                        int newAge = 0;

                        if(BookingExists()) {

                        } else {CreateBookings();}

                        if(BookingIsEmpty(FileName)) {

                                System.out.println();
                                System.out.println("There are no bookings to edit. Please add them");
                        } else{

                                System.out.println();
                                System.out.println("To edit a booking. Please enter the following");
                                System.out.println();

                                System.out.println("Firstname of existing paitent");
                                String firstNameToEdit = scanner.nextLine();
                                System.out.println();

                                System.out.println("Surname of existing paitent: ");
                                String SurnameToEdit = scanner.nextLine();
                                System.out.println();
                        
                            while(true) {
                                try{
                                    System.out.println("Age of existing paitent: ");
                                    int ageToEdit = scanner.nextInt();
                                    scanner.nextLine();
                                    AgeToEdit = ageToEdit;
                                    System.out.println();
                                    break;

                                } catch(InputMismatchException e) {System.out.println("Please enter a suitable number");}
                            }

                                System.out.println("Gender of existing paitent: ");
                                String genderToEdit = scanner.nextLine();
                                System.out.println();

                                System.out.println("Date of appointment of existing paitent: ");
                                String dayOfAppointmentToEdit = scanner.nextLine();
                                System.out.println();


                        while(true) {
                                try{
                                System.out.println("First Name of Doctor: ");
                                String firstNameOfDoctorToEdit = scanner.nextLine();
                                
                                System.out.println("Surname of Doctor");
                                String surnameOfDoctorToEdit = scanner.nextLine();
                                System.out.println();
                                
                                if(CheckDoctorExists(firstNameOfDoctorToEdit, surnameOfDoctorToEdit)) {

                                    FirstNameOfDoctorToEdit = firstNameOfDoctorToEdit;
                                    SurnameOfDoctorToEdit = surnameOfDoctorToEdit;
                                    break;

                                } else{System.out.println("Please enter a practising doctor");
                            
                                }

                            } catch(InputMismatchException e){}
                        }       

                                String Doctorsusername = GetDoctorsUserName(AllDoctorsFileName, FirstNameOfDoctorToEdit, SurnameOfDoctorToEdit);
                                String DoctorFileName = Doctorsusername + "Bookings.txt";

                                if(CheckBookingIsInFile(FileName, firstNameToEdit, SurnameToEdit, AgeToEdit, genderToEdit, dayOfAppointmentToEdit) && CheckBookingIsInFile(DoctorFileName, firstNameToEdit, SurnameToEdit, AgeToEdit, genderToEdit, dayOfAppointmentToEdit)) {

                                System.out.println("Thank you. Now please enter the infomation for the data you want to edit. For the others, please leave them blank. Unless instructed otherwise: ");
                                System.out.println();

                                System.out.println("Firstname: ");
                                String newFirstName = scanner.nextLine();
                                System.out.println();

                                System.out.println("Surname: ");
                                String newSecondName = scanner.nextLine();
                                System.out.println();

                        while(true) {

                            try{

                                System.out.println("Age (Enter -1 to keep it the same): ");
                                int NewAge = scanner.nextInt();
                                scanner.nextLine();
                                if(NewAge >= -1) {
                                
                                newAge = NewAge;
                                System.out.println();
                                break;

                            } else {

                                    System.out.println("Please enter a valid age above 0");
                                    System.out.println();
                            }

                            } catch(InputMismatchException e) {}
                            
                        }

                                System.out.println("Gender");
                                String newGender = scanner.nextLine();
                                System.out.println();

                                System.out.println("Condition/Symptoms: ");
                                String newCondition = scanner.nextLine();
                                System.out.println();
                                
                            while(true) {

                                try{
                                System.out.println("Date of appointment: ");
                                String NewDayOfAppointment = scanner.nextLine();
                                System.out.println(NewDayOfAppointment);
                                if(NewDayOfAppointment.isEmpty()) {

                                    break;

                                } else{

                                if(CheckDate(NewDayOfAppointment)) {

                                    newDayOfAppointment = NewDayOfAppointment;
                                    System.out.println();
                                    break;

                                } else {

                                        System.out.println("Please enter a valid date");

                                        }
                                    }

                                } catch(InputMismatchException e) {}
                            }
                            
                            while(true) {
                                try{

                                System.out.println("Hour (Enter -1 to keep the same)");
                                int newHourofAppointment = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println();

                                System.out.println();
                                System.out.println("Minute (Enter -1 to keep the same)");
                                int newMinuteOfAppointment = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println();

                                System.out.println("Doctors Firstname:");
                                String newDoctorsFirstName = scanner.nextLine();
                                System.out.println();

                                System.out.println("Doctors Surname");
                                String newDoctorsSurname = scanner.nextLine();
                                System.out.println();

                                if (CheckValueIsEmpty(newDayOfAppointment)) {

                                    newDayOfAppointment = dayOfAppointmentToEdit;
                                }

                                if(CheckValueIsEmpty(newDoctorsFirstName)) {

                                    newDoctorsFirstName = FirstNameOfDoctorToEdit;

                                }

                                if(CheckValueIsEmpty(newDoctorsSurname)) {

                                    newDoctorsSurname = SurnameOfDoctorToEdit;

                                } 

                                if(CheckValueIsNegativeOne(newHourofAppointment)) {

                                    newHourofAppointment = GetValueOfTime(FileName, firstNameToEdit, SurnameToEdit, AgeToEdit, genderToEdit, dayOfAppointmentToEdit, 6);
                                    
                                }

                                if(CheckValueIsNegativeOne(newMinuteOfAppointment)) {

                                    newMinuteOfAppointment = GetValueOfTime(FileName, firstNameToEdit, SurnameToEdit, AgeToEdit, genderToEdit, dayOfAppointmentToEdit, 7);

                                }

                                System.out.println(newDayOfAppointment);
                                System.out.println(newDoctorsFirstName);
                                System.out.println(newDoctorsSurname);
                                System.out.println(newHourofAppointment);
                                System.out.println(newMinuteOfAppointment);

                                if(!CheckOverLap(FileName, newHourofAppointment, newMinuteOfAppointment, newDoctorsFirstName, newDoctorsSurname, newDayOfAppointment,firstNameToEdit, SurnameToEdit) && (newHourofAppointment >= -1 && newHourofAppointment < 23 && newMinuteOfAppointment >= -1 && newMinuteOfAppointment <= 59)) {
                                    

                                    Boolean ViewDoctor = true;
                                    EditBooking(FileName, firstNameToEdit, SurnameToEdit, AgeToEdit, genderToEdit, dayOfAppointmentToEdit, newFirstName, newSecondName, newAge, newGender, newCondition, newDayOfAppointment, newHourofAppointment, newMinuteOfAppointment, newDoctorsFirstName, newDoctorsSurname, ViewDoctor);
                                    ViewDoctor = false;
                                    EditBooking(DoctorFileName, firstNameToEdit, SurnameToEdit, AgeToEdit, genderToEdit, dayOfAppointmentToEdit, newFirstName, newSecondName, newAge, newGender, newCondition, newDayOfAppointment, newHourofAppointment, newMinuteOfAppointment, newDoctorsFirstName, newDoctorsSurname, ViewDoctor);
                                        System.out.println("Thank you. It has been edited.");
                                        break;

                                } else{System.out.println("Please enter a valid hour/minute and or that time has been taken");
                                        System.out.println();
                                    }
                                
                                } catch(InputMismatchException e) {}
                             }

                        } else{

                                System.out.println("Please enter the correct infomation");

                            }
                         }
                        break;
                        

                case 5:
                        System.out.println();
                        System.out.println("Leaving now");
                        booking = false;
                        break;

                default:

            }



            } catch(InputMismatchException e) {}

        }

        scanner.close();
        
    }

    public static void main(String[] args) {

        bookings();

    }
    
}
