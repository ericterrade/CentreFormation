package fr.treeptik.centreformation.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.treeptik.centreformation.dao.GenericDAO;
import fr.treeptik.centreformation.exception.DAOException;

public abstract class GenericJPADAO<T, PK> implements GenericDAO<T, PK> {

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> entityClass;

	public GenericJPADAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T save(T entity) throws DAOException {
		try{
			entityManager.persist(entity);
		}catch(PersistenceException e){
			throw new DAOException("-------- Erreur de sauvegarde : "+entityClass.getSimpleName(),e);
		}
		return entity;
	}

	public T update(T entity) throws DAOException {
		try{
			entityManager.merge(entity);
		}
		catch(PersistenceException e){
			throw new DAOException("-------- Erreur d'Update : "+ entityClass.getSimpleName(),e);
		}
		return entity;
	}

	public void remove(T entity) throws DAOException {
		try{
			entityManager.remove(entity);
		}
		catch(PersistenceException e){
			throw new DAOException("-------- Erreur de suppression : "+ entityClass.getSimpleName(),e);
		}
	}

	public void removeById(PK id) {
		entityManager.createQuery(
				"delete from " + entityClass.getSimpleName() + " where id = "
						+ id).executeUpdate();
	}

	public List<T> findAll() {
		TypedQuery<T> query = (TypedQuery<T>) entityManager.createQuery("select t from " + entityClass.getSimpleName() + " t");
		return query.getResultList();
	}

	public T findById(PK id) {
		return entityManager.find(entityClass, id);
	}

}
