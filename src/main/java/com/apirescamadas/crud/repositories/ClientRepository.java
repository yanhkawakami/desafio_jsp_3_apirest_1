package com.apirescamadas.crud.repositories;

import com.apirescamadas.crud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
