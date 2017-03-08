package pji.cbt.dao;

import java.util.ArrayList;

import org.apache.ibatis.exceptions.PersistenceException;

/**
 * Interface contains basic CRUD operation for all entity.
 *
 * @since 8 Mar 2017
 * @version 1.0-SNAPSHOT
 * @author Sayid Sidqi
 */
public interface CommonDao<T, PK> {

	/**
	 * Method to persist new entity to database.
	 * 
	 * @param entity {@link T} object to be persisted
	 * @return {@link int} the number of rows affected by the insert or null if entity was failed to be persisted
	 * @throws PersistenceException
	 */
	public int insert(T entity) throws PersistenceException;

	/**
	 * Method to select id based on its identifier. 
	 * 
	 * @param id {@link PK} the key/identifier
	 * @return {@link T} the persistent object based on given key or null if there's no such entity found
	 * @throws PersistenceException
	 */
	public T find(PK id) throws PersistenceException;

	/**
	 * Method to select all record of entity class.
	 * 
	 * @return {@link ArrayList}&lt;{@link T}&gt; the populated objects or null if the record is empty
	 * @throws PersistenceException
	 */
	public ArrayList<T> findAll() throws PersistenceException;

	/**
	 * Method to update existing data on database.
	 * 
	 * @param entity {@link T} object to be updated
	 * @return {@link int} the number of rows affected by the update or null if update process was not successfully executed
	 * @throws PersistenceException
	 */
	public int update(T entity) throws PersistenceException;

	/**
	 * Method to remove data from database based on its identifier.
	 * 
	 * @param id {@link PK} the key/identifier
	 * @return {@link int} the number of rows affected by the delete or null if error occurs when removing data 
	 * @throws PersistenceException
	 */
	public int delete(PK id) throws PersistenceException;

}
