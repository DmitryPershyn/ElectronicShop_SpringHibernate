package com.pershyn.electronicshop.daoimpl;

import com.pershyn.electronicshop.dao.NewsDAO;
import com.pershyn.electronicshop.model.News;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NewsDAOImpl extends AbstractDAO implements NewsDAO {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NewsDAOImpl.class);

    public NewsDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<News> getAllNews() {
        List<News> newsList = getSession().createCriteria(News.class).list();
        return new HashSet<>(newsList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<News> getNews(Integer count) {
        List<News> newsList = getSession().createCriteria(News.class).setMaxResults(count).list();
        return new HashSet<>(newsList);
    }

    @Override
    public News getNewsById(Integer id) {
        return (News) getSession().createCriteria(News.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public News getNewsByDate(Date date) {
        return (News) getSession().createCriteria(News.class).add(Restrictions.eq("date", date)).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getLastAddedNews(Integer count) {
        List<News> news = (List<News>) getSession()
                .createCriteria(News.class)
                .addOrder(Order.desc("id"))
                .setMaxResults(count)
                .list();
        return new ArrayList<>(news);
    }

    @Override
    public void addNews(News news) {
        getSession().save(news);
        LOGGER.info("News has been added successfully ", news);
    }

    @Override
    public  void updateNews(News news){
        getSession().update(news);
        LOGGER.info("News has been updated successfully ", news);
    }

    @Override
    public void deleteNews(Integer id){
        News news = (News) getSession().load(News.class, id);
        if(news != null){
            LOGGER.info("News has been successfully remove ", news);
            getSession().delete(news);
        }
    }
}
