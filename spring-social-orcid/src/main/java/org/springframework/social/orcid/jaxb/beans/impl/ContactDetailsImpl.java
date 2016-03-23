//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.06 at 01:57:48 PM BST 
//


package org.springframework.social.orcid.jaxb.beans.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.social.orcid.jaxb.beans.Address;
import org.springframework.social.orcid.jaxb.beans.ContactDetails;
import org.springframework.social.orcid.jaxb.beans.Email;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "emails",
    "address"
})
@XmlRootElement(name = "contact-details")
public class ContactDetailsImpl
    implements ContactDetails
{

    @XmlElement(name = "email", type = EmailImpl.class)
    protected List<Email> emails;
    @XmlElement(type = AddressImpl.class)
    protected AddressImpl address;

    public List<Email> getEmails() {
        if (emails == null) {
            emails = new ArrayList<Email>();
        }
        return this.emails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address value) {
        this.address = ((AddressImpl) value);
    }

}