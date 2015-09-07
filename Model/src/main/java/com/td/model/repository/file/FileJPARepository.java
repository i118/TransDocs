package com.td.model.repository.file;

import com.td.jcr.JcrFactory;
import com.td.jcr.JcrOperations;
import com.td.model.repository.GenericJPARepository;
import com.td.model.entity.file.FileModel;
import com.td.model.entity.file.IFileModel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * Created by konstantinchipunov on 10.09.14.
 */
public class FileJPARepository extends GenericJPARepository<FileModel> implements FileRepository {

    public FileJPARepository() {
        super(FileModel.class);
    }

    public JcrOperations getTemplate() {
        return JcrFactory.getInstance().getOperations();
    }

    public IFileModel getFileByPath(String path){
        return getFileByPath(path, getModelClass());
    }

    public IFileModel getFileByPath(String path, Class<? extends IFileModel> clazz){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<? extends IFileModel> query = cb.createQuery(clazz);
        Root<? extends IFileModel> root = query.from(clazz);
        Predicate eqPath = cb.equal(root.get("fileLocation"), path);
        query.where(eqPath);
        return getEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public void delete(FileModel persistent){
        if(!getEntityManager().contains(persistent)){
            persistent = update(persistent);
        }
        getEntityManager().remove(persistent);
    }

    @Override
    public void save(FileModel persistent) {
        getEntityManager().persist(persistent);
    }
}
