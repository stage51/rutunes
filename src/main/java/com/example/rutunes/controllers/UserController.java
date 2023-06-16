package com.example.rutunes.controllers;

import com.example.rutunes.configurations.SecurityConfig;
import com.example.rutunes.models.Group;
import com.example.rutunes.models.User;
import com.example.rutunes.services.GroupService;
import com.example.rutunes.services.PasswordService;
import com.example.rutunes.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordService passwordService;
    private final GroupService groupService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("albums", user.getAlbums());
        return "user-info";
    }
    @GetMapping("/balance")
    public String balance(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "balance";
    }
    @PostMapping("/balance")
    public String setBalance(@RequestParam("balance") String balance, Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        user.setBalance(user.getBalance() + Integer.parseInt(balance));
        userService.updateUser(user);
        return "redirect:/balance";
    }
    @GetMapping("/profile/change")
    public String changeProfile(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("groups", groupService.listGroups(null));
        return "change-profile";
    }
    @PostMapping("/profile/change/name")
    public String changeName(@RequestParam("name") String name,
                             Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if (userService.isNameExist(name)) {
            model.addAttribute("errorMessage", "Пользователь с именем: " + name + " уже существует");
            return "change-profile";
        }
        if(name != null){user.setName(name);}
        userService.updateUser(user);
        return "redirect:/login";
    }
    @PostMapping("/profile/change/email")
    public String changeEmail(@RequestParam("email") String email,
                             Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if (userService.isEmailExist(email)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + email + " уже существует");
            return "change-profile";
        }
        if(email != null){user.setEmail(email);}
        userService.updateUser(user);
        return "redirect:/login";
    }
    @PostMapping("/profile/change/phone")
    public String changePhone(@RequestParam("phoneNumber") String phoneNumber,
                              //@RequestParam("password") String password,
                              Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if(phoneNumber != null){user.setPhoneNumber(phoneNumber);}
        userService.updateUser(user);
        return "redirect:/login";
    }
    @PostMapping("/profile/change/password")
    public String changePassword(@RequestParam("password") String password,
                              Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if(password != null){passwordService.changePassword(user, password);}
        userService.updateUser(user);
        return "redirect:/login";
    }
    @PostMapping("/profile/change/address")
    public String changeAddress(@RequestParam("address") String address,
                                 Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if(address != null){user.setAddress(address);}
        userService.updateUser(user);
        return "redirect:/profile";
    }
    @PostMapping("/profile/change/group")
    public String changeGroup(@RequestParam("group") Long id,
                                Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        if(id != null){user.setGroup(groupService.getGroupById(id));}
        userService.updateUser(user);
        return "redirect:/profile";
    }
    @PostMapping("/profile/change/avatar")
    public String changeAddress(@RequestParam("file") MultipartFile file,
                                Principal principal, Model model) throws IOException {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        userService.changeAvatar(user, file);
        return "redirect:/profile";
    }
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @GetMapping("/create/group")
    public String newGroup(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "create-group";
    }
    @PreAuthorize("hasAuthority('ROLE_ARTIST')")
    @PostMapping("/create/group")
    public String changeGroup(Principal principal, Model model, Group group) throws IOException {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        groupService.saveGroup(principal, group);
        return "redirect:/create/group";
    }
}
