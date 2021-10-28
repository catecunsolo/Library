package com.libraryegg.repository;

import com.libraryegg.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Integer> {
    @Modifying
    @Query("UPDATE Editorial e SET e.name = :name WHERE e.id = :id")
    void edit(@Param("id") Integer id, @Param("name") String name);
}
