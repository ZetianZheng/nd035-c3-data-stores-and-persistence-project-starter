package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /** find which employee are available at 'dayOfWeek'**/
    List<Employee> findByAvailableDaysContaining(DayOfWeek dayOfWeek);

    /** find which employee have specific skill **/
    List<Employee> findBySkillsContaining(EmployeeSkill skill);
}
