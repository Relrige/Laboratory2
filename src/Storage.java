import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    ArrayList<Group> groupList = new ArrayList<>();
    public Storage() {
    }

    public void deleteGroup(Group group) {
        groupList.remove(group);
        group.deleteGoodFile();
    }
    public void readGroups(String file){
        StringBuilder txt = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready())
            {
                txt.append(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] group = txt.toString().split(";");
        for (String s : group){
            String[] g = s.split(",");
            if(!groupList.contains(groupByName(g[0]))) groupList.add(new Group(g[0], g[1]));
        }
    }
    public Group groupByName(String name){
        for (Group g : groupList){
            if (g.getName().equals(name)) return g;
        }
        return null;
    }

    void writeGroups(String file){
        try {
            java.io.FileWriter writer = new java.io.FileWriter(file);
            for (Group g : groupList){
                writer.write(g.getName() + ", " + g.getDescription() + ";\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Group> getGroupList() {
        return groupList;
    }
}
