package org.cst.TransactionManagment.repository;

import org.cst.TransactionManagment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findAllByRollNo(int rollNo);
    boolean deleteByRollNo(int rollNo);
}
