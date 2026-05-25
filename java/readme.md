````
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
      System.out.printf("0) Összesen %d féle állatfajta adata beolvasva\n", allatok.size());
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
````
