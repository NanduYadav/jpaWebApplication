package com.learn.jpaWebApplication.dao;

import com.learn.jpaWebApplication.exceptions.DatabaseException;
import com.learn.jpaWebApplication.models.PurgeList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.beans.Expression;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by z002v2f on 17/11/17.
 */
@Transactional
@Repository
public class PurgeListDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurgeListDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<PurgeList> getMdmSeqIds() throws DatabaseException {
        try {
            Session session = entityManager.unwrap(Session.class);
            Criteria criteria = session.createCriteria(PurgeList.class);
            criteria.add(Restrictions.le("created", new Timestamp(new Date().getTime())));
            return criteria.list();
        } catch (Exception e) {
            String message = "Error in reading data from DB " + e.getMessage();
            LOGGER.error(message);
            throw new DatabaseException(message, e);
        }
    }

}
