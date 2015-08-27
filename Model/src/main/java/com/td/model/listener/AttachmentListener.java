package com.td.model.listener;

import com.td.model.entity.file.Attachment;
import com.td.model.entity.file.FileContainer;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantinchipunov on 30.09.14.
 */
public class AttachmentListener {

    @PrePersist
    public void prePersist(Attachment attachment) {
      checkOwner(attachment);
    }

    @PreUpdate
    public void preUpdate(Attachment attachment) {
        checkOwner(attachment);
    }

    protected void checkOwner(Attachment attachment) {
        if (attachment.getOwner() != null) return;

        Set<Attachment> attachmentSet = new HashSet<>();
        attachmentSet.add(attachment);
        FileContainer container = attachment.getContainer();
        FileContainer owner = null;
        while (container != null) {
            if (container instanceof Attachment) {
                owner = ((Attachment) container).getOwner();
                if (owner == null) {
                    attachmentSet.add((Attachment) container);
                } else {
                    break;
                }
                container = ((Attachment) container).getContainer();
            }else{
                break;
            }
            container = ((Attachment) container).getContainer();
        }

        if (owner != null) {
            for (Attachment attach : attachmentSet) {
                attach.setOwner(owner);
            }
        }
    }
}
