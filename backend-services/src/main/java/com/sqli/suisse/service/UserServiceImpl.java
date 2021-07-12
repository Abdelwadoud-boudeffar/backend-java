package com.sqli.suisse.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sqli.suisse.dao.model.User;
import com.sqli.suisse.dao.repository.UserRepository;
import com.sqli.suisse.dto.UserCreateDTO;
import com.sqli.suisse.dto.UserUpdateDTO;
import com.sqli.suisse.dto.UserViewDTO;
import com.sqli.suisse.exception.NotFoundException;
import com.sqli.suisse.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author ABDELWADOUD BOUDEFFAR
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserViewDTO getUserById(Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));
        return UserMapper.INSTANCE.userToUserViewDto(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserViewDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> UserMapper.INSTANCE.userToUserViewDto(user)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserViewDTO createUser(UserCreateDTO userCreateDTO) {
        final User user = userRepository
                .save(new User(userCreateDTO.getUserName(), userCreateDTO.getFirstName(), userCreateDTO.getLastName()));
        return UserMapper.INSTANCE.userToUserViewDto(user);
    }

    @Override
    @Transactional
    public UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());

        final User updatedUser = userRepository.save(user);

        return UserMapper.INSTANCE.userToUserViewDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Exception"));
        userRepository.deleteById(user.getId());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<UserViewDTO> slice(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(user -> UserMapper.INSTANCE.userToUserViewDto(user)).collect(Collectors.toList());
    }

}
