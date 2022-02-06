package com.splitwise.impl;

import com.splitwise.ExpenseStrategy;

import java.util.HashMap;
import java.util.Map;

public class NullExpenseStrategy implements ExpenseStrategy {
    @Override
    public Map<String, Double> getSplitExpense(String payerId) {
        return new HashMap<>();
    }
}
