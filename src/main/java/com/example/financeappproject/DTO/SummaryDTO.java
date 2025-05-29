package com.example.financeappproject.DTO;

public class SummaryDTO {
    private double totalIncome;
    private double totalExpense;
    private double balance;

    public SummaryDTO(double totalIncome, double totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = totalIncome - totalExpense;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getBalance() {
        return balance;
    }
}
