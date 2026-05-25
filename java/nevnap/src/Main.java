import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Nevnap> nevnapok = new ArrayList<>();
        try {
            Scanner beolvasas = new Scanner(new File("src/nevnap.csv"));
            while(beolvasas.hasNextLine()){
                String[] sor = beolvasas.nextLine().split(";");
                String nev = sor[0];
                String[] nevnap = new String[sor.length-1];
                for(int i = 0 ; i < nevnap.length; i++){
                    nevnap[i] = sor[i+1];
                }

                Nevnap np = new Nevnap(nevnap, nev);
                nevnapok.add(np);
            }
            beolvasas.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }

        System.out.printf("0) Összesen 2861 név beolvasva.", nevnapok.size());
        System.out.printf("\n\tA tizedik név %s, akinek %d névnapja van.", nevnapok.get(9).nev, nevnapok.get(9).nevnap.length);

        List<Nevnap> janos = nevnapok.stream().filter(obj->obj.nev.equals("János")).toList();
        System.out.printf("\n1) János névnapjai: %s", String.join(", ", janos.get(0).nevnap));

        List<String> apriliselseje = nevnapok.stream().filter(obj-> String.join(",", obj.nevnap).contains("0401")).map(obj->obj.nev).toList();
        System.out.print("\n2) Április elsején van névnapja: ");
        for(String nev : apriliselseje){
            System.out.print(nev + " ");
        }

        int max = 0;
        int legtobbNevnap = nevnapok.stream().mapToInt(obj->obj.nevnap.length).max().getAsInt();
        List<Nevnap> legtobbNevnaposok = nevnapok.stream().filter(obj->obj.nevnap.length==legtobbNevnap).toList();
        System.out.printf("\n3) Legtöbb névnapja (%d) %s nevűeknek van!", legtobbNevnaposok.get(0).nevnap.length, legtobbNevnaposok.get(0).nev);

        System.out.print("\n\tDe ugyanennyi névnapja van még: ");
        for(Nevnap nev : legtobbNevnaposok.subList(1, legtobbNevnaposok.size())){
            System.out.print(nev.nev + " ");
        }

        int sum = nevnapok.stream().mapToInt(obj->obj.nevnap.length).sum();
        System.out.printf("\n4) Összesen %d nap van a nevekhez rendelve.\n", sum);

        String[] honapok = {"Január", "Február", "Március", "Április", "Május", "Június", "Július", "Augusztus", "Szeptember", "Október", "November", "December"};
        TreeMap<String, Integer> honapDb = new TreeMap<>();
        for(Nevnap nevnap : nevnapok){
            for(int i = 0 ; i < nevnap.nevnap.length; i++){
                if(!honapDb.containsKey(nevnap.nevnap[i].substring(0,2))){
                    honapDb.put(nevnap.nevnap[i].substring(0,2), 1);
                }else{
                    honapDb.put(nevnap.nevnap[i].substring(0,2), honapDb.get(nevnap.nevnap[i].substring(0,2))+1);
                }
            }
        }
        for(String ho : honapDb.keySet()){
            System.out.printf("%s : %d\n", honapok[Integer.parseInt(ho)-1], honapDb.get(ho));
        }
        


    }
}