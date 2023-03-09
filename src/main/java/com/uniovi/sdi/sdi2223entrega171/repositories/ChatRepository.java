package com.uniovi.sdi.sdi2223entrega171.repositories;

import com.uniovi.sdi.sdi2223entrega171.entities.Chat;
import com.uniovi.sdi.sdi2223entrega171.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {


}
