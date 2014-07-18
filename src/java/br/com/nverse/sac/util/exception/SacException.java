/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.util.exception;

/**
 *
 * @author jmarcos
 */
public class SacException extends Exception {

    /**
     * Creates a new instance of
     * <code>SacException</code> without detail message.
     */
    public SacException() {
    }

    /**
     * Constructs an instance of
     * <code>SacException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SacException(String msg) {
        super(msg);
    }
}
