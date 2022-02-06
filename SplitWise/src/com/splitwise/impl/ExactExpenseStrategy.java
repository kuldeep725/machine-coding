package com.splitwise.impl;

import com.splitwise.ExpenseStrategy;
import com.splitwise.Formatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExactExpenseStrategy implements ExpenseStrategy {

    private final List<String> users;
    private final List<Double> expenses;
    public ExactExpenseStrategy(List<String> users, List<Double> expenses) {
        this.users = users;
        this.expenses = expenses;
    }

    @Override
    public Map<String, Double> getSplitExpense(String payerId) {
        Map<String, Double> map = new HashMap<>();

        Formatter formatter = new Formatter();

        for(int i = 0; i < users.size(); i++) {
            map.put(users.get(i), formatter.formatDouble(expenses.get(i)));
        }
        return map;
    }

}
