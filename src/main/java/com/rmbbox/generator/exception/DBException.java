package com.rmbbox.generator.exception;

import lombok.NoArgsConstructor;

/**
 * Created by lxl on 15/9/23.
 */
@NoArgsConstructor
public class DBException extends Exception {

    public DBException(String message){
        super(message);
    }

}
