package dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import controller.ServiceController;
import entity.Asset;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class KongMinHaoDAOImpl implements KongMinHaoDAO{

    private final SessionFactory sessionFactory;

    @Autowired
    public KongMinHaoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void increaseAsset(String name,long money) {


        Asset A = getAssetByName(name);
        A.setMoney(A.getMoney()+100);
        //String hql = "update Asset  set money=200 where name=\"KongMinHao\""
        this.sessionFactory.getCurrentSession().update(A);
        ///*.setParameter(0,A.getMoney()+money)*/.setParameter(0,A.getName());
    }

    @Override
    public Asset getAssetByName(String name) {
        String hql = "from Asset a where a.name=? ";
        Asset result = (Asset) sessionFactory.getCurrentSession().createQuery(hql).setParameter(0,name).uniqueResult();
        if(result!=null){
            return result;
        }
        else{
            result = new Asset(name,100);
            addAsset(result);
            return result;
        }
    }

    @Override
    public void addAsset(Asset asset) {
        this.sessionFactory.getCurrentSession().persist(asset);
    }

    @Override
    public List<Asset> getAllAsset() {
        String hql = "from Asset ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }
}

