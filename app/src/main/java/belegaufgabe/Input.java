package belegaufgabe;
import java.util.Scanner;
public class Input {
    static dlinkedList list = Operations.fillList();
    static int counter = 0;

    static int mainMenuInput() {
        Scanner sc = new Scanner(System.in);
        int entry = 0;
        boolean keeprunning = true;
        System.out.print("\nEnter one of the Options from above: ");
        while (keeprunning) {
            keeprunning = false;
            try {
                entry = sc.nextInt();
            } catch (Exception e) {
                System.out.print("You'r Entry of was not valid\nPlease try Again: ");
                sc.next();
                keeprunning = true;
            }
        }
        return entry; //returns entry as long as it's a number except 0
    }

    static String subMenuInputString() {
        Scanner sc = new Scanner(System.in);
        String entry = "";
        boolean keeprunning = true;
        //System.out.print("\nEnter one of the Options from above: ");
        while (keeprunning) {
            keeprunning = false;
            try {
                entry = sc.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("You'r Entry of was not valid\nPlease try Again: ");
                sc.next();
                keeprunning = true;
            }
        }
        return entry; //returns entry as long as it's a number except 0
    }

    static void exit() {
        System.out.println("You've choosen to end the Program.\nGoodbye!");
        System.exit(0);
    }

    static void performAnotherOperation(dlinkedList list) {
        Scanner sc = new Scanner(System.in);
        if (counter < 1) {
            System.out.print("\n\n\nThe Operation you requested was processed.\n");
            counter++;
        }
        System.out.print("Would you like to continue [Y/N]: ");
        try {
            String cn = sc.next();
            if (cn.equals("Y") || cn.equals("y")) {
                Menu.mainMenuInterface();
                Input.mainMenuSwitch(Input.mainMenuInput(), list);
            } else if (cn.equals("N") || cn.equals("n")) {
                exit();
            } else {
                System.out.println("Please enter a valid Option");
                performAnotherOperation(list);
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid Option");
            performAnotherOperation(list);
        }
    }

    static void mainMenuSwitch(int entry, dlinkedList list) {
        switch (entry) {
            case 1:
                list = list.addAtEndOfList(dlinkedList.addElement(list), list);
                performAnotherOperation(list);
            case 2:
                System.out.println("To edit a furniture Item you first need to locate it:");
                Menu.editElementSearchMenu();
                dlinkedList.Node node = editElementSearchSwitch(list);
                Menu.editElementSubMenu();
                editElementSwitch(node, list);
                performAnotherOperation(list);
            case 3:
                Menu.removeElementMenu();
                list = removeElementSwitch(mainMenuInput());
                performAnotherOperation(list);
            case 4:
                Menu.searchMenu();
                searchMenuSwitch(mainMenuInput(), list);
                performAnotherOperation(list);
            case 5:
                Menu.sortMenu();
                if (list.head == null) {
                    System.out.println("The List seems to be Empty there is nothing to be sorted");
                    sortMenuSwitch(mainMenuInput());
                }
                dlinkedList empty = new dlinkedList();
                list = sortMenuSwitch(mainMenuInput());
                dlinkedList.sortedList = empty;
                performAnotherOperation(list);
            case 6:
                System.out.println("These are all the Items in the List: ");
                dlinkedList.printAllElements(list);
                performAnotherOperation(list);
            case 7:
                dlinkedList.clearList(list);
                performAnotherOperation(list);
            case 8:
                int sum = dlinkedList.numberOfElements(list);
                System.out.println("The total Number of Items in the list is: " + sum);
                performAnotherOperation(list);
            case 9:
                double lowerLimit;
                double upperLimit;
                System.out.print("Please enter the lower price limit you would like to have, leave blank for no limit: ");
                try {
                    lowerLimit = Double.parseDouble(subMenuInputString());
                } catch (Exception e) {
                    lowerLimit = 0;
                    System.out.println("\nBeware if you entered a non Integer/Double the limit will be set to the minimum Price");
                }
                System.out.print("\nPlease enter the upper Limit you would like to have, leave blank for no limit: ");
                try {
                    upperLimit = Double.parseDouble(subMenuInputString());
                } catch (Exception e) {
                    upperLimit = 0;
                    System.out.println("\nBeware if you entered a non Integer/Double the limit will be set to the maximum Price");
                }
                dlinkedList.priceFilter(list, lowerLimit, upperLimit);
                performAnotherOperation(list);
            case 0:
                exit();
            default:
                Menu.mainMenuInterface();
                System.out.print("\nThe number You've chosen does not Exist");
                Input.mainMenuSwitch(Input.mainMenuInput(), list);
        }
    }

    static dlinkedList.Node searchMenuSwitch(int entry, dlinkedList list) {
        String string;
        dlinkedList.Node node;
        switch (entry) {
            case 1 -> {
                System.out.print("Please enter the Name of the Item you would like to search for: ");
                string = subMenuInputString();
                node = list.searchByName(string, list);
                if (node == null) {
                    System.out.println("\n\nAn Item with this Name could'nt be found");
                    searchMenuSwitch(0, list);
                } else {
                    dlinkedList.nodeToString(node);
                }
                return node;
            }
            case 4 -> {
                System.out.print("Please enter the Serialnumber of the Item you would like to search for: ");
                string = subMenuInputString();
                int intNumber = Integer.parseInt(string);
                node = list.searchBySN(intNumber, list);
                if (node == null) {
                    System.out.println("\n\nAn Item with this Serialnumber could'nt be found");
                } else {
                    dlinkedList.nodeToString(node);
                }
                return node;
            }
            case 2 -> {
                System.out.print("Please enter the Price of the Item you would like to search for: ");
                string = subMenuInputString();
                node = list.searchPrice(Double.parseDouble(string), list);
                return node;
            }
            case 3 -> {
                System.out.print("Please enter the Item Category you would like to search for: ");
                string = subMenuInputString();
                node = list.searchCategory(string, list);
                return node;
            }
            case 0 -> {
                Menu.mainMenuInterface();
                Input.mainMenuSwitch(Input.mainMenuInput(), list);
                return null;
            }
            default -> {
                Menu.searchMenu();
                System.out.print("\nThe number You've chosen does not Exist");
                Input.searchMenuSwitch(mainMenuInput(), list);
            }
        }
        return null;
    }

    static dlinkedList sortMenuSwitch(int entry) {
        switch (entry) {
            case 1:
                list = dlinkedList.quicksortString(list, "name");
                Menu.sortSubMenu();
                printSortedList();
                return list;
            case 2:
                list = dlinkedList.quicksortDouble(list, "price");
                Menu.sortSubMenu();
                printSortedList();
                return list;
            case 3:
                list = dlinkedList.quicksortString(list, "category");
                Menu.sortSubMenu();
                printSortedList();
                return list;
            case 4:
                list = dlinkedList.quicksortDouble(list, "serialnumber");
                Menu.sortSubMenu();
                printSortedList();
                break;
            case 0:
                Menu.mainMenuInterface();
                Input.mainMenuSwitch(Input.mainMenuInput(), list);
            default:
                Menu.sortMenu();
                System.out.println("\nThe number You've chosen does not Exist");
                Input.sortMenuSwitch(mainMenuInput());
        }
        return list;
    }

    static void printSortedList() {
        Scanner scanner = new Scanner(System.in);
        try {
            String cn = scanner.next();
            if (cn.equals("Y") || cn.equals("y")) {
                dlinkedList.printAllElements(list);
            } else if (cn.equals("N") || cn.equals("n")) {
                performAnotherOperation(list);
            } else {
                System.out.println("Please enter a valid Option");
                printSortedList();
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid Option");
            printSortedList();
        }
    }

    static void deletionSafetyCheck(dlinkedList.Node del) {
        if (del == null) {
            System.out.println("Please try again:");
            Menu.removeElementMenu();
            list = removeElementSwitch(mainMenuInput());
        }
        System.out.print("\nAre you sure you want to remove this Item? [Y/N]");
        Scanner scanner = new Scanner(System.in);
        try {
            String cn = scanner.next();
            if (cn.equals("Y") || cn.equals("y")) {
                assert del != null;
                dlinkedList.removeElement(list, del);
            } else if (cn.equals("N") || cn.equals("n")) {
                performAnotherOperation(list);
            } else {
                System.out.println("Please enter a valid Option");
                printSortedList();
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid Option");
            printSortedList();
        }
    }

    static dlinkedList removeElementSwitch(int entry) {
        switch (entry) {
            case 1:
                dlinkedList.Node del = searchMenuSwitch(1, list);
                deletionSafetyCheck(del);
                return list;
            case 2:
                del = searchMenuSwitch(4, list);
                deletionSafetyCheck(del);
                return list;
            case 0:
                Menu.mainMenuInterface();
                Input.mainMenuSwitch(Input.mainMenuInput(), list);
            default:
                System.out.println("\nThe number You've chosen does not Exist");
                return list;
        }
    }

    static void editElementSwitch(dlinkedList.Node node, dlinkedList list) {

        switch (mainMenuInput()) {
            case 1:
                double price = 0;
                dlinkedList.Node changedNode = new dlinkedList.Node(node.data);
                System.out.print("Please enter a valid Price: ");
                try{
                    price = Double.parseDouble(Input.subMenuInputString());
                    if(price < 0){
                        System.out.println("Negative Prices are not allowed, please try again");
                        editElementSwitch(node, list);
                    }
                } catch (Exception e) {
                    System.out.println("You'r entry for price was not valid, please try again");
                    editElementSwitch(node, list);
                }
                changedNode.data.setPrice(price);
                list.addAtEndOfList(changedNode.data, list);
                dlinkedList.removeElement(list, node);
                break;
            case 2:
                changedNode = new dlinkedList.Node(node.data);
                System.out.print("Please enter a Name:");
                String name = Input.subMenuInputString();
                if(list.searchByName(name, list) != null){
                    System.out.println("An item with this Name already exists please try again");
                    Menu.editElementSubMenu();
                    Input.editElementSwitch(node, Input.list);
                }
                changedNode.data.setName(name);
                list.addAtEndOfList(changedNode.data, list);
                dlinkedList.removeElement(list, node);
                break;
            case 3:
                changedNode = new dlinkedList.Node(node.data);
                System.out.print("Please enter a Category:");
                String category = Input.subMenuInputString();
                changedNode.data.setCategory(category);
                list.addAtEndOfList(changedNode.data, list);
                dlinkedList.removeElement(list, node);
                break;
            case 4:
                System.out.print("Please enter a Serialnumber:");
                int Serialnumber = 0;
                changedNode = new dlinkedList.Node(node.data);
                try {
                    Serialnumber = Integer.parseInt(Input.subMenuInputString());
                    if(list.searchBySN(Serialnumber, list) != null){
                        System.out.println("An item with this Serialnumber already exists please try again");
                        Menu.editElementSubMenu();
                        Input.editElementSwitch(node, Input.list);
                    }
                    if(Serialnumber < 0){
                        System.out.println("You'r entry for Serialnumber was not valid, please try again");
                        Input.mainMenuSwitch(1, Input.list);
                    }
                } catch (Exception e) {
                    System.out.println("You'r entry for Serialnumber was not valid, please try again");
                    Input.mainMenuSwitch(1, Input.list);
                }
                changedNode.data.setSerialNumber(Serialnumber);
                list.addAtEndOfList(changedNode.data, list);
                dlinkedList.removeElement(list, node);
                break;
            default:
                Menu.editElementSubMenu();
                System.out.println("The Number You've Chosen does not exist.");
                Input.editElementSwitch(node, list);
        }
        System.out.println("\n After Editing this is what your Item looks like");
        dlinkedList.nodeToString(node);
    }

    static dlinkedList.Node editElementSearchSwitch(dlinkedList list) {
        dlinkedList.Node node = null;
        switch (mainMenuInput()) {
            case 1 -> {
                node = searchMenuSwitch(1, list);
                try {
                    new dlinkedList.Node(node.data);
                } catch (Exception e) {
                    mainMenuSwitch(2, list);
                }
                return node;
            }
            case 2 -> {
                node = searchMenuSwitch(4, list);
                return node;
            }
            case 0 -> {
                Menu.mainMenuInterface();
                Input.mainMenuSwitch(Input.mainMenuInput(), list);
                return node;
            }
            default -> {
                System.out.println("The number You've chosen does not exist.");
                Menu.editElementSearchMenu();
                Input.editElementSearchSwitch(list);
            }
        }
        return node;
    }
}
