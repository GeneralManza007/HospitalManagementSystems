import java.util.*;

class EquipmentInfo{

    private int Total;
    private List<String> keys;

    public EquipmentInfo(int Total, List<String> keys) {
        this.Total = Total;
        this.keys = keys;
    }

    public int getTotal() {return Total;}

    public List<String> getKeys() {return keys;}
}

public class Medical {

    public static <V> List<Map.Entry<String, V>> getRandomEquipments(HashMap<String, V> map, int numberOfEntries) {

            List<Map.Entry<String,V>> entryList = new ArrayList<>(map.entrySet());
            List<Map.Entry<String,V>> randomEquipment = new ArrayList<>();

                if (entryList.isEmpty() || numberOfEntries <=0 ) {

                        return randomEquipment;
                }
            
            Random random = new Random();

            for(int i = 0; i < numberOfEntries; i++) {

                int RandomIndex = random.nextInt(entryList.size());
                randomEquipment.add(entryList.remove(RandomIndex));

            }

            return randomEquipment;
    }

    public EquipmentInfo Equipment(){

        HashMap<String, Integer> Equipments = new HashMap<String, Integer>();

        Equipments.put("Ankle & Toe Pressure kit", 2235);
        Equipments.put("Stethoscope", 471);
        Equipments.put("UltraSonic Scalar", 1004);
        Equipments.put("Pyon 2 Non-Optic PB-200", 1011);
        Equipments.put("Suchuco Reusable NSV Sharp Haemostat", 155);
        Equipments.put("Lister Sinus Forceps",5);
        Equipments.put("ECG Machine",2197);
        
        int numberOfEntriesToPick = 5;
        List<Map.Entry<String, Integer>> randomEntries = getRandomEquipments(Equipments, numberOfEntriesToPick);
        
        int Total = 0;
        List<String> keys = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : randomEntries) {
                
                Total += entry.getValue();
                keys.add(entry.getKey());
        }
        return new EquipmentInfo(Total, keys);
    }

    public static void main(String[] args) {
    }



    
}
