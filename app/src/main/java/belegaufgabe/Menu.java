package belegaufgabe;

public class Menu {
    static void mainMenuInterface() {
        System.out.println("*****************************************************");
        System.out.println("**                                                 **");
        System.out.println("**                                                 **");
        System.out.println("**             Furniture List Manager              **");
        System.out.println("**                                                 **");
        System.out.println("*****************************************************");
        System.out.println("Furniture List Manager Main Menu");
        System.out.println(" 1. Add Furniture Item");
        System.out.println(" 2. Edit Furniture Item");
        System.out.println(" 3. Remove Furniture Item");
        System.out.println(" 4. Search for Furniture");
        System.out.println(" 5. Sort Furniture List");
        System.out.println(" 6. Print Furniture List");
        System.out.println(" 7. Clear Furniture List");
        System.out.println(" 8. Show List Size");
        System.out.println(" 9. Filter furniture by Price");
        System.out.println("\n\n 0. Quit Furniture List Manager");
    }
    static void searchMenu(){
        System.out.println("Which Search Operation would you like to perform?");
        System.out.println(" 1. Search by Name");
        System.out.println(" 2. Search by Price");
        System.out.println(" 3. Search by Category");
        System.out.println(" 4. Search by Serialnumber");
        System.out.println("\n\n 0. Return to main Menu");
    }
    static void sortMenu(){
        System.out.println("By which feature would you like your list sorted:");
        System.out.println("!!!Sorting is always done in order from smallest to largest!!!");
        System.out.println(" 1. Sort by Name");
        System.out.println(" 2. Sort by Price");
        System.out.println(" 3. Sort by Category");
        System.out.println(" 4. Sort by Serialnumber");
        System.out.println("\n\n 0. Return to main Menu");
    }
    static void sortSubMenu(){
        System.out.print("\nThe items have been sorted would you like to print the sorted list? [Y/N]");
    }
    static void removeElementMenu(){
        System.out.println("Please identify the item you would like to remove from the List:");
        System.out.println(" 1. By Name");
        System.out.println(" 2. By Serialnumber");
        System.out.println("\n\n 0. Return to main Menu");
    }
    static void editElementSearchMenu(){
        System.out.println("By which feature would you like to find the Item to Edit?");
        System.out.println(" 1. By Name");
        System.out.println(" 2. By Serialnumber");
        System.out.println("\n\n 0. Return to main Menu");
    }
    static void editElementSubMenu(){
        System.out.println("Which attribute of the Items would you like to change?");
        System.out.println(" 1. Price");
        System.out.println(" 2. Name");
        System.out.println(" 3. Category");
        System.out.println(" 4. Serialnumber");
        System.out.println("\n\n 0. Return to main Menu");
    }
}
