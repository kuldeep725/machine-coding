package com.splitwise.impl;

import com.splitwise.ExpenseStrategy;
import com.splitwise.Formatter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentExpenseStrategy implements ExpenseStrategy {

    private List<String> users;
    private List<Integer> percentages;
    private double total;
    public PercentExpenseStrategy(List<String> users, double total, List<Integer> percentages) {
        this.users = users;
        this.total = total;
        this.percentages = percentages;
    }

    @Override
    public Map<String, Double> getSplitExpense(String payerId) {
        Map<String, Double> map = new HashMap<>();

        Formatter formatter = new Formatter();

        double sum = 0;
        for(int i = 1; i < users.size(); i++) {
            double curr = formatter.formatDouble((total * percentages.get(i))/100);
            sum += curr;
            if(!users.get(i).equalsIgnoreCase(payerId)) {
                map.put(users.get(i), curr);
            }
        }

        if(!users.get(0).equalsIgnoreCase(payerId))
            map.put(users.get(0), formatter.formatDouble(total-sum));

        return map;
    }

}
