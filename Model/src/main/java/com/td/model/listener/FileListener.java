package com.td.model.listener;


import com.td.jcr.JcrFactory;
import com.td.jcr.JcrOperations;
import com.td.model.entity.file.Attachment;
import com.td.model.entity.file.FileContainer;
import com.td.model.entity.file.IFileModel;
import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.nodetype.NodeType;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.InputStream;

import static com.td.model.utils.StringUtil.hasText;

/**
 * Created by konstantinchipunov on 16.09.14.
 */
@Component
public class FileListener {


    @PrePersist
    public void prePersist(IFileModel fileModel) throws RepositoryException {
        setExtension(fileModel);
        putFile(fileModel);
    }

    @PreUpdate
    public void preUpdate(IFileModel fileModel) throws RepositoryException {
        putFile(fileModel);
    }

    protected void setExtension(IFileModel fileModel) {
        String name = fileModel.getName();
        int index = name.lastIndexOf(".");
        if (index != -1) {
            String extension = name.substring(index + 1, name.length());
            fileModel.setExtension(extension);
        }

    }

    @PostRemove
    protected void postDelete(IFileModel fileModel) {
        if (fileModel.getFileLocation() != null) {
            Node node = getTemplate().getNodeByPath(fileModel.getFileLocation());
            getTemplate().remove(node);
            getTemplate().save();
        }
    }

    protected void putFile(IFileModel fileModel) throws RepositoryException {
        Node node = null;
        Node parentNode = getParentNode(fileModel);
        if (IFileModel.FileType.FILE.equals(fileModel.getFileType())) {
            if (!hasText(fileModel.getFileLocation())) {
                InputStream in = fileModel.getContent();
                node = getTemplate().putFile(parentNode, fileModel.getObjectId().toString(), fileModel.getMimeType(), in);
            } else {
                node = getTemplate().getNodeByPath(fileModel.getFileLocation());
                if (!parentNode.getPath().equals(node.getParent().getPath())) {
                    getTemplate().move(node.getPath(), parentNode.getPath()+"/"+fileModel.getObjectId().toString());
                    fileModel.setFileLocation(node.getPath());
                }
                InputStream in = fileModel.getContent();
                getTemplate().putFile(parentNode, fileModel.getObjectId().toString(), fileModel.getMimeType(), in);
            }
        } else {
            node = getTemplate().getOrCreateFolder(parentNode, fileModel.getObjectId().toString());
        }
        fileModel.setFileLocation(node.getPath());
        getTemplate().save();
    }

    protected Node getParentNode(IFileModel fileModel) throws RepositoryException {
        FileContainer container = null;
        Node fileNode = null;

        if (IFileModel.FileType.STORE.equals(fileModel.getFileType()) && fileModel instanceof Attachment) {
            if (!hasText(fileModel.getName())) fileModel.setName(fileModel.getObjectId().toString());

            container = ((Attachment) fileModel).getOwner();
            if (container != null) {
                return getTemplate().getOrCreateFolder(container.getObjectId().toString());
            }
        } else {
            StringBuilder path = new StringBuilder();
            container = fileModel.getContainer();
            while (container != null) {
                path.insert(0, container.getObjectId().toString());
                path.insert(0, "/");
                if (container instanceof IFileModel) {
                    boolean isStore = IFileModel.FileType.STORE.equals(((IFileModel) container).getFileType());
                    container = isStore ? ((Attachment) container).getOwner() : ((IFileModel) container).getContainer();
                } else {
                    container = null;
                }
            }
            if (path.length() > 0) {
                path.deleteCharAt(0);
            }
            return getTemplate().getOrCreateByPath(path.toString(), NodeType.NT_FOLDER);
        }
        return getTemplate().getRootNode();
    }


    protected JcrOperations getTemplate() {
        return JcrFactory.getInstance().getOperations();
    }
}
