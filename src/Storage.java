import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * A class to manage storage operations for groups of goods.
 */
public class Storage {
    ArrayList<Group> groupList = new ArrayList<>();
    /**
     * Constructs a Storage object.
     */
    public Storage() {
    }
    /**
     * Deletes a group and its associated data.
     *
     * @param group The group to delete.
     */
    public void deleteGroup(Group group) {
        groupList.remove(group);
        group.deleteGoodFile();
    }
    /**
     * Reads groups from a file and adds them to the group list.
     *
     * @param file The file to read groups from.
     */
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
    /**
     * Retrieves a group by its name.
     *
     * @param name The name of the group to retrieve.
     * @return The group with the specified name, or null if not found.
     */
    public Group groupByName(String name){
        for (Group g : groupList){
            if (g.getName().equals(name)) return g;
        }
        return null;
    }
    /**
     * Writes groups and their descriptions to a file.
     *
     * @param file The file to write groups to.
     */
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
    /**
     * Gets the list of groups.
     *
     * @return The list of groups.
     */
    public List<Group> getGroupList() {
        return groupList;
    }
}
