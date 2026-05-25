import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public class Allat {
        public String faj;
        public int magas;
        public int suly;
        public int kor;

        public Allat(String sor) {
            String[] s = sor.split(";");
            faj = s[0];
            magas = Integer.parseInt(s[1]);
            suly = Integer.parseInt(s[2]);
            kor = Integer.parseInt(s[3]);
        }
    }

    private ArrayList<Allat> allatok = new ArrayList<>();

    public Main() {
        betolt("allatok.csv");
        //--------------------------------------------------------
        System.out.printf("0) Összesen %d féle állatfajta adata beolvasva\n", allatok.size());

        //--------------------------------------------------------
        Allat legmagasabb = allatok.get(0);
        for (Allat a : allatok) {
            if (a.magas > legmagasabb.magas) {
                legmagasabb = a;
            }
        }
        System.out.printf("1) A legmagasabb állatfajta: %s, %d\n", legmagasabb.faj, legmagasabb.magas);

        //--------------------------------------------------------

        int db = 0, kor = 0;
        for (Allat a : allatok) {
            if (a.suly > 20) {
                db++;
                kor += a.kor;
            }
        }
        System.out.printf("2) A húsz kilónál nehezebb fajták átlagéletkora: %.2f év\n", (float)kor/db);

        //--------------------------------------------------------

        int suly = 0;
        for (Allat a : allatok) {
            suly += a.suly;
        }
        float atlagSuly = (float)suly/allatok.size();

        Allat legkozelebb = allatok.get(0);
        for (Allat a : allatok) {
            if (Math.abs(a.suly - atlagSuly) < Math.abs(legkozelebb.suly - atlagSuly)) {
                legkozelebb = a;
            }
        }

        System.out.printf(" 3) Az átlagsúlyhoz (%.0fkg) legközelebbi fajta: %s (%dkg)\n", atlagSuly, legkozelebb.faj, legkozelebb.suly);

        //--------------------------------------------------------
        System.out.printf("4) Kettős betű van a fajta nevében:\n");
        for (Allat a : allatok) {
            boolean van = false;
            for (int i = 0; i < a.faj.length()-1; i++) {
                if (a.faj.charAt(i) == a.faj.charAt(i+1)) {
                    van = true;
                }
            }
            if (van) {
                System.out.printf("     * %s\n", a.faj);
            }
        }

        //--------------------------------------------------------
        System.out.printf("5) Magasság kategórák (50cm):\n");
        TreeMap<Integer, Integer> stat = new TreeMap<>();
        for (Allat a : allatok) {
            int kat = a.magas / 50;
            if (!stat.containsKey(kat)) {
                stat.put(kat, 1);
            } else {
                stat.put(kat, stat.get(kat) + 1);
            }
        }
        for (int kat : stat.keySet()) {
            System.out.printf("    * %03d-%03dcm: %d darab\n", kat*50, kat*50+49, stat.get(kat));
        }

        //--------------------------------------------------------
        ArrayList<Integer> kats = new ArrayList<>();
        for (int kat : stat.keySet()) {
            kats.add(kat);
        }
        int v = (int)(Math.random()*kats.size());
        int kat = kats.get(v);
        System.out.printf("6) Ebből egy véletlen kategóriába (%d-%dcm) eső állatok:\n    * ", kat*50, kat*50+49);
        String vesszo = "";
        for (Allat a : allatok) {
            if (a.magas / 50 == kat) {
                System.out.printf(vesszo + "%s", a.faj);
                vesszo = ", ";
            }
        }
    }

    private void betolt(String fajlnev) {
        Scanner be = null;
        try {
            be = new Scanner(new File(fajlnev), "utf-8");
            be.nextLine();
            while (be.hasNextLine()) {
                allatok.add(new Allat(be.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (be != null) {
                be.close();
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}