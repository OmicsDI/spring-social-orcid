//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.03 at 11:16:50 AM BST 
//


package uk.ac.ebi.ddi.social.orcid.jaxb.beans;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * A non-empty string that includes a language code to
 * 				indicate the language used for the string's value.
 * 			
 * 
 * <p>Java class for string-with-lang-code complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="string-with-lang-code">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.orcid.org/ns/common>non-empty-string">
 *       &lt;attribute name="language-code" use="required" type="{http://www.orcid.org/ns/common}language-code" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "string-with-lang-code", propOrder = {
    "value"
})
@XmlSeeAlso({
    TranslatedTitle.class
})
public class StringWithLangCode
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlValue
    protected String value;
    @XmlAttribute(name = "language-code", required = true)
    protected LanguageCode languageCode;

    /**
     * Must contain one or more charaters that are not a
     * 				space, carriage return or linefeed
     * 			
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the languageCode property.
     * 
     * @return
     *     possible object is
     *     {@link LanguageCode }
     *     
     */
    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    /**
     * Sets the value of the languageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link LanguageCode }
     *     
     */
    public void setLanguageCode(LanguageCode value) {
        this.languageCode = value;
    }

}
