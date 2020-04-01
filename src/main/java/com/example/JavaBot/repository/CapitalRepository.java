package com.example.JavaBot.repository;

import com.example.JavaBot.Entity.CapitalsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CapitalRepository extends JpaRepository<CapitalsInfo, Integer> {
    /**
     * Поиск по имени согласно ключевых слов Data Jpa
     * @param name
     * @return
     */
    Optional<CapitalsInfo> findByName(String name);

    /**
     * удаление из бд по имени.
     * @param name
     */
    void deleteByName(String name);

    @Modifying
    @Query("update CapitalsInfo ci set ci.name =?1, ci.description = ?2 where ci.Id = ?3")
    int update(String name, String description, int id);
}
