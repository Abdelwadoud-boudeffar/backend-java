package com.sqli.suisse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ABDELWADOUD BOUDEFFAR
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UserViewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    // TODO
//	public static UserViewDTO of(User user) {
//		return UserMapper.INSTANCE.userToUserViewDto(user);
//	}
}
