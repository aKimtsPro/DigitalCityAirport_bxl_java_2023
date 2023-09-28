package be.digitalcity.spring.airport.service;

import be.digitalcity.spring.airport.models.entity.Person;

import java.util.List;

public interface CrudService<T, TID> {

    List<T> getAll();
    T getOne(TID id);

    void insert(T entity);

    void delete(TID id);

    T update(TID id, T entity);

}
