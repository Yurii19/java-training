package hibernate.services;

import hibernate.dao.Dao;
import hibernate.models.Client;

abstract class Service<T> {
    final Dao<T> dao;

    public Service(Class<T> type) {
        this.dao = new Dao<>(type);
    }

    public T createNew(T value) {
        Long id = dao.create(value);
        return id == null ? null : value;
    }

    public void update(T value) {
        dao.create(value);
    }

    public void delete(T value) {
        dao.create(value);
    }

    public T get(int id) {
        return dao.getById(id);
    }
}
