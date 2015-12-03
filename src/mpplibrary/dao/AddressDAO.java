/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.dao;

import mpplibrary.base.Address;

/**
 *
 * @author sanjeev
 */
public class AddressDAO {
    private Address address;

    public AddressDAO(Address address) {
        this.address = address;
    }

    public AddressDAO() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    
    
//    public ArrayList<Address> getAddresses()
//    {
//        return new ArrayList<Address>();
//    }
    
    
    
}
