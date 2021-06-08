package com.epam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private Integer amount;

    public Salary() {

    }

    public Salary(Long employeeId, Integer amount) {
        this.employeeId = employeeId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Objects.equals(id, salary.id) && Objects.equals(employeeId, salary.employeeId) && Objects.equals(amount, salary.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, amount);
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                '}';
    }
}