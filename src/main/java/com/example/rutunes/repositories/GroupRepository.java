package com.example.rutunes.repositories;

import com.example.rutunes.models.Group;
import com.example.rutunes.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByName(String name);
    Group findGroupByName(String name);
}
