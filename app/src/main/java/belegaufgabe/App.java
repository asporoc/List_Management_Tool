/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package belegaufgabe;

public class App {
    public static void main(String[] args) {

        dlinkedList dList=Operations.fillList();
        Menu.mainMenuInterface();
        Input.mainMenuSwitch(Input.mainMenuInput(), dList);
    }
}
