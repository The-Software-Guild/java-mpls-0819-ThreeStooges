/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

/**
 *
 * @author mac
 */
public class InsufficientFundsServiceException extends Exception {

    public InsufficientFundsServiceException(String msg) {
        super(msg);
    }

    public InsufficientFundsServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
