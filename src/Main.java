import javax.swing.*;
import java.awt.*;
import java.io.*;

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
        JFrame menu = new JFrame("Tester");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(1080, 720);
        menu.setLayout(new GridLayout(2, 1));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1));
        JButton groups = new CoolButton("Групи товарів");
        JButton goods = new CoolButton("Товари");
        JButton stats = new CoolButton("Статистика");
        menuPanel.add(groups);
        menuPanel.add(goods);
        menuPanel.add(stats);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 1));
        JLabel title = new JLabel("Управління підприємством");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        infoPanel.setBackground(new Color(217, 185, 155));
        infoPanel.add(title);
        menu.add(infoPanel);
        menu.add(menuPanel);
        menu.setVisible(true);
        groups.addActionListener(e -> {
            menu.setVisible(false);
            JFrame groupsFrame = new JFrame("Групи товарів");
            groupsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            groupsFrame.setSize(1080, 720);
            groupsFrame.setLayout(new GridLayout(2, 1));
            JPanel groupsPanel = new JPanel();
            groupsPanel.setLayout(new GridLayout(4, 1));
            JButton addGroup = new CoolButton("Додати групу");
            groupsPanel.add(addGroup);
            JButton editGroup = new CoolButton("Редагувати групу");
            groupsPanel.add(editGroup);
            JButton deleteGroup = new CoolButton("Видалити групу");
            groupsPanel.add(deleteGroup);
            JButton back = new CoolButton("Назад");
            groupsPanel.add(back);
            JPanel infoPanel1 = new JPanel();
            infoPanel1.setLayout(new GridLayout(1, 1));
            JLabel title1 = new JLabel("Групи товарів");
            title1.setHorizontalAlignment(SwingConstants.CENTER);
            title1.setFont(new Font("Arial", Font.BOLD, 36));
            infoPanel1.setBackground(new Color(217, 185, 155));
            infoPanel1.add(title1);
            groupsFrame.add(infoPanel1);
            groupsFrame.add(groupsPanel);
            groupsFrame.setVisible(true);
            back.addActionListener(e1 -> {
                groupsFrame.setVisible(false);
                menu.setVisible(true);
            });
            addGroup.addActionListener(e12 -> {
                groupsFrame.setVisible(false);
                JFrame addGroupFrame = new JFrame("Додати групу");
                addGroupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addGroupFrame.setSize(1080, 720);
                addGroupFrame.setLayout(new GridLayout(2, 1));
                JPanel addGroupPanel = new JPanel();
                JPanel groupButtons = new JPanel();
                groupButtons.setLayout(new GridLayout(2, 1));
                addGroupPanel.setLayout(new GridLayout(2, 2));
                addGroupPanel.setBackground(new Color(217, 185, 155));
                JLabel groupName = new JLabel("Назва групи");
                groupName.setFont(new Font("Arial", Font.BOLD, 24));
                addGroupPanel.add(groupName);
                JTextField groupNameField = new JTextField();
                groupNameField.setFont(new Font("Arial", Font.BOLD, 20));
                addGroupPanel.add(groupNameField);
                JLabel groupDescription = new JLabel("Опис групи");
                groupDescription.setFont(new Font("Arial", Font.BOLD, 24));
                addGroupPanel.add(groupDescription);
                JTextField groupDescriptionField = new JTextField();
                groupDescriptionField.setFont(new Font("Arial", Font.BOLD, 20));
                addGroupPanel.add(groupDescriptionField);
                JButton addGroupButton = new CoolButton("Додати групу");
                groupButtons.add(addGroupButton);
                JButton back2 = new CoolButton("Назад");
                groupButtons.add(back2);
                addGroupFrame.add(addGroupPanel);
                addGroupFrame.add(groupButtons);
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
                        if (group.getName().equals(groupNameField.getText())) {
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
                JFrame editGroupFrame = new JFrame("Редагувати групу");
                editGroupFrame.setVisible(true);
                editGroupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                editGroupFrame.setSize(1080, 720);
                editGroupFrame.setLayout(new GridLayout(2, 1));
                JPanel editGroupPanel = new JPanel();
                editGroupPanel.setLayout(new GridLayout(1, 1));
                JPanel groupButtons = new JPanel();
                groupButtons.setLayout(new GridLayout(3, 1));
                JComboBox<String> groupList = new JComboBox<>();
                for (Group group : storage.groupList) {
                    groupList.addItem(group.getName());
                }
                groupList.setFont(new Font("Arial", Font.BOLD, 18));
                editGroupPanel.add(groupList);
                JButton editGroupNameButton = new CoolButton("Редагувати назву групи");
                groupButtons.add(editGroupNameButton);
                JButton editGroupDescriptionButton = new CoolButton("Редагувати опис групи");
                groupButtons.add(editGroupDescriptionButton);
                JButton back3 = new CoolButton("Назад");
                groupButtons.add(back3);
                editGroupFrame.add(editGroupPanel);
                editGroupFrame.add(groupButtons);
                back3.addActionListener(e42 -> {
                    groupsFrame.setVisible(true);
                    editGroupFrame.setVisible(false);
                });
                editGroupNameButton.addActionListener(e5 -> {
                    editGroupFrame.setVisible(false);
                    JFrame editGroupNameFrame = new JFrame("Редагувати назву групи");
                    editGroupNameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    editGroupNameFrame.setSize(1080, 720);
                    editGroupNameFrame.setLayout(new GridLayout(2, 1));
                    JPanel editGroupNamePanel = new JPanel();
                    editGroupNamePanel.setLayout(new GridLayout(2, 2));
                    editGroupNamePanel.setBackground(new Color(217, 185, 155));
                    JLabel groupName = new JLabel("Нова назва групи");
                    groupName.setFont(new Font("Arial", Font.BOLD, 24));
                    editGroupNamePanel.add(groupName);
                    JTextField groupNameField = new JTextField();
                    groupNameField.setFont(new Font("Arial", Font.BOLD, 20));
                    editGroupNamePanel.add(groupNameField);
                    JButton editGroupNameButton1 = new CoolButton("Редагувати назву групи");
                    editGroupNameFrame.add(editGroupNamePanel);
                    editGroupNameFrame.add(editGroupNameButton1);
                    editGroupNameFrame.setVisible(true);
                    editGroupNameButton1.addActionListener(e6 -> {
                        editGroupNameFrame.setVisible(false);
                        groupsFrame.setVisible(true);
                        if (groupNameField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(editGroupNameFrame, "Введіть назву групи");
                            return;
                        }
                        for (Group group : storage.groupList) {
                            if (group.getName().equals(groupNameField.getText())) {
                                JOptionPane.showMessageDialog(editGroupNameFrame, "Група з такою назвою вже існує");
                                return;
                            }
                        }
                        storage.groupList.get(groupList.getSelectedIndex()).setName(groupNameField.getText());
                        storage.writeGroups("Files\\groups.txt");
                    });
                });
                editGroupDescriptionButton.addActionListener(e7 -> {
                    editGroupFrame.setVisible(false);
                    JFrame editGroupDescriptionFrame = new JFrame("Редагувати опис групи");
                    editGroupDescriptionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    editGroupDescriptionFrame.setSize(1080, 720);
                    editGroupDescriptionFrame.setLayout(new GridLayout(2, 1));
                    JPanel editGroupDescriptionPanel = new JPanel();
                    editGroupDescriptionPanel.setLayout(new GridLayout(2, 2));
                    editGroupDescriptionPanel.setBackground(new Color(217, 185, 155));
                    JLabel groupDescription = new JLabel("Новий опис групи");
                    groupDescription.setFont(new Font("Arial", Font.BOLD, 24));
                    editGroupDescriptionPanel.add(groupDescription);
                    JTextField groupDescriptionField = new JTextField();
                    groupDescriptionField.setFont(new Font("Arial", Font.BOLD, 20));
                    editGroupDescriptionPanel.add(groupDescriptionField);
                    JButton editGroupDescriptionButton1 = new CoolButton("Редагувати опис групи");
                    editGroupDescriptionFrame.add(editGroupDescriptionPanel);
                    editGroupDescriptionFrame.add(editGroupDescriptionButton1);
                    editGroupDescriptionFrame.setVisible(true);
                    editGroupDescriptionButton1.addActionListener(e8 -> {
                        editGroupDescriptionFrame.setVisible(false);
                        groupsFrame.setVisible(true);
                        storage.groupList.get(groupList.getSelectedIndex()).setDescription(groupDescriptionField.getText());
                        storage.writeGroups("Files\\groups.txt");
                    });
                });

            });
            deleteGroup.addActionListener(e9 -> {
                groupsFrame.setVisible(false);
                JFrame deleteGroupFrame = new JFrame("Видалити групу");
                deleteGroupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                deleteGroupFrame.setSize(1080, 720);
                deleteGroupFrame.setLayout(new GridLayout(2, 1));
                JPanel deleteGroupPanel = new JPanel();
                deleteGroupPanel.setLayout(new GridLayout(1, 1));
                JPanel groupButtons = new JPanel();
                groupButtons.setLayout(new GridLayout(2, 1));
                JComboBox<String> groupList = new JComboBox<>();
                for (Group group : storage.groupList) {
                    groupList.addItem(group.getName());
                }
                groupList.setFont(new Font("Arial", Font.BOLD, 18));
                deleteGroupPanel.add(groupList);
                JButton deleteGroupButton = new CoolButton("Видалити групу");
                groupButtons.add(deleteGroupButton);
                JButton back4 = new CoolButton("Назад");
                groupButtons.add(back4);
                deleteGroupFrame.add(deleteGroupPanel);
                deleteGroupFrame.add(groupButtons);
                deleteGroupFrame.setVisible(true);
                back4.addActionListener(e10 -> {
                    groupsFrame.setVisible(true);
                    deleteGroupFrame.setVisible(false);
                });
                deleteGroupButton.addActionListener(e11 -> {
                    deleteGroupFrame.setVisible(false);
                    groupsFrame.setVisible(true);
                    storage.deleteGroup(storage.groupList.get(groupList.getSelectedIndex()));
                    storage.writeGroups("Files\\groups.txt");
                });
            });
        });
        goods.addActionListener(e1 -> {
            for (Group group : storage.groupList) {
                group.readGoods();
            }
            menu.setVisible(false);
            JFrame goodsFrame = new JFrame("Групи товарів");
            goodsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            goodsFrame.setSize(1080, 720);
            goodsFrame.setLayout(new GridLayout(2, 1));
            JPanel goodsPanel = new JPanel();
            goodsPanel.setLayout(new GridLayout(3, 2));
            JButton addGood = new CoolButton("Додати товар");
            goodsPanel.add(addGood);
            JButton editGood = new CoolButton("Редагувати товар");
            goodsPanel.add(editGood);
            JButton deleteGood = new CoolButton("Видалити товар");
            goodsPanel.add(deleteGood);
            JButton searchGood = new CoolButton("Пошук товару");
            goodsPanel.add(searchGood);
            JButton goodAmount = new CoolButton("Додати/списати товар");
            goodsPanel.add(goodAmount);
            JButton back1 = new CoolButton("Назад");
            goodsPanel.add(back1);
            JPanel infoPanel2 = new JPanel();
            infoPanel2.setLayout(new GridLayout(1, 1));
            JLabel title2 = new JLabel("Товари");
            title2.setHorizontalAlignment(SwingConstants.CENTER);
            title2.setFont(new Font("Arial", Font.BOLD, 36));
            infoPanel2.setBackground(new Color(217, 185, 155));
            infoPanel2.add(title2);
            goodsFrame.add(infoPanel2);
            goodsFrame.add(goodsPanel);
            goodsFrame.setVisible(true);
            back1.addActionListener(e2 -> {
                goodsFrame.setVisible(false);
                menu.setVisible(true);
            });
            addGood.addActionListener(e -> {
                goodsFrame.setVisible(false);
                JFrame addGoodFrame = new JFrame("Додати товар");
                addGoodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addGoodFrame.setSize(1080, 720);
                addGoodFrame.setLayout(new GridLayout(2, 1));
                JPanel addGoodPanel = new JPanel();
                JPanel goodButtons = new JPanel();
                goodButtons.setLayout(new GridLayout(2, 1));
                addGoodPanel.setLayout(new GridLayout(1, 1));
                addGoodPanel.setBackground(new Color(217, 185, 155));
                JComboBox<String> groupList = new JComboBox<>();
                for (Group group : storage.groupList) {
                    groupList.addItem(group.getName());
                }
                groupList.setFont(new Font("Arial", Font.BOLD, 18));
                addGoodPanel.add(groupList);
                JButton addGoodButton = new CoolButton("Вибрати групу");
                JButton back5 = new CoolButton("Назад");
                goodButtons.add(addGoodButton);
                goodButtons.add(back5);
                addGoodFrame.add(addGoodPanel);
                addGoodFrame.add(goodButtons);
                addGoodFrame.setVisible(true);
                back5.addActionListener(e3 -> {
                    addGoodFrame.setVisible(false);
                    goodsFrame.setVisible(true);
                });
                addGoodButton.addActionListener(e8 -> {
                    addGoodFrame.setVisible(false);
                    Group group = storage.groupList.get(groupList.getSelectedIndex());
                    JFrame addGoodFrame1 = new JFrame("Додати товар");
                    addGoodFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    addGoodFrame1.setSize(1080, 720);
                    addGoodFrame1.setLayout(new GridLayout(2, 1));
                    JPanel addGoodPanel1 = new JPanel();
                    addGoodPanel1.setLayout(new GridLayout(5, 5));
                    addGoodPanel1.setBackground(new Color(217, 185, 155));
                    JLabel goodName = new JLabel("Назва товару");
                    goodName.setFont(new Font("Arial", Font.BOLD, 24));
                    addGoodPanel1.add(goodName);
                    JTextField goodNameField = new JTextField();
                    goodNameField.setFont(new Font("Arial", Font.BOLD, 20));
                    addGoodPanel1.add(goodNameField);
                    JLabel goodDescription = new JLabel("Опис товару");
                    goodDescription.setFont(new Font("Arial", Font.BOLD, 24));
                    addGoodPanel1.add(goodDescription);
                    JTextField goodDescriptionField = new JTextField();
                    goodDescriptionField.setFont(new Font("Arial", Font.BOLD, 20));
                    addGoodPanel1.add(goodDescriptionField);
                    JLabel goodProducer = new JLabel("Виробник товару");
                    goodProducer.setFont(new Font("Arial", Font.BOLD, 24));
                    addGoodPanel1.add(goodProducer);
                    JTextField goodProducerField = new JTextField();
                    goodProducerField.setFont(new Font("Arial", Font.BOLD, 20));
                    addGoodPanel1.add(goodProducerField);
                    JLabel goodAmountt = new JLabel("Кількість товару");
                    goodAmountt.setFont(new Font("Arial", Font.BOLD, 24));
                    addGoodPanel1.add(goodAmountt);
                    JTextField goodAmountField = new JTextField();
                    goodAmountField.setFont(new Font("Arial", Font.BOLD, 20));
                    addGoodPanel1.add(goodAmountField);
                    JLabel goodPrice = new JLabel("Ціна товару");
                    goodPrice.setFont(new Font("Arial", Font.BOLD, 24));
                    addGoodPanel1.add(goodPrice);
                    JTextField goodPriceField = new JTextField();
                    goodPriceField.setFont(new Font("Arial", Font.BOLD, 20));
                    addGoodPanel1.add(goodPriceField);
                    JButton addGoodButton1 = new CoolButton("Додати товар");
                    JButton backButton = new CoolButton("Назад");
                    addGoodFrame1.add(addGoodPanel1);
                    JPanel buttons = new JPanel();
                    buttons.setLayout(new GridLayout(2, 1));
                    buttons.add(addGoodButton1);
                    buttons.add(backButton);
                    addGoodFrame1.add(buttons);
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
                        if (!goodAmountField.getText().matches("\\d+") || !goodPriceField.getText().matches("\\d+\\.\\d+")) {
                            JOptionPane.showMessageDialog(addGoodFrame1, "Некоректні дані");
                            return;
                        }
                        if (Integer.parseInt(goodAmountField.getText()) < 0 || Double.parseDouble(goodPriceField.getText()) <= 0) {
                            JOptionPane.showMessageDialog(addGoodFrame1, "Некоректні дані");
                            return;
                        }
                        for (Group g : storage.groupList) {
                            for (Good good : g.goods) {
                                if (good.getName().equals(goodNameField.getText())) {
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
                JFrame editGoodFrame = new JFrame("Редагувати товар");
                editGoodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                editGoodFrame.setSize(1080, 720);
                editGoodFrame.setLayout(new GridLayout(2, 1));
                JPanel editGoodPanel = new JPanel();
                editGoodPanel.setLayout(new GridLayout(1, 1));
                JPanel editGoodButtons = new JPanel();
                editGoodButtons.setLayout(new GridLayout(2, 1));
                JComboBox<String> groupList1 = new JComboBox<>();
                for (Group group : storage.groupList) {
                    groupList1.addItem(group.getName());
                }
                groupList1.setFont(new Font("Arial", Font.BOLD, 18));
                editGoodPanel.add(groupList1);
                JButton editGoodButton = new CoolButton("Вибрати групу");
                editGoodButtons.add(editGoodButton);
                JButton back6 = new CoolButton("Назад");
                editGoodButtons.add(back6);
                editGoodFrame.add(editGoodPanel);
                editGoodFrame.add(editGoodButtons);
                editGoodFrame.setVisible(true);
                back6.addActionListener(e33 -> {
                    editGoodFrame.setVisible(false);
                    goodsFrame.setVisible(true);
                });
                editGoodButton.addActionListener(e -> {
                    editGoodFrame.setVisible(false);
                    Group group = storage.groupList.get(groupList1.getSelectedIndex());
                    JFrame editGoodFrame1 = new JFrame("Редагувати товар");
                    editGoodFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    editGoodFrame1.setSize(1080, 720);
                    editGoodFrame1.setLayout(new GridLayout(2, 1));
                    JPanel editGoodPanel1 = new JPanel();
                    editGoodPanel1.setLayout(new GridLayout(1, 1));
                    JPanel editGoodButtons1 = new JPanel();
                    editGoodButtons1.setLayout(new GridLayout(6, 1));
                    JComboBox<String> goodList = new JComboBox<>();
                    for (Good good : group.goods) {
                        goodList.addItem(good.getName());
                    }
                    goodList.setFont(new Font("Arial", Font.BOLD, 18));
                    editGoodPanel1.add(goodList);

                    JButton editGoodDescriptionButton = new CoolButton("Редагувати опис товару");
                    JButton editGoodProducerButton = new CoolButton("Редагувати виробника товару");
                    JButton editGoodAmountButton = new CoolButton("Редагувати кількість товару");
                    JButton editGoodPriceButton = new CoolButton("Редагувати ціну товару");
                    JButton editGoodNameButton = new CoolButton("Редагувати назву товару");
                    editGoodButtons1.add(editGoodNameButton);
                    editGoodButtons1.add(editGoodDescriptionButton);
                    editGoodButtons1.add(editGoodProducerButton);
                    editGoodButtons1.add(editGoodAmountButton);
                    editGoodButtons1.add(editGoodPriceButton);
                    JButton back7 = new CoolButton("Назад");
                    editGoodButtons1.add(back7);
                    editGoodFrame1.add(editGoodPanel1);
                    editGoodFrame1.add(editGoodButtons1);
                    editGoodFrame1.setVisible(true);
                    back7.addActionListener(e34 -> {
                        editGoodFrame1.setVisible(false);
                        editGoodFrame.setVisible(true);
                    });

                });

            });
            deleteGood.addActionListener(e -> {
                JFrame deleteGoodFrame = new JFrame("Видалити товар");
                deleteGoodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                deleteGoodFrame.setSize(1080, 720);
                deleteGoodFrame.setLayout(new GridLayout(2, 1));
                JPanel deleteGoodPanel = new JPanel();
                deleteGoodPanel.setLayout(new GridLayout(1, 1));
                JPanel deleteGoodButtons = new JPanel();
                deleteGoodButtons.setLayout(new GridLayout(2, 1));
                JComboBox<String> groupList2 = new JComboBox<>();
                for (Group group : storage.groupList) {
                    groupList2.addItem(group.getName());
                }
                groupList2.setFont(new Font("Arial", Font.BOLD, 18));
                deleteGoodPanel.add(groupList2);
                JButton deleteGoodButton = new CoolButton("Вибрати групу");
                deleteGoodButtons.add(deleteGoodButton);
                JButton back8 = new CoolButton("Назад");
                deleteGoodButtons.add(back8);
                deleteGoodFrame.add(deleteGoodPanel);
                deleteGoodFrame.add(deleteGoodButtons);
                deleteGoodFrame.setVisible(true);
                back8.addActionListener(e3 -> {
                    deleteGoodFrame.setVisible(false);
                    goodsFrame.setVisible(true);
                });
                deleteGoodButton.addActionListener(e4 -> {
                    deleteGoodFrame.setVisible(false);
                    Group group = storage.groupList.get(groupList2.getSelectedIndex());
                    JFrame deleteGoodFrame1 = new JFrame("Видалити товар");
                    deleteGoodFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    deleteGoodFrame1.setSize(1080, 720);
                    deleteGoodFrame1.setLayout(new GridLayout(2, 1));
                    JPanel deleteGoodPanel1 = new JPanel();
                    deleteGoodPanel1.setLayout(new GridLayout(1, 1));
                    JPanel deleteGoodButtons1 = new JPanel();
                    deleteGoodButtons1.setLayout(new GridLayout(2, 1));
                    JComboBox<String> goodList = new JComboBox<>();
                    for (Good good : group.goods) {
                        goodList.addItem(good.getName());
                    }
                    goodList.setFont(new Font("Arial", Font.BOLD, 18));
                    deleteGoodPanel1.add(goodList);
                    JButton deleteGoodButton1 = new CoolButton("Видалити товар");
                    deleteGoodButtons1.add(deleteGoodButton1);
                    JButton back9 = new CoolButton("Назад");
                    deleteGoodButtons1.add(back9);
                    deleteGoodFrame1.add(deleteGoodPanel1);
                    deleteGoodFrame1.add(deleteGoodButtons1);
                    deleteGoodFrame1.setVisible(true);
                    back9.addActionListener(e5 -> {
                        deleteGoodFrame1.setVisible(false);
                        deleteGoodFrame.setVisible(true);
                    });
                    deleteGoodButton1.addActionListener(e6 -> {
                        deleteGoodFrame1.setVisible(false);
                        goodsFrame.setVisible(true);
                        group.deleteGood(group.goods.get(goodList.getSelectedIndex()));
                        group.writeGoods();
                    });
                });
            });

        });
    }
}