package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    List<Employee> employeeList;

    @ManyToMany
    List<Pet> petList;

    private LocalDate date;

    @Column(name = "activities")
    @ElementCollection(fetch = FetchType.EAGER, targetClass = EmployeeSkill.class)
    @CollectionTable(name = "schedule_activity", joinColumns = @JoinColumn(name = "activity_id"))
    private Set<EmployeeSkill> skills;

    /** constructor **/
    public Schedule() {

    }

    public Schedule(Long id, List<Employee> employeeList, List<Pet> petList, LocalDate date, Set<EmployeeSkill> skills) {
        this.id = id;
        this.employeeList = employeeList;
        this.petList = petList;
        this.date = date;
        this.skills = skills;
    }

    /** getter and setter **/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
}
