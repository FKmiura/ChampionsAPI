package com.kmiura.campeao.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.kmiura.campeao.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

public class GenericDao<Entidade> {

    private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDao() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

    public void salvar(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			session.persist(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	public void excluir(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			session.remove(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}

	public Entidade merge(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			Entidade retorno = (Entidade) session.merge(entidade);
			transacao.commit();
			return retorno;
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	public void editar(Entidade entidade) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		try {
			transacao = session.beginTransaction();
			session.merge(entidade);
			transacao.commit();
		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			session.close();
		}
	}
	
	@Transactional
	public List<Entidade> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Entidade> criteriaQuery = criteriaBuilder.createQuery(classe);
			Root<Entidade> root = criteriaQuery.from(classe);

			criteriaQuery.select(root);

			List<Entidade> resultado = session.createQuery(criteriaQuery).getResultList();
			return resultado;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	@Transactional
	public List<Entidade> listarOrdenado(String campoOrdenacao, Boolean acendente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Entidade> criteriaQuery = criteriaBuilder.createQuery(classe);
			Root<Entidade> root = criteriaQuery.from(classe);
			
			criteriaQuery.select(root);
			
			if (acendente) {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(campoOrdenacao)));
			} else {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(campoOrdenacao)));
			}
			
			criteriaQuery.select(root);

			List<Entidade> resultado = session.createQuery(criteriaQuery).getResultList();
			return resultado;
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	public Entidade buscarId(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Entidade> criteriaQuery = criteriaBuilder.createQuery(classe);
			Root<Entidade> root = criteriaQuery.from(classe);
			
			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
			
			Entidade resultado = session.createQuery(criteriaQuery).getSingleResult();
			return resultado;
			
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}
