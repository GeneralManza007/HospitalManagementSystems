import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TestingFuncs {

    public static int GetValueOfTime(String filename, String Firstname, String LastName, int Age, String gender, String dayOfAppointment, int Position) {

            int ReturnValue = 0;

            try{
                File file = new File(filename);

                    try(Scanner reader = new Scanner(file)) {

                        while(reader.hasNext()) {

                            String booking = reader.nextLine();
                            String [] bookingsArr = booking.split("; ");

                              System.out.println("Debug: " + Arrays.toString(bookingsArr));
                            
                            if(bookingsArr[0].equalsIgnoreCase(Firstname) && bookingsArr[1].equalsIgnoreCase(LastName) && Integer.parseInt(bookingsArr[2]) == Age && bookingsArr[3].equalsIgnoreCase(gender) && bookingsArr[5].equalsIgnoreCase(dayOfAppointment)) {

                                    ReturnValue = Integer.parseInt(bookingsArr[Position]);

                                    return ReturnValue;

                            }

                        }

                    } 

            } catch(FileNotFoundException e) {}

            return ReturnValue;
        }


    public static void main(String[] args) {
        
            String FileName = "Bookings.txt";
            String FirstName = "Hamza";
            String Surname = "Malik";
            String Gender = "Male";
            int Age = 18;
            String DayOfAppointment = "14/01/2024";


            int Value = GetValueOfTime(FileName, FirstName, Surname, Age, Gender, DayOfAppointment, 6);
            System.out.println(Value);
    }
    
}
