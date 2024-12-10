package org.example.facebook.repository;

import org.example.facebook.dto.ConversationDTO;
import org.example.facebook.dto.ShowConversationDTO;
import org.example.facebook.dto.UserDTO;
import org.example.facebook.model.Conversation;
import org.example.facebook.model.ConversationUser;
import org.example.facebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IConversationUserRepository extends JpaRepository<ConversationUser, Integer> {

    @Query(value = "select new org.example.facebook.dto.ShowConversationDTO(cu.id, cu.content, cu.createAt, cu.fromUser.id, cu.fromUser.avtUrl, cu.fromUser.username, cu.toUser.id, cu.toUser.avtUrl, cu.toUser.username) from ConversationUser cu left join Conversation c on cu.conversation.id = c.id where cu.conversation.id = :conversationId")
    List<ShowConversationDTO> showConversation(@Param("conversationId") Integer conversationId);
    @Query(value = "select new org.example.facebook.dto.ShowConversationDTO(cu.id, cu.content, cu.createAt, cu.fromUser.id, cu.fromUser.avtUrl, cu.fromUser.username, cu.toUser.id, cu.toUser.avtUrl, cu.toUser.username) from ConversationUser cu where (cu.fromUser = :fromUser AND cu.toUser = :toUser) OR (cu.fromUser = :toUser AND cu.toUser = :fromUser) ORDER BY cu.createAt ASC")
    List<ShowConversationDTO> showConversation2(@Param("fromUser") User fromUser, @Param("toUser") User toUser);
    @Query("SELECT DISTINCT new org.example.facebook.dto.ShowConversationDTO(cu.fromUser.avtUrl, cu.fromUser.id, cu.fromUser.username) FROM ConversationUser cu WHERE cu.fromUser.email <> :email" )
    List<ShowConversationDTO> listUserConversation(@Param("email") String email);
    @Query("SELECT DISTINCT new org.example.facebook.dto.ShowConversationDTO(cu.toUser.avtUrl, cu.toUser.id, cu.toUser.username) FROM ConversationUser cu WHERE cu.toUser.email <> :email")
    List<ShowConversationDTO> listUserConversation2(@Param("email") String email);
}