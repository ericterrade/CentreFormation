package fr.treeptik.centreformation.dao;

import java.util.List;

import fr.treeptik.centreformation.exception.DAOException;

public interface GenericDAO<T, PK> {

	T save(T entity) throws DAOException;

	T update(T entity) throws DAOException;

	void remove(T entity) throws DAOException;

	void removeById(PK id);

	List<T> findAll();
	
	T findById(PK id);

}
