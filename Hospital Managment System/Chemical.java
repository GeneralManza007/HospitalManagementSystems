 import java.util.*;

class ChemicalInfo{

    private int Total;
    private List<String> keys;

    public ChemicalInfo(int Total, List<String> keys) {
        this.Total = Total;
        this.keys = keys;
    }

    public int getTotal() {return Total;}

    public List<String> getKeys() {return keys;}
}

public class Chemical {

    public static <V> List<Map.Entry<String, V>> getRandomChemicals(HashMap<String, V> map, int numberOfEntries) {

        List<Map.Entry<String,V>> entryList = new ArrayList<>(map.entrySet());
        List<Map.Entry<String,V>> randomChemical = new ArrayList<>();

            if (entryList.isEmpty() || numberOfEntries <=0 ) {

                    return randomChemical;
            }
        
        Random random = new Random();

        for(int i = 0; i < numberOfEntries; i++) {

            int RandomIndex = random.nextInt(entryList.size());
            randomChemical.add(entryList.remove(RandomIndex));

        }

        return randomChemical;
}

public ChemicalInfo Chemicals(){

    HashMap<String, Integer> Chemicals = new HashMap<String, Integer>();

    Chemicals.put("100 Hydrogen Peroxide cans ", 1200);
    Chemicals.put("100 Lugols Iodine Bottles ", 695);
    Chemicals.put("100 Epipens",23000);
    Chemicals.put("100 Amoxilin tablets",2000);
    Chemicals.put("100 Atorvastatin tablets", 40000);
    Chemicals.put("100 Inhalers", 10000);
    Chemicals.put("100 Ranitidine tablets", 2000);
    
    int numberOfEntriesToPick = 5;
    List<Map.Entry<String, Integer>> randomEntries = getRandomChemicals(Chemicals, numberOfEntriesToPick);
    
    int Total = 0;
    List<String> keys = new ArrayList<>();

    for(Map.Entry<String, Integer> entry : randomEntries) {
            
            Total += entry.getValue();
            keys.add(entry.getKey());
    }

    return new ChemicalInfo(Total, keys);
}
    
}
