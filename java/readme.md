````
  public class Adat {
      public String s1;
      public int i1;

      public Allat(String sor) {
          String[] s = sor.split(";");
          s1 = s[0];
          i1 = Integer.parseInt(s[1]);
      }
  }

  private ArrayList<Adat> adatok = new ArrayList<>();

  public Main() {
      betolt("adatok.csv");
      System.out.printf("0) Összesen %d adat beolvasva\n", adatok.size());
  }

  private void betolt(String fajlnev) {
      Scanner be = null;
      try {
          be = new Scanner(new File(fajlnev), "utf-8");
          be.nextLine();
          while (be.hasNextLine()) {
              adatok.add(new Adat(be.nextLine()));
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
````
