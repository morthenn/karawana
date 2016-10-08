package allinone.repositories;


import allinone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findByNameAndPassword(String name, String password);
}


