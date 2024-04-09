import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Group {
    List<Good> goods = new ArrayList<>();
    private String name;
    private String description;

    private String file;
    public Group (String name, String description){
        this.name = name;
        this.description = description;
        this.file = "src\\" + name + ".txt";
    }
    public void readGoods(){
        String txt = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready())
            {
                 txt += reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] good = txt.split(";");
        for (String s : good){
            String[] g = s.split(",");
            for (int i = 0; i < g.length; i++) g[i] = g[i].trim();

            goods.add(new Good(g[0], g[1], g[2], Integer.parseInt(g[3]), Double.parseDouble(g[4])));

        }
    }
    public void writeGoods(){
        try {
            java.io.FileWriter writer = new java.io.FileWriter(file);
            for (Good g : goods){
                writer.write(g.getName() + ", " + g.getDescription() + ", " + g.getProducer() + ", " + g.getAmount() + ", " + g.getPrice() + ";\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Good> getGoodsList() {
        return goods;
    }
    public String toString(){
        if(name == null && description == null) return "Група не визначена";
        if(name == null) return "Група не визначена" + ": " + description;
        if(description == null) return name + ": " + "Опис не визначено";
        return name + ": " + description;
    }
    public void addGood(Good good) {
        goods.add(good);
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
