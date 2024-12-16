import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

public class Financial {

    private int Money_in;
    private int Money_out;
    private int Starting_Balance;

    Random rand = new Random();

    public Financial(int Money_in, int Money_out) {

            this.Money_in = Money_in;
            this.Money_out = Money_out;

    }

    public int Starting_Balance(){

        Starting_Balance = rand.nextInt(2500000, 5000000);

        return Starting_Balance;

    }

    public int getMoney_in(){return this.Money_in;}

    public int getMoney_out(){return this.Money_out;}

    public int getStarting_Balnce(){return Starting_Balance;}

    public int Medical_Supplies(int Money_out) {
        
            int Medical_out = Starting_Balance() - Money_out;
        
        return Medical_out;

    }

    public int Chemical_Purchasing(int Money_out) {

        int Chemical_out = Starting_Balance() - Money_out;

        return Chemical_out;

    }

    public int DoctorSalary() {
        
        int totalSalary = 0;

        try{
        
            String FileName = "Doctor.txt";

            File file = new File(FileName);
            Scanner reader = new Scanner(file);

            while(reader.hasNext()) {

                String Doctor = reader.nextLine();

                String [] Doctor_Salaries = Doctor.split("; ");

                int Doctor_Salary_Int = Integer.parseInt(Doctor_Salaries[3]);

                totalSalary += Doctor_Salary_Int;

            }

            reader.close();
    
        } catch(FileNotFoundException e) {}

        return totalSalary;


    }

    public int ReceptionistSalary(String filename){

        int totalSalary = 0;

        try{
             if(!Receptionist.CheckReceptionistExists(filename)) {

                Receptionist.CreateReceptionist();
             }

             if(Receptionist.ReceptionistIsEmpty(filename)) {

                return 0;
             }

            File file = new File(filename);

            Scanner reader = new Scanner(file);

            while(reader.hasNext()) {

                String Receptionist = reader.nextLine();

                String [] ReceptionistArr = Receptionist.split("; ");

                int ReceptionistSalary = Integer.parseInt(ReceptionistArr[4]);

                totalSalary += ReceptionistSalary;

            }

            reader.close();
    
        } catch(FileNotFoundException e) {}

        return totalSalary;

    }

    public int AdminSalary(String filename){

        int totalSalary = 0;

        try{

            File file = new File(filename);

            Scanner reader = new Scanner(file);

            while(reader.hasNext()) {

                String Admin = reader.nextLine();

                String [] AdminArr = Admin.split("; ");

                int AdminSalary = Integer.parseInt(AdminArr[6]);

                totalSalary += AdminSalary;

            }

            reader.close();
    
        } catch(FileNotFoundException e) {}

        return totalSalary;

    }

    public int InternShips(){

            return 0;

    }

    public int GovermentPay() {

       int GovernPay = rand.nextInt(500000,1000000);

        return GovernPay;
    }

    public int BalanceAfterGovenmentPay() {

        int BalanceAfterGovenmentPay = Starting_Balance() + GovermentPay();

        return BalanceAfterGovenmentPay;
    }


    public int Invested() {

        int InvestedAmount = rand.nextInt(250000, 1000000);

            return InvestedAmount;
    }

    public int BalanceAfterInvested() {

        int BalanceAfterInvested = Starting_Balance() + Invested();

        return BalanceAfterInvested;
    }

}
