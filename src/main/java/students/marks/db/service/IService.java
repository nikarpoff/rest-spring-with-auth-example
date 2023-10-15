package students.marks.db.service;


import students.marks.db.exception.DatabaseException;

public interface IService<Entity> {

    Iterable<Entity> listAll();

    void delete(Long id) throws DatabaseException;

    Entity add(Entity entity) throws DatabaseException;

    Entity update(Entity entity) throws DatabaseException;

    Entity findById(Long id) throws DatabaseException;

}
