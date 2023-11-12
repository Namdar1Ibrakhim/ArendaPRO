package com.example.arendapro.repository;

import com.example.arendapro.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Integer> {

    List<Messages> findAllByReceiver_Id(Integer id);
    List<Messages> findAllByReceiver_IdAndSender_Id(Integer receiver_id, Integer sender_id);


}
