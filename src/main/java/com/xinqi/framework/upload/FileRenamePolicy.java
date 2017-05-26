package com.xinqi.framework.upload;

import java.io.File;

/**
 * Created by 李坤 on 2016/12/12.
 */
public interface FileRenamePolicy {
    File rename(File f);
}
