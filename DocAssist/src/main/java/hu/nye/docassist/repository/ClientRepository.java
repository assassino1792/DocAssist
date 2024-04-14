package hu.nye.docassist.repository;

import hu.nye.docassist.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Logger logger = LoggerFactory.getLogger(ClientRepository.class);
}
