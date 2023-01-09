package belegaufgabe;

public class Operations {
    public static dlinkedList fillList(){
        item test = new item(12312, 299.00, "Hurtig", "Wardrobe");
        item test1 = new item(21112, 600.89, "Smoergos", "Table");
        item test2 = new item(55662, 89.99, "Grantig", "Chair");
        item test3 = new item(32113,20.99,"Lightly","Table Lamp");
        item test4 = new item(13211,100.00,"Leek","Wardrobe");
        item test5 = new item(32398,359.99,"Jare","Coffee Table");
        item test6 = new item(33213,429.99,"Maechtig","Table");
        dlinkedList dList = new dlinkedList();
        dList.addAtEndOfList(test, dList);
        dList.addAtEndOfList(test1, dList);
        dList.addAtEndOfList(test3, dList);
        dList.addAtEndOfList(test4, dList);
        dList.addAtEndOfList(test5, dList);
        dList.addAtEndOfList(test6, dList);
        dList.insertBeforeNode(dList.head,test2, dList);

        return dList;
    }
}
