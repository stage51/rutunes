package com.example.rutunes.services;

import com.example.rutunes.models.UserBasket;
import com.example.rutunes.models.User;
import com.example.rutunes.repositories.UserBasketRepository;
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
public class UserBasketService {
    private final UserBasketRepository userBasketRepository;
    private final UserRepository userRepository;

    public List<UserBasket> listUserBasket(String title) {
        if (title != null) return userBasketRepository.findByTitle(title);
        return userBasketRepository.findAll();
    }

    public void saveUserBasket(Principal principal, UserBasket userBasket) throws IOException {
        userBasket.setUser(getUserByPrincipal(principal));
        log.info("Saving new UserBasket. Title: {}; Author email: {}", userBasket.getAlbum().getGroup().getName(),userBasket.getUser().getEmail());
        userBasketRepository.save(userBasket);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteUserBasket(User user, Long id) {
        UserBasket userBasket = userBasketRepository.findById(id)
                .orElse(null);
        if (userBasket != null) {
            if (userBasket.getUser().getId().equals(user.getId())) {
                userBasketRepository.delete(userBasket);
                log.info("UserBasket with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this UserBasket with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("UserBasket with id = {} is not found", id);
        }    }

    public UserBasket getUserBasketById(Long id) {
        return userBasketRepository.findById(id).orElse(null);
    }
    public List<UserBasket> getUserBasketByUser(User user) {
        return userBasketRepository.findByUser(user);
    }
    public List<UserBasket> getUserBasket() {
        return userBasketRepository.findAll();
    }
}
