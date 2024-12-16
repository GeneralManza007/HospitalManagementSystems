import java.util.*;

class OperatingInfo{

    private int Total;
    private List<String> keys;

    public OperatingInfo(int Total, List<String> keys) {
        this.Total = Total;
        this.keys = keys;
    }

    public int getTotal() {return Total;}

    public List<String> getKeys() {return keys;}
}

public class Operating {

    public static <V> List<Map.Entry<String, V>> getRandomOperatingCost(HashMap<String, V> map, int numberOfEntries) {

        List<Map.Entry<String,V>> entryList = new ArrayList<>(map.entrySet());
        List<Map.Entry<String,V>> randomOperatingCost = new ArrayList<>();

            if (entryList.isEmpty() || numberOfEntries <=0 ) {

                    return randomOperatingCost;
            }
        
        Random random = new Random();

        for(int i = 0; i < numberOfEntries; i++) {

            int RandomIndex = random.nextInt(entryList.size());
            randomOperatingCost.add(entryList.remove(RandomIndex));

        }

        return randomOperatingCost;
}

public OperatingInfo Operatings(){

    HashMap<String, Integer> Chemicals = new HashMap<String, Integer>();

    Chemicals.put("Operating the MRI Machine", 12000);
    Chemicals.put("Operating the CT Machine", 9000);
    Chemicals.put("Cleaning the hospital",12000);
    Chemicals.put("Operating the Robot Neuro Machine",15000);
    Chemicals.put("Operating the Mammography Machine", 40000);
    Chemicals.put("Servicing the air conditioners", 10000);
    Chemicals.put("Cleaning and servicing the OR rooms", 5000);
    
    int numberOfEntriesToPick = 5;
    List<Map.Entry<String, Integer>> randomEntries = getRandomOperatingCost(Chemicals, numberOfEntriesToPick);
    
    int Total = 0;
    List<String> keys = new ArrayList<>();

    for(Map.Entry<String, Integer> entry : randomEntries) {
            
            Total += entry.getValue();
            keys.add(entry.getKey());
    }

    return new OperatingInfo(Total, keys);
}
    
}