package pji.cbt.dao.impl;

import java.util.ArrayList;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pji.cbt.dao.CommonDao;

/**
 * Implementation class of {@link CommonDao}.
 *
 * @since 8 Mar 2017
 * @version 1.0-SNAPSHOT
 * @author Sayid Sidqi
 */
public abstract class CommonDaoImpl<T, PK> implements CommonDao<T, PK> {

	private static final Logger LOG = LoggerFactory.getLogger(CommonDaoImpl.class);
	private static final String NAMESPACE = "mappers";

	private SqlSessionFactory sf;
	private Class<T> type;

	public static final String PREFIX_SELECT_QUERY = "find";
	public static final String PREFIX_INSERT_QUERY = "insert";
	public static final String PREFIX_UPDATE_QUERY = "update";
	public static final String PREFIX_DELETE_QUERY = "delete";

	public CommonDaoImpl(Class<T> type, SqlSessionFactory sf) {
		this.type = type;
		this.sf = sf;
		if (sf == null) {
			LOG.error("Error: Could not instantiate CommonDaoImpl. Failed to load session factory");
		}
	}

	protected SqlSessionFactory getSessionFactory() {
		return sf;
	}

	/**
	 * {@inheritDoc}
	 */
	public int insert(T entity) throws PersistenceException {
		SqlSession session = sf.openSession();
		Integer status = null;
		try {
			String query = NAMESPACE + "." + PREFIX_INSERT_QUERY + entity.getClass().getSimpleName();
			status = (Integer) session.insert(query, entity);
			session.commit();
		} finally {
			session.close();
		}
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T find(PK id) throws PersistenceException {
		SqlSession session = sf.openSession();
		T entity = null;
		try {
			String query = NAMESPACE + "." + PREFIX_SELECT_QUERY + this.type.getSimpleName();
			entity = (T) session.selectOne(query, id);
		} finally {
			session.close();
		}
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<T> findAll() throws PersistenceException {
		SqlSession session = sf.openSession();
		ArrayList<T> entities = null;
		try {
			String query = NAMESPACE + "." + PREFIX_SELECT_QUERY + "All" + this.type.getSimpleName();
			entities = (ArrayList<T>) session.selectList(query);
		} finally {
			session.close();
		}
		return entities;
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(T entity) throws PersistenceException {
		SqlSession session = sf.openSession();
		Integer status = null;
		try {
			String query = NAMESPACE + "." + PREFIX_UPDATE_QUERY + entity.getClass().getSimpleName();
			status = (Integer) session.update(query, entity);
			session.commit();
		} finally {
			session.close();
		}
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(PK id) throws PersistenceException {
		SqlSession session = sf.openSession();
		Integer status = null;
		try {
			String query = NAMESPACE + "." + PREFIX_DELETE_QUERY + this.type.getSimpleName();
			status = (Integer) session.delete(query, id);
			session.commit();
		} finally {
			session.close();
		}
		return status;
	}

}
