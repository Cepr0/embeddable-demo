package io.github.cepr0.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentRepo extends JpaRepository<Parent, UUID> {
}
