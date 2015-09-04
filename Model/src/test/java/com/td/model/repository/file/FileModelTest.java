package com.td.model.repository.file;

import com.td.model.context.qualifier.FileQualifier;
import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.AbstractDaoTest;
import com.td.model.entity.file.CustomerFileModel;
import com.td.model.entity.file.IFileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by konstantinchipunov on 17.09.14.
 */
public class FileModelTest extends AbstractDaoTest<IFileModel> {

    @Autowired
    @FileQualifier
    FileRepository dao;

    @Test(enabled = false)
    public void fileTest() throws Exception{
        IFileModel fileModel = new CustomerFileModel();
        File file = new File("/Users/konstantinchipunov/settings.xml");
        try(FileInputStream in = new FileInputStream(file)) {
             fileModel.setName(file.getName());
             fileModel.setFileType(IFileModel.FileType.FILE);
             fileModel.setContent(in);
             fileModel.setMimeType("");
             //getDao().saveOrUpdate(fileModel);
            ((GenericJPARepository)getDao()).getEntityManager().flush();
            ((GenericJPARepository)getDao()).getEntityManager().clear();
        }

        IFileModel persistFile = (IFileModel) getDao().findById(fileModel.getObjectId());
        Path path = Files.createTempFile("jcr", "xml");
        File tmp = path.toFile();
        try(
                OutputStream out = new FileOutputStream(tmp);
            ){
            persistFile.readContent(out);
        }
        Desktop.getDesktop().edit(tmp);
    }



    @Override
    protected FileRepository getDao() {
        return dao;
    }
}
