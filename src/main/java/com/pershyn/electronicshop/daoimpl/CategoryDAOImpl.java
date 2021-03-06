package com.pershyn.electronicshop.daoimpl;

import com.pershyn.electronicshop.dao.CategoryDAO;
import com.pershyn.electronicshop.model.Category;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDAOImpl extends AbstractDAO implements CategoryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDAOImpl.class);

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void updateCategory(Category category) {
        getSession().update(category);
        LOGGER.info("Category updated: ", category);
    }

    @Override
    public void addCategory(Category category) {
        getSession().save(category);
        LOGGER.info("Category added: ", category);
    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category = (Category) getSession().createCriteria(Category.class).add(Restrictions.eq("id", id)).uniqueResult();
        LOGGER.info("Category loaded: {}.", category);
        return category;
    }

    @Override
    public void deleteCategoryById(Integer id) {
        Category category = (Category) getSession().load(Category.class, id);

        if (category != null) {
            LOGGER.info("Category removed: {}.", category);
            getSession().delete(category);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Category> getAllCategories() {
        List<Category> category= getSession().createCriteria(Category.class).list();
        return new HashSet<>(category);
    }
}
