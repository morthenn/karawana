package allinone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import allinone.entities.TestEntity;

@NoRepositoryBean
public interface TestEntityRepository extends JpaRepository<TestEntity, Long>, TestEntityRepositoryCustom {
    
}
