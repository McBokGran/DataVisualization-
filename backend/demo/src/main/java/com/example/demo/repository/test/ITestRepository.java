package com.example.demo.repository.test;

import com.example.demo.model.PrintCycle;
import com.example.demo.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestRepository extends JpaRepository<Test, Long> {
    Test save(Test test);
}
