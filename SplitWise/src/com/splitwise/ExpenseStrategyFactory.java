package com.splitwise;

import com.splitwise.impl.EqualExpenseStrategy;
import com.splitwise.impl.ExactExpenseStrategy;
import com.splitwise.impl.NullExpenseStrategy;
import com.splitwise.impl.PercentExpenseStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseStrategyFactory {

    public static ExpenseStrategy getExpenseStrategy(String strategyName, double amountPaid, List<String> groupUsers, String[] values) {
        if(strategyName.equalsIgnoreCase("EQUAL")) {
            return new EqualExpenseStrategy(groupUsers, amountPaid);
        }

        if(strategyName.equalsIgnoreCase("EXACT")) {
            return new ExactExpenseStrategy(groupUsers, Arrays.stream(values).map(Double::parseDouble).collect(Collectors.toList()));
        }

        if(strategyName.equalsIgnoreCase("PERCENT")) {
            return new PercentExpenseStrategy(groupUsers, amountPaid, Arrays.stream(values).map(Integer::parseInt).collect(Collectors.toList()));
        }

        return new NullExpenseStrategy();
    }
}
