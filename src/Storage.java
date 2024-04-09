import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    List<Group> groupList = new ArrayList<>();
    public Storage() {
    }

    public void deleteGroup(Group group) {
        groupList.remove(group);
    }
    public void readGroups(String file){
        StringBuilder txt = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready())
            {
                 txt.append(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] group = txt.toString().split(";");
        for (String s : group){
            String[] g = s.split(",");
            if(g.length == 1) {
                groupList.add(new Group(g[0], "Опис не визначено"));
            } else {
                groupList.add(new Group(g[0], g[1]));
            }
        }
    }
    public List<Group> getGroupList() {
        return groupList;
    }
}
