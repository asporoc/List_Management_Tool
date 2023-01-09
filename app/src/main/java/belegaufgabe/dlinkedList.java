package belegaufgabe;

import java.util.Objects;

public class dlinkedList implements search {
    Node head;
    Node tail;
    @Override
    public Node searchBySN(int d, dlinkedList list) {//Eintrag suche nach Seriennummer
        Node x = head;
        try {
            while (x != null && x.data.serialNumber != d) {
                x = x.next;
            }
            return x;
        } catch (Exception e) {
            System.out.print("No Item with that Serialnumber was found please try again: ");
        }
        return x;
    }
    @Override
    public Node searchByName(String s, dlinkedList list) {
        Node x = list.head;
        while (x != null && !Objects.equals(x.data.name, s)) {
            x = x.next;
        }
        return x;
    }
    @Override
    public Node searchPrice(double d, dlinkedList list) {
        Node x = head; // 1 OP
        dlinkedList searchedElements = new dlinkedList(); // 1 OP
        while (x != null) { // n
            if(x.data.price == d){ // 1 OP
               searchedElements = searchedElements.addAtEndOfList(x.data, searchedElements); // 1 OP
            }
            x = x.next; // 1 OP
        }
        if (numberOfElements(searchedElements) <= 0){ // n
            System.out.println("An item with this Price could'nt be found."); // 1 OP
        }
        printAllElements(searchedElements); // 1 OP
        return x; // 1 OP
    }
    public Node searchByPrice(double d) { // Method used in Sort Method
        Node x = head; // 1 OP
        while (x != null && x.data.price != d) { // n
            x = x.next; // 1 OP
        }
        return x; // 1 OP
    }
    @Override
    public Node searchCategory(String s, dlinkedList list) {
        Node x = head;
        dlinkedList searchedElements = new dlinkedList();
        while (x != null) {
            if(x.data.category.equals(s)){
              searchedElements.addAtEndOfList(x.data, searchedElements);
            }
            x = x.next;
        }
        printAllElements(searchedElements);
        return x;
    }
    public Node searchByCategory(String s) {//search used by sort Method
        Node x = head;
        while (x != null && !Objects.equals(x.data.category, s)) {
            x = x.next;
        }
        return x;
    }
    static class Node{
        item data;
        Node next;
        Node prev;
        Node(item d) {
            this.data = d;
        }
    }
    public dlinkedList addAtEndOfList(item newData, dlinkedList list) {//neues Element am Ende der Liste hinzufuegen
        Node newNode = new Node(newData);
        if (list.head == null) {
            list.head = newNode;
            list.tail = newNode;
            list.head.prev = null;
        } else {
            list.tail.next = newNode;
            newNode.prev = list.tail;
            list.tail = newNode;
        }
        list.tail.next = null;
        return list;
    }
    public void insertAfterNode(Node prevNode, item newData, dlinkedList list) {
        if (prevNode == null) {
            System.out.println("You can't add a Item after a particular Node if that Node does'nt exist.");
            return;
        }
        Node newNode = new Node(newData);
        newNode.next = prevNode.next;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        if (newNode.next != null) {
            newNode.next.prev = newNode; // prev von dem folge node wird veraendert
        } else {
            list.tail = newNode;
        }
    }
    public void insertBeforeNode(Node nextNode, item newData, dlinkedList list) {
        if (nextNode == null) {
            System.out.println("You Can't add an Item Before a particular Node if that Node does'nt exist.");
            return;
        }
        Node newNode = new Node(newData);
        newNode.prev = nextNode.prev;
        newNode.next = nextNode;
        nextNode.prev = newNode;
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        } else {
            list.head = newNode;
        }
    }
    public static void printAllElements(dlinkedList list) {
        if (list.head == null) {
            System.out.println("The List seems to be empty.");
            return;
        }
        Node node = list.head;
        System.out.println("_________________________________________________________________");
        System.out.printf("|%-15s|%-10s|%-15s|%-20s|%n","Serialnumber","Name","Price","Category");
        System.out.println("|---------------|----------|---------------|--------------------|");
        do {
            System.out.printf("|%-15d|%-10s|%-15f|%-20s|%n",node.data.serialNumber, node.data.name, node.data.price, node.data.category);
            node = node.next;
        } while (node != null);
    }
    public static int numberOfElements(dlinkedList list) {
        int sum = 0;
        if (list.head != null) {
            Node node = list.head;
            do {
                node = node.next;
                sum++;
            } while (node != null);
        }
        return sum;
    }
    public static void clearList(dlinkedList list) {
        Node node = list.head;
        if (list.head == null) {
            return;
        }
        list.head = null;
        while (true) {
            if (node.next != null) {
                list.head = node.next;
                list.head.prev = null;
                node.next = list.head.next;
                list.head = null;
            } else {
                break;
            }
        }
    }
    public static void removeElement(dlinkedList list, Node del) {
        if (del.prev != null) {
            del.prev.next = del.next;
        } else list.head = del.next;
        if (del.next != null) {
            del.next.prev = del.prev;
        }
    }
    static Node pivot;
    static dlinkedList sortedList = new dlinkedList();
    public static dlinkedList quicksortDouble(dlinkedList list, String toSort) {
        dlinkedList smaller = new dlinkedList(); // 1 OP
        dlinkedList greater = new dlinkedList(); // 1 OP
        Node y = list.head; // 1 OP
        pivot = list.tail; // 1 OP
        double yData = 0; // 1 OP
        double pivotData = 0; // 1 OP
        if (pivot == null) {
            return sortedList; // 1 OP
        } else {
            if (numberOfElements(sortedList) == 0) {//n+1 OP
                sortedList.addAtEndOfList(pivot.data, sortedList);//1 OP
            }
            while (y.next != null) { // n OP
                if(Objects.equals(toSort, "price")){// 1 OP
                    yData = y.data.price; // 1 OP
                    pivotData = pivot.data.price; // 1 OP
                } else if(Objects.equals(toSort, "serialnumber")) { // 1 OP
                    yData = y.data.serialNumber;// 1 OP
                    pivotData = pivot.data.serialNumber; // 1 OP
                }
                if (yData <= pivotData) { // 1 OP
                    smaller.addAtEndOfList(y.data, smaller);// 1 OP
                    y = y.next;// 1 OP
                } else {// 1 OP
                    greater.addAtEndOfList(y.data, greater); // 1 OP
                    y = y.next;// 1 OP
                }
            }
            //Inserting pivot into sortedList until both smaller and greater list are empty
            if (numberOfElements(greater) != 0 && Objects.equals(toSort, "price")) {// n + 1 OP
                sortedList.insertAfterNode(sortedList.searchByPrice(pivotData), greater.tail.data, sortedList); // n + n
            }else if(numberOfElements(greater) != 0 && Objects.equals(toSort, "serialnumber")){// n + 1 OP
                sortedList.insertAfterNode(sortedList.searchBySN(pivot.data.serialNumber, sortedList), greater.tail.data, sortedList);// n + n
            }
            if (numberOfElements(smaller) != 0 && Objects.equals(toSort, "price")) {// n + 1 OP
                sortedList.insertBeforeNode(sortedList.searchByPrice(pivotData), smaller.tail.data, sortedList);// n + n
            }else if(numberOfElements(smaller) != 0 && Objects.equals(toSort, "serialnumber")){// n + 1 OP
                sortedList.insertBeforeNode(sortedList.searchBySN(pivot.data.serialNumber, sortedList), smaller.tail.data, sortedList); // n + n
            }
            if (numberOfElements(smaller) == 0 && numberOfElements(greater) == 0){ //n + n
                return sortedList;
            } else {
                quicksortDouble(smaller,toSort); // n
                quicksortDouble(greater,toSort); // n
            }
        }
        return sortedList; // 1 OP
    }
    public static dlinkedList quicksortString(dlinkedList list, String toSort) {
        dlinkedList smaller = new dlinkedList();
        dlinkedList greater = new dlinkedList();
        Node y = list.head;
        pivot = list.tail;
        String yData = "";
        String pivotData = "";
        if (pivot == null) {
            return sortedList;
        } else {
            if (numberOfElements(sortedList) == 0) {
                sortedList.addAtEndOfList(pivot.data, sortedList);
            }
            while (y.next != null) {
                if(Objects.equals(toSort, "name")){
                yData = y.data.name;
                pivotData = pivot.data.name;
                } else if(Objects.equals(toSort, "category")) {
                    yData = y.data.category;
                    pivotData = pivot.data.category;
                }
                if (yData.compareToIgnoreCase(pivotData) <= 0) {
                    smaller.addAtEndOfList(y.data, smaller);
                    y = y.next;
                } else if (yData.compareToIgnoreCase(pivotData) > 0) {
                    greater.addAtEndOfList(y.data, greater);
                    y = y.next;
                }
            }
            //Inserting pivot into sortedList until both smaller and greater list are empty
            if (numberOfElements(greater) != 0 && Objects.equals(toSort, "name")) {
                sortedList.insertAfterNode(sortedList.searchByName(pivotData, sortedList), greater.tail.data, sortedList);
            }else if(numberOfElements(greater) != 0 && Objects.equals(toSort, "category")){
                sortedList.insertAfterNode(sortedList.searchByCategory(pivot.data.category), greater.tail.data, sortedList);
            }
            if (numberOfElements(smaller) != 0 && Objects.equals(toSort, "name")) {
                sortedList.insertBeforeNode(sortedList.searchByName(pivotData, sortedList), smaller.tail.data, sortedList);
            }else if(numberOfElements(smaller) != 0 && Objects.equals(toSort, "category")){
                sortedList.insertBeforeNode(sortedList.searchByCategory(pivot.data.category), smaller.tail.data, sortedList);
            }
            if (numberOfElements(smaller) == 0 && numberOfElements(greater) == 0) {
                return sortedList;
            } else {
                quicksortString(smaller,toSort);
                quicksortString(greater,toSort);
            }
        }
        return sortedList;
    }
    public static item addElement(dlinkedList list) {

        int Serialnumber = 0;
        double price = 0;
        System.out.print("Please enter a Name:");
        String name = Input.subMenuInputString();
        if(list.searchByName(name, list) != null){
            System.out.println("An item with this Name already exists please choose another name!");
            Input.mainMenuSwitch(1, Input.list);
        }
        System.out.print("Please enter a Category:");
        String category = Input.subMenuInputString();
        System.out.print("Please enter a Price:");

        try{
            price = Double.parseDouble(Input.subMenuInputString());
            if(price < 0){
                System.out.println("Negative Prices are not allowed, please try again");
                Input.mainMenuSwitch(1, Input.list);
            }
        } catch (Exception e) {
            System.out.println("You'r entry for price was not valid, please try again");
            Input.mainMenuSwitch(1, Input.list);
        }
        System.out.print("Please enter a Serialnumber:");
        try {
            Serialnumber = Integer.parseInt(Input.subMenuInputString());
            if(list.searchBySN(Serialnumber, list) != null){
                System.out.println("An item with this Serialnumber already exists please choose another Serialnumber");
                Input.mainMenuSwitch(1, Input.list);
            }
            if(Serialnumber < 0){
                System.out.println("You'r entry for Serialnumber was not valid, please try again");
                Input.mainMenuSwitch(1, Input.list);
            }
        } catch (Exception e) {
            System.out.println("You'r entry for Serialnumber was not valid, please try again");
            Input.mainMenuSwitch(1, Input.list);
        }
        return new item(Serialnumber, price, name, category);
    }
    public static void nodeToString(Node node){
        System.out.println("_________________________________________________________________");
        System.out.printf("|%-15s|%-10s|%-15s|%-20s|%n","Serialnumber","Name","Price","Category");
        System.out.println("|---------------|----------|---------------|--------------------|");
        System.out.printf("|%-15d|%-10s|%-15f|%-20s|%n",node.data.serialNumber, node.data.name, node.data.price, node.data.category);
    }
    final static dlinkedList empty = new dlinkedList();
    public static void priceFilter(dlinkedList list, double lowerLimit, double upperLimit){
        dlinkedList filteredList = new dlinkedList();
        Node x = list.head;
        if (upperLimit == 0){
            Node y = list.head;
            Node max = y;
            while(y.next != null){
                y = y.next;
                if (y.data.price > max.data.price){
                    max.data.price = y.data.price;
                }
            }
            upperLimit = max.data.price;
        }
        while (x != null) {
            if(lowerLimit < x.data.price && x.data.price <= upperLimit){
                filteredList.addAtEndOfList(x.data, filteredList);
            }
            x = x.next;
        }
        dlinkedList printable = quicksortDouble(filteredList, "price");
        printAllElements(printable);
        sortedList = empty;
    }


    }
