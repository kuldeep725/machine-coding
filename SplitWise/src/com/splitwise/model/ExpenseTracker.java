package com.splitwise.model;

import com.splitwise.ExpenseStrategy;
import com.splitwise.Formatter;

import java.util.HashMap;
import java.util.Map;

public class ExpenseTracker {

    private Map<String, Map<String, Double>> allUsersExpenseMap;

    public ExpenseTracker() {
        this.allUsersExpenseMap = new HashMap<>();
    }

    public Map<String, Map<String, Double>> getAllUsersExpenseMap() {
        return this.allUsersExpenseMap;
    }

    public Map<String, Double> getUserExpenseMap(String userId) {
        return allUsersExpenseMap.get(userId);
    }

    public void addExpense(String payerId, ExpenseStrategy expenseStrategy) {
        Map<String, Double> newExpenseMap = expenseStrategy.getSplitExpense(payerId);

        Map<String, Double> currExpenseMap;
        if(allUsersExpenseMap.containsKey(payerId)) {
            currExpenseMap = allUsersExpenseMap.get(payerId);
        } else {
            currExpenseMap = new HashMap<>();
            allUsersExpenseMap.put(payerId, currExpenseMap);
        }

        Formatter formatter = new Formatter();

        for(String userId: newExpenseMap.keySet()) {
            if(currExpenseMap.containsKey(userId)) {
                double newValue = formatter.formatDouble(currExpenseMap.get(userId) + newExpenseMap.get(userId));
                currExpenseMap.put(userId, newValue);
            } else {
                currExpenseMap.put(userId, newExpenseMap.get(userId));
            }
        }

    }

}
