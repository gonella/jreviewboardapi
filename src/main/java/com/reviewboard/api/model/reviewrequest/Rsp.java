//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.26 at 10:05:51 PM BRT 
//


package com.reviewboard.api.model.reviewrequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}stat"/>
 *         &lt;element ref="{}review_request"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "stat",
    "reviewRequest"
})
@XmlRootElement(name = "rsp")
public class Rsp {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String stat;
    @XmlElement(name = "review_request", required = true)
    protected ReviewRequest reviewRequest;

    /**
     * Gets the value of the stat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStat() {
        return stat;
    }

    /**
     * Sets the value of the stat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStat(String value) {
        this.stat = value;
    }

    /**
     * Gets the value of the reviewRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ReviewRequest }
     *     
     */
    public ReviewRequest getReviewRequest() {
        return reviewRequest;
    }

    /**
     * Sets the value of the reviewRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReviewRequest }
     *     
     */
    public void setReviewRequest(ReviewRequest value) {
        this.reviewRequest = value;
    }

}
