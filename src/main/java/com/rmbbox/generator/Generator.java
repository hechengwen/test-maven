package com.rmbbox.generator;

/**
 * Created by lxl on 15/9/23.
 */
public interface Generator {

    void generator() throws Exception;

    boolean testDBConnect() throws Exception;

}
