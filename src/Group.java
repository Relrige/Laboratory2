import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Group {
    List<Good> goods = new ArrayList<>();
    private String name;
    private String description;

    private String file;
    public Group (String name, String description){
        this.name = name.trim();
        this.description = description.trim();
        this.file = "FIles\\" + name + ".txt";
        if (!Files.exists(Paths.get(file))) {
            try {
                Files.createFile(Paths.get(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void readGoods(){
        String txt = "";
        File file = new File(this.file);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready())
            {
                txt += reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!txt.isEmpty()) {
            String[] good = txt.split(";");
            for (String s : good) {
                String[] g = s.split(",");
                for (int i = 0; i < g.length; i++) g[i] = g[i].trim();

                if(!goods.contains(goodByName(g[0]))) goods.add(new Good(g[0], g[1], g[2], Integer.parseInt(g[3]), Double.parseDouble(g[4])));

            }
        }
    }
    public Good goodByName(String name){
        for (Good g : goods){
            if (g.getName().equals(name)) return g;
        }
        return null;
    }
    public void deleteGood(Good good) {
        goods.remove(good);
    }
    public void deleteGoodFile(){
        try {
            Files.delete(Paths.get(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        return name + " (" + description + ")";
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
    public void setName(String name) {
        this.name = name;
        try {
            Files.delete(Paths.get(file));
            file = "FIles\\" + name + ".txt";
            FileWriter writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
