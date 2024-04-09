public class Tester {
    public static void main(String[] args) {
        Storage storage = new Storage();
        storage.readGroups("src\\groups.txt");
        System.out.println(storage.getGroupList());
        storage.getGroupList().get(0).readGoods();
        System.out.println(storage.getGroupList().get(0).getGoodsList());
        storage.getGroupList().get(1).addGood(new Good("Шоколад", "Молочний шоколад", "Roshen", 100, 20.5));
        storage.getGroupList().get(1).addGood(new Good("Морозиво", null, "Ласунка", 100));
        storage.getGroupList().get(1).writeGoods();

    }
}
