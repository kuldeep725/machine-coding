package com.splitwise;

import java.util.Map;

public interface ExpenseStrategy {

    Map<String, Double> getSplitExpense(String payerId);

}
