package com.example.springboot.modules.logs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<LogsEntity, Long> {

}
