package be.digitalcity.spring.airport.dal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;

@NoRepositoryBean
public interface GetterRepository<T, TID> extends Repository<T, TID> {

    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    List<T> findAll(Sort sortable);
    T findById(TID id);

}
