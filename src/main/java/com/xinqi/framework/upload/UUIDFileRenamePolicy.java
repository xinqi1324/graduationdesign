package com.xinqi.framework.upload;

import java.io.File;
import java.util.UUID;

/**
 * Created by 李坤 on 2016/12/12.
 */
public class UUIDFileRenamePolicy implements FileRenamePolicy {
    @Override
    public File rename(File f) {
        String suffix = "";
        String originalName = f.getName();
        if(originalName.lastIndexOf('.') > 0)
            suffix = originalName.substring(originalName.lastIndexOf('.'));
        String newFileName = UUID.randomUUID().toString() + suffix;
        return new File(f.getParent(), newFileName);
    }
}
