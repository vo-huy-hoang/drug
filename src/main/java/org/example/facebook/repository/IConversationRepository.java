package org.example.facebook.repository;

import org.example.facebook.model.Conversation;
import org.example.facebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IConversationRepository extends JpaRepository<Conversation, Integer> {
    @Query("SELECT c FROM Conversation c JOIN c.conversationUsers cu " +
            "WHERE (cu.fromUser.id = :fromUserId AND cu.toUser.id = :toUserId) " +
            "OR (cu.fromUser.id = :toUserId AND cu.toUser.id = :fromUserId)")
    Optional<Conversation> findConversationBetweenUsers(@Param("fromUserId") Integer fromUserId, @Param("toUserId") Integer toUserId);
}
