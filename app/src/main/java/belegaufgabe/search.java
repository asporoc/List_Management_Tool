package belegaufgabe;

public interface search {

    dlinkedList.Node searchBySN( int d, dlinkedList list);
    dlinkedList.Node searchByName(String s, dlinkedList list);
    dlinkedList.Node searchPrice(double d, dlinkedList list);
    dlinkedList.Node searchCategory(String s, dlinkedList list);
}
