import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        File file = new File("Files\\groups.txt");
        String txt = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                txt += reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Group group : storage.groupList) {
            group.readGoods();
        }

        if (!txt.isEmpty()) storage.readGroups("Files\\groups.txt");
        StandartFrame menu = new StandartFrame("Tester");
        JButton groups = new CoolButton("Групи товарів");
        JButton goods = new CoolButton("Товари");
        JButton stats = new CoolButton("Статистика");
        JButton[] b = {groups, goods, stats};
        menu.setInfoPanel("Управління підприємством");
        menu.setButtonPanel(b);
        menu.setVisible(true);
        groups.addActionListener(e -> {
            menu.setVisible(false);
            StandartFrame groupsFrame = new StandartFrame("Групи товарів");
            JButton addGroup = new CoolButton("Додати групу");
            JButton editGroup = new CoolButton("Редагувати групу");
            JButton deleteGroup = new CoolButton("Видалити групу");
            JButton back = new CoolButton("Назад");
            JButton[] buttons1 = {addGroup, editGroup, deleteGroup, back};
            groupsFrame.setInfoPanel("Групи товарів");
            groupsFrame.setButtonPanel(buttons1);
            back.addActionListener(e1 -> {
                groupsFrame.setVisible(false);
                menu.setVisible(true);
            });
            addGroup.addActionListener(e12 -> {
                groupsFrame.setVisible(false);
                StandartFrame addGroupFrame = new StandartFrame("Додати групу");
                JTextField groupNameField = new JTextField();
                JTextField groupDescriptionField = new JTextField();
                JLabel groupName = new JLabel("Назва групи");
                JLabel groupDescription = new JLabel("Опис групи");
                JTextField[] textFields = {groupNameField, groupDescriptionField};
                JLabel[] labels = {groupName, groupDescription};
                addGroupFrame.setTextInputPanel(textFields, labels);
                JButton addGroupButton = new CoolButton("Додати групу");
                JButton back2 = new CoolButton("Назад");
                JButton[] buttons2 = {addGroupButton, back2};
                addGroupFrame.setButtonPanel(buttons2);
                addGroupFrame.setVisible(true);


                back2.addActionListener(e2 -> {
                    addGroupFrame.setVisible(false);
                    groupsFrame.setVisible(true);
                });
                addGroupButton.addActionListener(e3 -> {
                    addGroupFrame.setVisible(false);
                    groupsFrame.setVisible(true);
                    if (groupNameField.getText().isEmpty() || groupDescriptionField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(addGroupFrame, "Заповніть усі поля");
                        return;
                    }
                    for (Group group : storage.groupList) {
                        if (group.getName().equalsIgnoreCase(groupNameField.getText())) {
                            JOptionPane.showMessageDialog(addGroupFrame, "Група з такою назвою вже існує");
                            return;
                        }
                    }

                    Group group = new Group(groupNameField.getText(), groupDescriptionField.getText());
                    storage.groupList.add(group);
                    storage.writeGroups("Files\\groups.txt");

                    System.out.println(storage.groupList);

                });
            });
            editGroup.addActionListener(e4 -> {
                groupsFrame.setVisible(false);
                StandartFrame editGroupFrame = new StandartFrame("Редагувати групу");
                String[] groupNames = new String[storage.groupList.size()];
                for (int i = 0; i < storage.groupList.size(); i++) {
                    groupNames[i] = storage.groupList.get(i).getName();
                }
                editGroupFrame.setSearchPanel(Arrays.asList(groupNames));
                JButton editGroupNameButton = new CoolButton("Редагувати назву групи");
                JButton editGroupDescriptionButton = new CoolButton("Редагувати опис групи");
                JButton back3 = new CoolButton("Назад");
                JButton[] buttons3 = {editGroupNameButton, editGroupDescriptionButton, back3};
                editGroupFrame.setButtonPanel(buttons3);
                editGroupFrame.setVisible(true);

                back3.addActionListener(e42 -> {
                    groupsFrame.setVisible(true);
                    editGroupFrame.setVisible(false);
                });
                editGroupNameButton.addActionListener(e5 -> {
                    editGroupFrame.setVisible(false);
                    StandartFrame editGroupNameFrame = new StandartFrame("Редагувати назву групи");
                    JTextField groupNameField = new JTextField();
                    editGroupNameFrame.setTextInputPanel(new JTextField[]{groupNameField}, new JLabel[]{new JLabel("Нова назва групи")});
                    JButton editGroupNameButton1 = new CoolButton("Редагувати назву групи");
                    JButton back4 = new CoolButton("Назад");
                    editGroupNameFrame.setButtonPanel(new JButton[]{editGroupNameButton1, back4});
                    editGroupNameFrame.setVisible(true);
                    editGroupNameButton1.addActionListener(e6 -> {
                        editGroupNameFrame.setVisible(false);
                        groupsFrame.setVisible(true);
                        if (groupNameField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(editGroupNameFrame, "Введіть назву групи");
                            return;
                        }
                        for (Group group : storage.groupList) {
                            if (group.getName().equalsIgnoreCase(groupNameField.getText())) {
                                JOptionPane.showMessageDialog(editGroupNameFrame, "Група з такою назвою вже існує");
                                return;
                            }
                        }
                        storage.groupList.get(editGroupFrame.list.getSelectedIndex()).setName(groupNameField.getText());
                        storage.writeGroups("Files\\groups.txt");
                    });
                    back4.addActionListener(e7 -> {
                        editGroupNameFrame.setVisible(false);
                        editGroupFrame.setVisible(true);
                    });
                });
                editGroupDescriptionButton.addActionListener(e7 -> {
                    editGroupFrame.setVisible(false);
                    StandartFrame editGroupDescriptionFrame = new StandartFrame("Редагувати опис групи");
                    JTextField groupDescriptionField = new JTextField();
                    editGroupDescriptionFrame.setTextInputPanel(new JTextField[]{groupDescriptionField}, new JLabel[]{new JLabel("Новий опис групи")});
                    JButton editGroupDescriptionButton1 = new CoolButton("Редагувати опис групи");
                    JButton back4 = new CoolButton("Назад");
                    editGroupDescriptionFrame.setButtonPanel(new JButton[]{editGroupDescriptionButton1, back4});
                    editGroupDescriptionFrame.setVisible(true);
                    back4.addActionListener(e8 -> {
                        editGroupDescriptionFrame.setVisible(false);
                        editGroupFrame.setVisible(true);
                    });
                    editGroupDescriptionButton1.addActionListener(e8 -> {
                        editGroupDescriptionFrame.setVisible(false);
                        groupsFrame.setVisible(true);
                        storage.groupList.get(editGroupFrame.list.getSelectedIndex()).setDescription(groupDescriptionField.getText());
                        storage.writeGroups("Files\\groups.txt");
                    });
                });

            });
            deleteGroup.addActionListener(e9 -> {
                groupsFrame.setVisible(false);
                StandartFrame deleteGroupFrame = new StandartFrame("Видалити групу");
                String[] groupNames = new String[storage.groupList.size()];
                for (int i = 0; i < storage.groupList.size(); i++) {
                    groupNames[i] = storage.groupList.get(i).getName();
                }
                deleteGroupFrame.setSearchPanel(Arrays.asList(groupNames));
                JButton deleteGroupButton = new CoolButton("Видалити групу");
                JButton back4 = new CoolButton("Назад");
                JButton[] buttons4 = {deleteGroupButton, back4};
                deleteGroupFrame.setButtonPanel(buttons4);
                deleteGroupFrame.setVisible(true);
                back4.addActionListener(e10 -> {
                    groupsFrame.setVisible(true);
                    deleteGroupFrame.setVisible(false);
                });
                deleteGroupButton.addActionListener(e11 -> {
                    deleteGroupFrame.setVisible(false);
                    groupsFrame.setVisible(true);
                    storage.deleteGroup(storage.groupList.get(deleteGroupFrame.list.getSelectedIndex()));
                    storage.writeGroups("Files\\groups.txt");
                });
            });
        });
        goods.addActionListener(e1 -> {
            for (Group group : storage.groupList) {
                group.readGoods();
            }
            menu.setVisible(false);
            StandartFrame goodsFrame = new StandartFrame("Товари");
            JButton addGood = new CoolButton("Додати товар");
            JButton editGood = new CoolButton("Редагувати товар");
            JButton deleteGood = new CoolButton("Видалити товар");
            JButton back1 = new CoolButton("Назад");
            JButton[] buttons = {addGood, editGood, deleteGood, back1};
            goodsFrame.setInfoPanel("Товари");
            goodsFrame.setButtonPanel(buttons);
            goodsFrame.setVisible(true);
            back1.addActionListener(e2 -> {
                goodsFrame.setVisible(false);
                menu.setVisible(true);
            });
            addGood.addActionListener(e -> {
                goodsFrame.setVisible(false);
                StandartFrame addGoodFrame = new StandartFrame("Додати товар");
                JButton addGoodButton = new CoolButton("Вибрати групу");
                JButton back5 = new CoolButton("Назад");
                JButton[] buttons5 = {addGoodButton, back5};
                String[] groupNames = new String[storage.groupList.size()];
                for (int i = 0; i < storage.groupList.size(); i++) {
                    groupNames[i] = storage.groupList.get(i).getName();
                }
                addGoodFrame.setSearchPanel(Arrays.asList(groupNames));
                addGoodFrame.setButtonPanel(buttons5);

                back5.addActionListener(e3 -> {
                    addGoodFrame.setVisible(false);
                    goodsFrame.setVisible(true);
                });
                addGoodButton.addActionListener(e8 -> {
                    addGoodFrame.setVisible(false);
                    Group group = storage.groupList.get(addGoodFrame.list.getSelectedIndex());
                    StandartFrame addGoodFrame1 = new StandartFrame("Додати товар");
                    JTextField goodNameField = new JTextField();
                    JTextField goodDescriptionField = new JTextField();
                    JTextField goodProducerField = new JTextField();
                    JTextField goodAmountField = new JTextField();
                    JTextField goodPriceField = new JTextField();
                    JLabel goodName = new JLabel("Назва товару");
                    JLabel goodDescription = new JLabel("Опис товару");
                    JLabel goodProducer = new JLabel("Виробник товару");
                    JLabel goodAmount = new JLabel("Кількість товару");
                    JLabel goodPrice = new JLabel("Ціна товару");
                    JTextField[] textFields = {goodNameField, goodDescriptionField, goodProducerField, goodAmountField, goodPriceField};
                    JLabel[] labels = {goodName, goodDescription, goodProducer, goodAmount, goodPrice};
                    addGoodFrame1.setTextInputPanel(textFields, labels);
                    JButton addGoodButton1 = new CoolButton("Додати товар");
                    JButton backButton = new CoolButton("Назад");
                    JButton[] buttons6 = {addGoodButton1, backButton};
                    addGoodFrame1.setButtonPanel(buttons6);
                    addGoodFrame1.setVisible(true);
                    backButton.addActionListener(e9 -> {
                        addGoodFrame1.setVisible(false);
                        addGoodFrame.setVisible(true);
                    });
                    addGoodButton1.addActionListener(e10 -> {
                        addGoodFrame1.setVisible(false);
                        goodsFrame.setVisible(true);
                        if (goodNameField.getText().isEmpty() || goodDescriptionField.getText().isEmpty() || goodProducerField.getText().isEmpty() || goodAmountField.getText().isEmpty() || goodPriceField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(addGoodFrame1, "Заповніть всі поля");
                            return;
                        }
                        if (!goodAmountField.getText().matches("\\d+") || (!goodPriceField.getText().matches("\\d+\\.\\d+")  &&  !goodPriceField.getText().matches("\\d+"))) {
                            JOptionPane.showMessageDialog(addGoodFrame1, "Некоректні дані");
                            return;
                        }
                        if (Integer.parseInt(goodAmountField.getText()) < 0 || Double.parseDouble(goodPriceField.getText()) <= 0) {
                            JOptionPane.showMessageDialog(addGoodFrame1, "Некоректні дані");
                            return;
                        }
                        for (Group g : storage.groupList) {
                            for (Good good : g.goods) {
                                if (good.getName().equalsIgnoreCase(goodNameField.getText())) {
                                    JOptionPane.showMessageDialog(addGoodFrame1, "Товар з такою назвою вже існує");
                                    return;
                                }
                            }
                        }

                        group.goods.add(new Good(goodNameField.getText(), goodDescriptionField.getText(), goodProducerField.getText(), Integer.parseInt(goodAmountField.getText()), Double.parseDouble(goodPriceField.getText())));
                        group.writeGoods();
                    });


                });
            });
            editGood.addActionListener(e32 -> {
                goodsFrame.setVisible(false);
                StandartFrame editGoodFrame = new StandartFrame("Редагувати товар");
                JButton editGoodButton = new CoolButton("Вибрати групу");
                JButton back6 = new CoolButton("Назад");
                JButton[] buttons6 = {editGoodButton, back6};
                String[] groupNames = new String[storage.groupList.size()];
                for (int i = 0; i < storage.groupList.size(); i++) {
                    groupNames[i] = storage.groupList.get(i).getName();
                }
                editGoodFrame.setSearchPanel(Arrays.asList(groupNames));
                editGoodFrame.setButtonPanel(buttons6);
                editGoodFrame.setVisible(true);
                back6.addActionListener(e33 -> {
                    editGoodFrame.setVisible(false);
                    goodsFrame.setVisible(true);
                });
                editGoodButton.addActionListener(e -> {
                    editGoodFrame.setVisible(false);
                    Group group = storage.groupList.get(editGoodFrame.list.getSelectedIndex());
                    StandartFrame editGoodFrame1 = new StandartFrame("Редагувати товар");
                    String[] goodNames = new String[group.goods.size()];
                    for (int i = 0; i < group.goods.size(); i++) {
                        goodNames[i] = group.goods.get(i).getName();
                    }
                    editGoodFrame1.setSearchPanel(Arrays.asList(goodNames));
                    JButton editGoodNameButton = new CoolButton("Редагувати назву товару");
                    JButton editGoodDescriptionButton = new CoolButton("Редагувати опис товару");
                    JButton editGoodProducerButton = new CoolButton("Редагувати виробника товару");
                    JButton editGoodAmountButton = new CoolButton("Редагувати кількість товару");
                    JButton editGoodPriceButton = new CoolButton("Редагувати ціну товару");
                    JButton back7 = new CoolButton("Назад");
                    JButton[] buttons7 = {editGoodNameButton, editGoodDescriptionButton, editGoodProducerButton, editGoodAmountButton, editGoodPriceButton, back7};
                    editGoodFrame1.setButtonPanel(buttons7);
                    editGoodFrame1.setVisible(true);
                    back7.addActionListener(e34 -> {
                        editGoodFrame1.setVisible(false);
                        editGoodFrame.setVisible(true);
                    });

                });

            });
            deleteGood.addActionListener(e -> {
                goodsFrame.setVisible(false);
                StandartFrame deleteGoodFrame = new StandartFrame("Видалити товар");
                JButton deleteGoodButton = new CoolButton("Вибрати групу");
                JButton back8 = new CoolButton("Назад");
                JButton[] buttons8 = {deleteGoodButton, back8};
                String[] groupNames = new String[storage.groupList.size()];
                for (int i = 0; i < storage.groupList.size(); i++) {
                    groupNames[i] = storage.groupList.get(i).getName();
                }
                deleteGoodFrame.setSearchPanel(Arrays.asList(groupNames));
                deleteGoodFrame.setButtonPanel(buttons8);
                deleteGoodFrame.setVisible(true);
                back8.addActionListener(e3 -> {
                    deleteGoodFrame.setVisible(false);
                    goodsFrame.setVisible(true);

                });
                deleteGoodButton.addActionListener(e4 -> {
                    deleteGoodFrame.setVisible(false);
                    Group group = storage.groupList.get(deleteGoodFrame.list.getSelectedIndex());
                    StandartFrame deleteGoodFrame1 = new StandartFrame("Видалити товар");
                    String[] goodNames = new String[group.goods.size()];
                    for (int i = 0; i < group.goods.size(); i++) {
                        goodNames[i] = group.goods.get(i).getName();
                    }
                    deleteGoodFrame1.setSearchPanel(Arrays.asList(goodNames));
                    JButton deleteGoodButton1 = new CoolButton("Видалити товар");
                    JButton back9 = new CoolButton("Назад");
                    JButton[] buttons9 = {deleteGoodButton1, back9};
                    deleteGoodFrame1.setButtonPanel(buttons9);
                    deleteGoodFrame1.setVisible(true);
                    back9.addActionListener(e5 -> {
                        deleteGoodFrame1.setVisible(false);
                        deleteGoodFrame.setVisible(true);
                    });
                    deleteGoodButton1.addActionListener(e6 -> {
                        deleteGoodFrame1.setVisible(false);
                        goodsFrame.setVisible(true);
                        group.deleteGood(group.goods.get(deleteGoodFrame1.list.getSelectedIndex()));
                        group.writeGoods();
                    });
                });
            });

        });
        stats.addActionListener(e -> {
            menu.setVisible(false);
            storage.readGroups("Files\\groups.txt");
            for (Group group : storage.groupList) {
                group.readGoods();
            }
            StandartFrame statsFrame = new StandartFrame("Статистика");
            JButton storageStats = new CoolButton("Всі товари на складі");
            JButton groupStats = new CoolButton("Всі товари в групі");
            JButton allGoodsPrice = new CoolButton("Загальна ціна товарів на складі");
            JButton groupGoodsPrice = new CoolButton("Вартість товарів у групі");
            JButton back10 = new CoolButton("Назад");
            JButton[] buttons10 = {storageStats, groupStats, allGoodsPrice, groupGoodsPrice, back10};
            statsFrame.setInfoPanel("Статистика");
            statsFrame.setButtonPanel(buttons10);
            statsFrame.setVisible(true);
            back10.addActionListener(e1 -> {
                statsFrame.setVisible(false);
                menu.setVisible(true);
            });
            storageStats.addActionListener(e1 -> {
                statsFrame.setVisible(false);
                StandartFrame storageStatsFrame = new StandartFrame("Всі товари на складі");
                JPanel storageStatsPanel = new JPanel();
                storageStatsPanel.setLayout(new GridLayout(1, 1));
                String txt1 = "";
                for (Group group : storage.groupList) {
                    txt1 += "- " + group + ":\n";
                    if (group.goods.isEmpty())
                        txt1 += "   Немає товарів\n";
                    for (Good good : group.goods) {
                        txt1 += "   " + good + "\n";
                    }
                }
                System.out.println(storage.groupList);
                JTextArea storageStatsArea = new JTextArea(txt1);
                storageStatsArea.setBackground(new Color(217, 185, 155));
                storageStatsArea.setLineWrap(true);
                storageStatsArea.setAutoscrolls(true);
                storageStatsArea.setFont(new Font("Arial", Font.BOLD, 18));
                storageStatsArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(storageStatsArea);
                storageStatsPanel.add(scrollPane);
                JButton back11 = new CoolButton("Назад");
                storageStatsFrame.add(storageStatsPanel);
                storageStatsFrame.add(back11);
                storageStatsFrame.setVisible(true);
                back11.addActionListener(e2 -> {
                    storageStatsFrame.setVisible(false);
                    statsFrame.setVisible(true);
                });
            });
            groupStats.addActionListener(e1 -> {
                statsFrame.setVisible(false);
                StandartFrame groupStatsFrame = new StandartFrame("Всі товари в групі");
                String[] groupNames = new String[storage.groupList.size()];
                for (int i = 0; i < storage.groupList.size(); i++) {
                    groupNames[i] = storage.groupList.get(i).getName();
                }
                groupStatsFrame.setSearchPanel(Arrays.asList(groupNames));
                JButton groupStatsButton = new CoolButton("Вибрати групу");
                JButton back12 = new CoolButton("Назад");
                JButton[] buttons11 = {groupStatsButton, back12};
                groupStatsFrame.setButtonPanel(buttons11);
                groupStatsFrame.setVisible(true);



                back12.addActionListener(e2 -> {
                    groupStatsFrame.setVisible(false);
                    statsFrame.setVisible(true);
                });
                groupStatsButton.addActionListener(e2 -> {
                    groupStatsFrame.setVisible(false);
                    JFrame groupStatsFrame1 = new JFrame("Всі товари в групі");
                    groupStatsFrame1.setLayout(new GridLayout(2, 1));
                    groupStatsFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    groupStatsFrame1.setSize(1080, 720);
                    groupStatsFrame1.setBackground(new Color(217, 185, 155));
                    JPanel groupStatsPanel1 = new JPanel();
                    groupStatsPanel1.setLayout(new GridLayout(1, 1));
                    String txt2 = "";
                    Group group = storage.groupList.get(groupStatsFrame.list.getSelectedIndex());
                    txt2 += group + ":\n";
                    if (group.goods.isEmpty())
                        txt2 += "Немає товарів\n";
                    for (Good good : group.goods) {
                        txt2 += good + "\n";
                    }
                    JTextArea groupStatsArea = new JTextArea(txt2);
                    groupStatsArea.setBackground(new Color(217, 185, 155));
                    groupStatsArea.setLineWrap(true);
                    groupStatsArea.setAutoscrolls(true);
                    groupStatsArea.setFont(new Font("Arial", Font.BOLD, 24));
                    groupStatsArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(groupStatsArea);
                    groupStatsPanel1.add(scrollPane);
                    JButton back13 = new CoolButton("Назад");
                    groupStatsFrame1.add(groupStatsPanel1);
                    groupStatsFrame1.add(back13);
                    groupStatsFrame1.setVisible(true);
                    back13.addActionListener(e3 -> {
                        groupStatsFrame1.setVisible(false);
                        groupStatsFrame.setVisible(true);
                    });
                });


            });
            allGoodsPrice.addActionListener(e2 ->{
                String storagePrice = "Загальна ціна товарів на складі: ";
                double allPrice = 0;
                for (Group group : storage.groupList) {
                    for (Good good : group.goods) {
                        allPrice += good.getPrice() * good.getAmount();
                    }
                }
                storagePrice += allPrice + " грн";
                JOptionPane.showMessageDialog(statsFrame, storagePrice);
            });
            groupGoodsPrice.addActionListener(e1 -> {
                statsFrame.setVisible(false);
                StandartFrame chooseGroup = new StandartFrame("Вартість товарів у групі");
                String[] groupNames = new String[storage.groupList.size()];
                for (int i = 0; i < storage.groupList.size(); i++) {
                    groupNames[i] = storage.groupList.get(i).getName();
                }
                chooseGroup.setSearchPanel(Arrays.asList(groupNames));
                JButton chooseGroupButton = new CoolButton("Вибрати групу");
                JButton back14 = new CoolButton("Назад");
                JButton[] buttons12 = {chooseGroupButton, back14};
                chooseGroup.setButtonPanel(buttons12);
                chooseGroup.setVisible(true);
                back14.addActionListener(e2 -> {
                    chooseGroup.setVisible(false);
                    statsFrame.setVisible(true);

                });
                chooseGroupButton.addActionListener(e2 -> {
                    Group group = storage.groupList.get(chooseGroup.list.getSelectedIndex());
                    double groupPrice = 0;
                    String groupPriceString = "Вартість товарів у групі " + group.getName() + ": ";
                    for (Good good : group.goods) {
                        groupPrice += good.getPrice() * good.getAmount();
                    }
                    groupPriceString += groupPrice + " грн";
                    JOptionPane.showMessageDialog(chooseGroup, groupPriceString);

                });
            });
        });
    }
}