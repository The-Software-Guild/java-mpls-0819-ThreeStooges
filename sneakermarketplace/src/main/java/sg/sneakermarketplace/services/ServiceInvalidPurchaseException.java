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
public class ServiceInvalidPurchaseException extends Exception {
        public ServiceInvalidPurchaseException(String msg) {
        super(msg);
    }
    public ServiceInvalidPurchaseException(String msg, Throwable inner) {
        super(msg, inner);
    }
}
