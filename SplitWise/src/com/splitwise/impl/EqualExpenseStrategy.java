package com.splitwise.impl;

import com.splitwise.ExpenseStrategy;
import com.splitwise.Formatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualExpenseStrategy implements ExpenseStrategy {

    private final List<String> users;
    private final double total;

    public EqualExpenseStrategy(List<String> users, double total) {
        this.users = users;
        this.total = total;
    }

    @Override
    public Map<String, Double> getSplitExpense(String payerId) {
        Map<String, Double> map = new HashMap<>();
        Formatter formatter = new Formatter();
        double singleExpense = formatter.formatDouble(total/users.size());

        double rem = total;
        for(int i = 1; i < users.size(); i++) {
            if(!users.get(i).equalsIgnoreCase(payerId)) {
                map.put(users.get(i), singleExpense);
            }
            rem -= singleExpense;
        }

        if(!users.get(0).equalsIgnoreCase(payerId))
            map.put(users.get(0), formatter.formatDouble(rem));

        return map;
    }
}
