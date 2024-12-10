package org.example.facebook.repository;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.facebook.dto.UserDTO;
import org.example.facebook.model.Conversation;
import org.example.facebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "select u.* from User as u where (:username = '' or :username is null or (u.username = :username))", nativeQuery = true)
    User showUsername(@Param("username") String username);

    @Query(value = "select u.* from User as u where (:email = '' or :email is null or (u.email = :email))", nativeQuery = true)
    User show(@Param("email") String email);
    @Query(value = "select new org.example.facebook.dto.UserDTO(u.id, u.avtUrl, u.username, u.email, u.nickname, u.phoneNumber, u.story, u.gender, u.quantityFriend) from User as u  where u.email = :email")
    UserDTO ViewAndEdit(@Param("email") String email);
    @Query(value = "select new org.example.facebook.dto.UserDTO(u.id, u.avtUrl, u.username) from User as u where u.email <> :email")
    List<UserDTO> listFollow(@Param("email") String email);

    @Query("SELECT cu.toUser.id, cu.toUser.username, cu.toUser.avtUrl FROM ConversationUser cu WHERE cu.conversation = :conversation AND cu.fromUser <> :fromUser")
    User findToUserByConversation(Conversation conversation, User fromUser);
    @Query(value = "select new org.example.facebook.dto.UserDTO(u.id, u.avtUrl, u.username) from User as u where u.email <> :email")
    List<UserDTO> findByToUser(@Param("email") String email);

    User findByEmail(String email);
}
