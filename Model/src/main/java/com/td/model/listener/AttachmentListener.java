package com.td.model.listener;

import com.td.model.entity.file.IAttachment;
import com.td.model.entity.file.IFileContainer;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantinchipunov on 30.09.14.
 */
public class AttachmentListener {

    @PrePersist
    public void prePersist(IAttachment attachment) {
      checkOwner(attachment);
    }

    @PreUpdate
    public void preUpdate(IAttachment attachment) {
        checkOwner(attachment);
    }

    protected void checkOwner(IAttachment attachment) {
        if (attachment.getOwner() != null) return;

        Set<IAttachment> attachmentSet = new HashSet<>();
        attachmentSet.add(attachment);
        IFileContainer container = attachment.getContainer();
        IFileContainer owner = null;
        while (container != null) {
            if (container instanceof IAttachment) {
                owner = ((IAttachment) container).getOwner();
                if (owner == null) {
                    attachmentSet.add((IAttachment) container);
                } else {
                    break;
                }
                container = ((IAttachment) container).getContainer();
            }else{
                break;
            }
            container = ((IAttachment) container).getContainer();
        }

        if (owner != null) {
            for (IAttachment attach : attachmentSet) {
                attach.setOwner(owner);
            }
        }
    }
}
