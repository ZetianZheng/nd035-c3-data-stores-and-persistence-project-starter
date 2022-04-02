package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;

import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name = "employee")
@AttributeOverrides({
        @AttributeOverride(name="id",
                            column = @Column(name="employee_id")),
        @AttributeOverride(name="name",
                column = @Column(name="employee_name"))
})
public class Employee extends User{
    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeSkill> skills;

    @Column(name = "availble_day")
    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "employee_availableDay", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<DayOfWeek> availableDays;

    /** constructor **/
    public Employee(Set<EmployeeSkill> skills, Set<DayOfWeek> availableDays) {
        this.skills = skills;
        this.availableDays = availableDays;
    }

    public Employee(Long id, String name, String notes, String phoneNumber, Set<EmployeeSkill> skills, Set<DayOfWeek> availableDays) {
        super(id, name, notes, phoneNumber);
        this.skills = skills;
        this.availableDays = availableDays;
    }

    public Employee() {
    }

    /** getter and setter **/
    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(Set<DayOfWeek> availableDays) {
        this.availableDays = availableDays;
    }
}
