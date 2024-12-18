import java.util.Scanner;

class Coffee {
    String name;
    int waterRequired;
    int milkRequired;
    int coffeeBeansRequired;
    int cost;

    Coffee(String name, int waterRequired, int milkRequired, int coffeeBeansRequired, int cost) {
        this.name = name;
        this.waterRequired = waterRequired;
        this.milkRequired = milkRequired;
        this.coffeeBeansRequired = coffeeBeansRequired;
        this.cost = cost;
    }
}

class CoffeeMachine {
    int water = 400;
    int milk = 540;
    int coffeeBeans = 120;
    int cups = 9;
    int money = 550;
    int coffeesMade = 0;
    boolean needsCleaning = false;
    Scanner scanner = new Scanner(System.in);

    Coffee espresso = new Coffee("espresso", 250, 0, 16, 4);
    Coffee latte = new Coffee("latte", 350, 75, 20, 7);
    Coffee cappuccino = new Coffee("cappuccino", 200, 100, 12, 6);

    public void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    public void buy() {
        if (coffeesMade >= 10) {
            System.out.println("I need cleaning!");
            return;
        }

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = scanner.nextLine();

        if (input.equals("back")) {
            return;
        }

        int coffeeType = Integer.parseInt(input);
        Coffee selectedCoffee = null;

        switch (coffeeType) {
            case 1: selectedCoffee = espresso; break;
            case 2: selectedCoffee = latte; break;
            case 3: selectedCoffee = cappuccino; break;
            default:
                System.out.println("Invalid selection.");
                return;
        }

        if (water < selectedCoffee.waterRequired) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < selectedCoffee.milkRequired) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffeeBeans < selectedCoffee.coffeeBeansRequired) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups <= 0) {
            System.out.println("Sorry, not enough cups!");
        } else {
            water -= selectedCoffee.waterRequired;
            milk -= selectedCoffee.milkRequired;
            coffeeBeans -= selectedCoffee.coffeeBeansRequired;
            cups--;
            money += selectedCoffee.cost;
            coffeesMade++;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    public void fill() {
        System.out.println("Write how many ml of water you want to add:");
        water += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many ml of milk you want to add:");
        milk += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many disposable cups you want to add:");
        cups += Integer.parseInt(scanner.nextLine());
    }

    public void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public void clean() {
        coffeesMade = 0;
        needsCleaning = false;
        System.out.println("I have been cleaned!");
    }
}

public class CoffeeMachineApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String action = scanner.nextLine();

            switch (action) {
                case "buy":
                    coffeeMachine.buy();
                    break;
                case "fill":
                    coffeeMachine.fill();
                    break;
                case "take":
                    coffeeMachine.take();
                    break;
                case "clean":
                    coffeeMachine.clean();
                    break;
                case "remaining":
                    coffeeMachine.remaining();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }
}


/**
 * package machine;
 * import java.util.Scanner;
 * import java.lang.Math;
 *
 * public class CoffeeMachine {
 *     final static private Scanner s = new Scanner(System.in);
 *     private static int water = 400, milk = 540, coffee = 120;
 *     private static int cups = 9, money = 550;
 *
 *     public static void PrintMachine() {
 *         System.out.printf("The coffee machine has:\n%d ml of water\n%d ml of milk\n", water, milk);
 *         System.out.printf("%d g of coffee beans\n%d disposable cups\n%d of money\n", coffee, cups, money);
 *     }
 *
 *     public static void take() {
 *         System.out.printf("I gave you $%d\n", money);
 *         money = 0;
 *     }
 *
 *     public static void fill() {
 *         System.out.println("Write how many ml of water you want to add:");
 *         water += s.nextInt();
 *         s.nextLine();  // Consume the newline
 *
 *         System.out.println("Write how many ml of milk you want to add:");
 *         milk += s.nextInt();
 *         s.nextLine();  // Consume the newline
 *
 *         System.out.println("Write how many grams of coffee beans you want to add:");
 *         coffee += s.nextInt();
 *         s.nextLine();  // Consume the newline
 *
 *         System.out.println("Write how many disposable cups of coffee you want to add:");
 *         cups += s.nextInt();
 *         s.nextLine();  // Consume the newline
 *     }
 *
 *     public static void buy() {
 *         System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, or type 'back' to cancel:");
 *
 *         String input = s.nextLine();  // Read the user's input as a string
 *         if (input.equals("back")) {
 *             return;  // Exit the buy method if the user types 'back'
 *         }
 *
 *         try {
 *             int type = Integer.parseInt(input);  // Convert the input to an integer
 *             // Check if there are enough resources to make the selected coffee
 *             if (type == 1 && water >= 250 && coffee >= 16 && cups > 0) {
 *                 water -= 250;
 *                 coffee -= 16;
 *                 money += 4;
 *                 cups--;
 *             } else if (type == 2 && water >= 350 && milk >= 75 && coffee >= 20 && cups > 0) {
 *                 water -= 350;
 *                 milk -= 75;
 *                 coffee -= 20;
 *                 money += 7;
 *                 cups--;
 *             } else if (type == 3 && water >= 200 && milk >= 100 && coffee >= 12 && cups > 0) {
 *                 water -= 200;
 *                 milk -= 100;
 *                 coffee -= 12;
 *                 money += 6;
 *                 cups--;
 *             } else {
 *                 System.out.println("Sorry, not enough resources to make the selected coffee.");
 *             }
 *         } catch (NumberFormatException e) {
 *             System.out.println("Invalid input, please enter a valid number or 'back' to cancel.");
 *         }
 *     }
 *
 *     public static void main(String[] args) {
 *         while (true) {
 *             System.out.print("\nWrite action (buy, fill, take, remaining, exit): ");
 *             String action = s.nextLine();  // Read user input
 *
 *             if (action.equals("exit")) {
 *                 System.out.println("Turning off the coffee machine...");
 *                 break;  // Exit the loop and terminate the program
 *             }
 *
 *             switch (action) {
 *                 case "buy":
 *                     buy();
 *                     break;
 *                 case "fill":
 *                     fill();
 *                     break;
 *                 case "take":
 *                     take();
 *                     break;
 *                 case "remaining":
 *                     PrintMachine();  // Show current state of the machine
 *                     break;
 *                 default:
 *                     System.out.println("Error! Invalid action.");
 *                     break;
 *             }
 *         }
 *     }
 * }
 * public static void buy() {
 *     System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, or type 'back' to cancel:");
 *     String input = s.nextLine();  // Read input as a string
 *
 *     if (input.equals("back")) {
 *         return;  // Exit the buy method if 'back' is entered
 *     }
 *
 *     try {
 *         int type = Integer.parseInt(input);  // Parse the input to an integer (coffee type)
 *
 *         // Check if there are enough resources to make the selected coffee
 *         if (type == 1) {
 *             if (water < 250) {
 *                 System.out.println("Sorry, not enough water!");
 *             } else if (coffee < 16) {
 *                 System.out.println("Sorry, not enough coffee beans!");
 *             } else if (cups <= 0) {
 *                 System.out.println("Sorry, not enough cups!");
 *             } else {
 *                 water -= 250;
 *                 coffee -= 16;
 *                 money += 4;
 *                 cups--;
 *                 System.out.println("I have enough resources, making you a coffee!");
 *             }
 *         } else if (type == 2) {
 *             if (water < 350) {
 *                 System.out.println("Sorry, not enough water!");
 *             } else if (milk < 75) {
 *                 System.out.println("Sorry, not enough milk!");
 *             } else if (coffee < 20) {
 *                 System.out.println("Sorry, not enough coffee beans!");
 *             } else if (cups <= 0) {
 *                 System.out.println("Sorry, not enough cups!");
 *             } else {
 *                 water -= 350;
 *                 milk -= 75;
 *                 coffee -= 20;
 *                 money += 7;
 *                 cups--;
 *                 System.out.println("I have enough resources, making you a coffee!");
 *             }
 *         } else if (type == 3) {
 *             if (water < 200) {
 *                 System.out.println("Sorry, not enough water!");
 *             } else if (milk < 100) {
 *                 System.out.println("Sorry, not enough milk!");
 *             } else if (coffee < 12) {
 *                 System.out.println("Sorry, not enough coffee beans!");
 *             } else if (cups <= 0) {
 *                 System.out.println("Sorry, not enough cups!");
 *             } else {
 *                 water -= 200;
 *                 milk -= 100;
 *                 coffee -= 12;
 *                 money += 6;
 *                 cups--;
 *                 System.out.println("I have enough resources, making you a coffee!");
 *             }
 *         } else {
 *             System.out.println("Invalid option, please select 1, 2, or 3.");
 *         }
 *     } catch (NumberFormatException e) {
 *         System.out.println("Invalid input, please enter a valid number or 'back' to cancel.");
 *     }
 * }
 */

