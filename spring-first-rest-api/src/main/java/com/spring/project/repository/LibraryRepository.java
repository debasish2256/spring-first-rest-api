package com.spring.project.repository;

import java.util.Optional;

import com.spring.project.dto.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findByLibraryName(String libraryName);
}
	