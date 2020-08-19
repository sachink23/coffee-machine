package machine;
import java.util.Scanner;
public class CoffeeMachine {
    private static Scanner scanner;
    private static int availableWater = 400;
    private static int availableMilk = 540;
    private static int availableBeans = 120;
    private static int availableCups = 9;
    private static int availableMoney = 550;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.next();
            switch (action) {
                case "buy":
                    performBuyAction();
                    break;

                case "fill":
                    performFillAction();
                    break;

                case "take":
                    performTakeAction();
                    break;
                case "remaining":
                    displayAvailableSupplies();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Sorry, I didn't understand\n");
                    break;
            }
        }
        while (!exit);
    }

    private static void displayAvailableSupplies() {
        System.out.println("The coffee machine has:");
        System.out.println(getAvailableWater() + " ml of water");
        System.out.println(getAvailableMilk() + " ml of milk");
        System.out.println(getAvailableBeans() + " gm of coffee beans");
        System.out.println(getAvailableCups() + " disposable cup/s");
        System.out.println("₹" + getAvailableMoney() + " money\n");
    }

    public static int getAvailableWater() {
        return availableWater;
    }

    public static void setAvailableWater(int availableWater) {
        CoffeeMachine.availableWater = availableWater;
    }

    public static int getAvailableMilk() {
        return availableMilk;
    }

    public static void setAvailableMilk(int availableMilk) {
        CoffeeMachine.availableMilk = availableMilk;
    }

    public static int getAvailableBeans() {
        return availableBeans;
    }

    public static void setAvailableBeans(int availableBeans) {
        CoffeeMachine.availableBeans = availableBeans;
    }

    public static int getAvailableCups() {
        return availableCups;
    }

    public static void setAvailableCups(int availableCups) {
        CoffeeMachine.availableCups = availableCups;
    }
    public static int getAvailableMoney() {
        return availableMoney;
    }

    public static void setAvailableMoney(int availableMoney) {
        CoffeeMachine.availableMoney = availableMoney;
    }
    private static boolean performBuyAction() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int whatCustomerNeeds;
        try {
            whatCustomerNeeds = scanner.nextInt();
        } catch (Exception e) {
            whatCustomerNeeds = 0;
        }

        boolean status = false;
        if(getAvailableCups() > 0) {
            switch (whatCustomerNeeds) {
                case 1:
                    status = makeEspresso();
                    break;
                case 2:
                    status = makeLatte();
                    break;
                case 3:
                    status = makeCappuccino();
                    break;
                default:
                    System.out.println("Sorry, I didn't understand\n");
                    break;
            }
        }
        return status;
    }

    private static boolean makeCappuccino() {
        int requiredWater = 200;
        int requiredBeans = 12;
        int requiredMilk = 100;
        int addsAmount = 6;
        return makeBeverage(requiredWater, requiredBeans, requiredMilk, addsAmount);
    }
    private static boolean makeLatte() {
        int requiredWater = 350;
        int requiredBeans = 20;
        int requiredMilk = 75;
        int addsAmount = 7;
        return makeBeverage(requiredWater, requiredBeans, requiredMilk, addsAmount);
    }

    private static boolean makeEspresso() {
        int requiredWater = 250;
        int requiredBeans = 16;
        int requiredMilk = 0;
        int addsAmount = 4;
        return makeBeverage(requiredWater, requiredBeans, requiredMilk, addsAmount);
    }

    private static boolean makeBeverage(int requiredWater, int requiredBeans, int requiredMilk, int addsAmount) {
        boolean canMakeCoffee = true;
        if (getAvailableWater() < requiredWater) {
            System.out.println("Sorry, not enough water!");
            canMakeCoffee = false;
        }
        if (getAvailableMilk() < requiredMilk) {
            System.out.println("Sorry, not enough milk!\n");
            canMakeCoffee = false;
        }
        if (getAvailableBeans() < requiredBeans) {
            System.out.println("Sorry, not enough coffee beans!\n");
            canMakeCoffee = false;
        }
        if (getAvailableCups() < 1) {
            System.out.println("Sorry, not enough cups!\n");
            canMakeCoffee = false;
        }

        if (canMakeCoffee) {
            System.out.println("I have enough resources, making you a coffee!");
            System.out.println("|_P\n");
            setAvailableMoney(getAvailableMoney() + addsAmount);
            setAvailableCups(getAvailableCups() - 1);
            setAvailableBeans(getAvailableBeans() - requiredBeans);
            setAvailableWater(getAvailableWater() - requiredWater);
            setAvailableMilk(getAvailableMilk() - requiredMilk);
            return true;
        }
        return false;
    }



    private static void performFillAction() {
        System.out.println("Write how many ml of water do you want to add:");
        setAvailableWater(getAvailableWater() + scanner.nextInt());
        System.out.println("Write how many ml of milk do you want to add:");
        setAvailableMilk(getAvailableMilk() + scanner.nextInt());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        setAvailableBeans(getAvailableBeans() + scanner.nextInt());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        setAvailableCups(getAvailableCups() + scanner.nextInt());

    }
    private static void performTakeAction() {
        int disbursedCash = getAvailableMoney();
        setAvailableMoney(0);
        System.out.println("I gave you $" + disbursedCash + "\n");
    }
}
