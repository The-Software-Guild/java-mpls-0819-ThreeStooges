/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sneakermarketplace.services;

/**
 *
 * @author Thomas
 */
public class InvalidBidException extends Exception {
    public InvalidBidException(String msg) {
        super(msg);
    }
    public InvalidBidException(String msg, Throwable inner) {
        super(msg, inner);
    }
    
}
