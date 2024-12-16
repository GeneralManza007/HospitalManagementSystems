import java.util.*;

public class Stock{

    Scanner scanner = new Scanner(System.in);

    private HashMap<String, Integer> medicineStock;
    private HashMap<String, Integer> chemcialStock;

    public Stock(Map<String, Integer> initialMedicineStock, Map<String, Integer> initalChemicalStock) {
    
        this.medicineStock = new HashMap<>(initialMedicineStock);
        this.chemcialStock = new HashMap<>(initalChemicalStock);


    }

    public void addMedicine(String name, int quantity ) {

        if(medicineStock.containsKey(name)) {

                int currentQuantity = medicineStock.get(name);
                medicineStock.put(name, currentQuantity + quantity);
                    
                }else{

                        medicineStock.put(name,quantity);


                }

        }

     public void addChemical(String name, int quantity ) {

        if(chemcialStock.containsKey(name)) {

                int currentQuantity = chemcialStock.get(name);
                chemcialStock.put(name, currentQuantity + quantity);
                    
                }else{

                        chemcialStock.put(name,quantity);


                }

        }

        public void viewMedicines() {

            System.out.println();
            System.out.println("Our Medicine/Equipment Stock");
            System.out.println();

            for(String name : medicineStock.keySet()) {

                int quantity = medicineStock.get(name);
                System.out.println(name + ": " + quantity);
                System.out.println();

            }

        }

        public void viewChemicals() {

            System.out.println();
            System.out.println("Our Chemical Stock");
            System.out.println();

            for(String name : chemcialStock.keySet()) {

                int quantity = chemcialStock.get(name);
                System.out.println(name + ": " + quantity);
                System.out.println();

            }


        }

        public static Stock interactWithStock() {

            Random rand = new Random();

            Map<String, Integer> initialMedicineStock = new HashMap<>();

            initialMedicineStock.put("Ankle & Toe Pressure kit", rand.nextInt(500,1000));
            initialMedicineStock.put("Stethoscopes", rand.nextInt(10,100));
            initialMedicineStock.put("UltraSonic Scalars", rand.nextInt(1,10));
            initialMedicineStock.put("Pyon 2 Non-Optic PB-200", rand.nextInt(1,20));
            initialMedicineStock.put("Suchuco Reusable NSV Sharp Haemostat",rand.nextInt(1,1000));
            initialMedicineStock.put("Lister Sinus Forceps", rand.nextInt(200,1000));
            initialMedicineStock.put("ECG Machine", rand.nextInt(1,20));


            Map<String, Integer> initalChemicalStock = new HashMap<>();

            initalChemicalStock.put("Hydrogen Peroxide cans ", rand.nextInt(1,500));
            initalChemicalStock.put("Lugols Iodine Bottles ", rand.nextInt(1,200));
            initalChemicalStock.put("Epipens",rand.nextInt(1,10000));
            initalChemicalStock.put("Amoxilin tablets",rand.nextInt(1,10000));
            initalChemicalStock.put("Atorvastatin tablets", rand.nextInt(1,5000));
            initalChemicalStock.put("Inhalers", rand.nextInt(1,3000));
            initalChemicalStock.put("Ranitidine tablets", rand.nextInt(1,5000));

            return new Stock(initialMedicineStock, initalChemicalStock);


        }

        public void StockMedicineOption(){

            boolean StockMedicineOpt = true;

            while(StockMedicineOpt) {

                    System.out.println("Select:");
                    System.out.println();

                    System.out.println("1. View Medicine Stock");
                    System.out.println("2. Add to Medicine Stock");
                    System.out.println("3. Go back"); 
                    
                    int chosen = scanner.nextInt();
                    //scanner.nextLine();

                    switch(chosen) {

                        case 1:
                                viewMedicines();
                                break;


                        case 2:
                                System.out.println("Please enter the following: ");
                                System.out.println();

                                System.out.println("Name: ");
                                String NameOfMedicine = scanner.nextLine();
                                System.out.println();

                                System.out.println("Quantity: ");
                                int quantity = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println();

                                addMedicine(NameOfMedicine, quantity);

                                System.out.println("Thank you. You may go and view it");
                                System.out.println();
                                break;
                                
                                




                        case 3:
                                System.out.println("Leaving now!");
                                StockMedicineOpt = false;
                                break;



                        default:
                                System.out.println("Please enter a suitable choice");
                                break;




                    }


            }


            

        }

        public void StockChemicalOption(){

            boolean StockChemicalOpt = true;

            while(StockChemicalOpt) {

                    System.out.println("Choose: ");
                    System.out.println();
                    System.out.println("1. View Chemical Stock");
                    System.out.println("2. Add to Chemical Stock");
                    System.out.println("3. Go back");

                    int Chose = scanner.nextInt();
                    scanner.nextLine();

                    switch(Chose) {

                        case 1:
                                viewChemicals();
                                break;



                        case 2:
                                System.out.println("Please enter the following: ");
                                System.out.println();

                                System.out.println("Name:");
                                String NameOfChemical = scanner.nextLine();
                                System.out.println();

                                System.out.println("Quantity:");
                                int quantity = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println();

                                addChemical(NameOfChemical, quantity);

                                System.out.println("Thank you. You may go and view it ");
                                System.out.println();
                                break;




                        case 3:
                                 System.out.println("Leaving now!");
                                 System.exit(0);
                                 //StockChemicalOpt = false;
                                 break;
                                


                        default:



                    }


            }



        }

        public void StockMainOptions(String Name) {
                
            interactWithStock();

            boolean startup = true;

            while (startup) {

                System.out.println("Please chose an option " + Name + ":");
                System.out.println();
                System.out.println("1. Select Medical Stock");
                System.out.println("2. Select Chemical Stock");
                System.out.println("3. Go back");

                int Choice = scanner.nextInt();
                scanner.nextLine(); 

                switch(Choice) {

                        case 1:
                                StockMedicineOption();
                                break;


                        case 2:
                                StockChemicalOption();
                                break;

                        case 3:
                                System.out.println("Leaving now!");
                                startup = false;
                                break;


                        default:
                                 System.out.println("Please enter a suitable choice: ");

                }




            }

            scanner.close();
        }

        public static void main(String[] args) {
                
                Stock stock = Stock.interactWithStock();

                String Name = "Hamza";

                stock.StockMainOptions(Name);
                

        }

    }

    
