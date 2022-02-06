package com.splitwise;

import com.splitwise.model.ExpenseTracker;

import java.util.*;
import java.util.stream.Collectors;

public class Driver {

    private static ExpenseTracker expenseTracker = new ExpenseTracker();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Formatter formatter = new Formatter();

        while(true) {
            String command = sc.nextLine();

            if(command.equalsIgnoreCase("EXIT"))
                break;

            if(command.equalsIgnoreCase("SHOW")) {
                showAll();
            }
            else if(command.startsWith("SHOW")) {
                String userId = command.substring(4).trim();
                showUser(userId);
            }
            else if(command.startsWith("EXPENSE")) {
                String[] subCommands = command.split("\s+");

                int k = 1;
                String payerId = subCommands[k++];
                double amountPaid = formatter.formatDouble(Double.parseDouble(subCommands[k++]));
                int groupCount = Integer.parseInt(subCommands[k++]);

                List<String> groupUsers = new ArrayList<>();
                for(int i = 0; i < groupCount; i++) {
                    groupUsers.add(subCommands[k++]);
                }

                String strategyType = subCommands[k++];
                String[] values = Arrays.copyOfRange(subCommands, k, subCommands.length);
                ExpenseStrategy expenseStrategy = ExpenseStrategyFactory.getExpenseStrategy(strategyType, amountPaid, groupUsers, values);

                expenseTracker.addExpense(payerId, expenseStrategy);

                System.out.println("Expense added for user " + payerId + " for group " + groupUsers.stream().filter(userId -> !userId.equals(payerId)).collect(Collectors.toList()));
            }
        }
    }

    public static void showAll() {
        Map<String, Map<String, Double>> allUsersExpenseMap = expenseTracker.getAllUsersExpenseMap();

        if(allUsersExpenseMap.size() == 0) {
            System.out.println("No balances");
            return;
        }

        System.out.println("Expense details for all users");

        for(String userId: allUsersExpenseMap.keySet()) {
            Map<String, Double> currUserMap = allUsersExpenseMap.get(userId);
            for(String otherUserId: currUserMap.keySet()) {
                System.out.println(otherUserId + " owes " + userId + " : " + currUserMap.get(otherUserId));
            }
        }

    }

    public static void showUser(String searchId) {

        Map<String, Map<String, Double>> allUsersExpenseMap = expenseTracker.getAllUsersExpenseMap();

        if(allUsersExpenseMap == null || allUsersExpenseMap.size() == 0) {
            System.out.println("No balances");
            return;
        }

        System.out.println("Expense details for user " + searchId);

        for(String userId: allUsersExpenseMap.keySet()) {
            Map<String, Double> currUserMap = allUsersExpenseMap.get(userId);
            for(String otherUserId: currUserMap.keySet()) {
                if(userId.equalsIgnoreCase(searchId) || otherUserId.equals(searchId))
                    System.out.println(otherUserId + " owes " + userId + " : " + currUserMap.get(otherUserId));
            }
        }
    }

}
