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
    @ElementCollection(fetch = FetchType.EAGER, targetClass = EmployeeSkill.class)
    @CollectionTable(name = "employee_skill", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<EmployeeSkill> skills;

    @Column(name = "available_day")
    @ElementCollection(fetch = FetchType.EAGER, targetClass = DayOfWeek.class)
    @CollectionTable(name = "employee_availableDay", joinColumns = @JoinColumn(name = "employee_id"))
    private Set<DayOfWeek> daysAvailable;

    /** constructor **/
    public Employee(Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Employee(Long id, String name, String notes, String phoneNumber, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        super(id, name, notes, phoneNumber);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
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

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
