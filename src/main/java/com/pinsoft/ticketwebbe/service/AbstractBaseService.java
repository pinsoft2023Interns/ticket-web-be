package com.pinsoft.ticketwebbe.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractBaseService<T,Id> {

    protected abstract JpaRepository<T,Id> getRepository();

    public List<T> listAll() {
        return getRepository().findAll();
    }

    public T get(Id id) {
        return getRepository().findById(id).orElseThrow();
    }

    public T save(T entity) {
        return getRepository().save(entity);
    }

    public void delete(Id id) {
        getRepository().deleteById(id);
    }

}
