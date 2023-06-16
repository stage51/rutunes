package com.example.rutunes.services;

import com.example.rutunes.models.Group;
import com.example.rutunes.models.User;
import com.example.rutunes.repositories.GroupRepository;
import com.example.rutunes.repositories.GroupRepository;
import com.example.rutunes.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public List<Group> listGroups(String title) {
        if (title != null) return groupRepository.findByName(title);
        return groupRepository.findAll();
    }

    public void saveGroup(Principal principal, Group group) throws IOException {
        group.setUser(getUserByPrincipal(principal));
        log.info("Saving new Group. Title: {}; Author email: {}", group.getName(),group.getUser().getEmail());
        groupRepository.save(group);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteGroup(User user, Long id) {
        Group group = groupRepository.findById(id)
                .orElse(null);
        if (group != null) {
            if (group.getUser().getId().equals(user.getId())) {
                groupRepository.delete(group);
                log.info("Group with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this group with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Group with id = {} is not found", id);
        }    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
    public Group getGroupByName(String name){return groupRepository.findGroupByName(name);}
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
}
