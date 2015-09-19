//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.09.26 at 07:01:33 PM BRT 
//


package com.reviewboard.api.model.reviewresource;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{}body_top"/>
 *         &lt;element ref="{}ship_it"/>
 *         &lt;element ref="{}links"/>
 *         &lt;element ref="{}timestamp"/>
 *         &lt;element ref="{}id"/>
 *         &lt;element ref="{}body_bottom"/>
 *         &lt;element ref="{}public"/>
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
    "bodyTop",
    "shipIt",
    "links",
    "timestamp",
    "id",
    "bodyBottom",
    "_public"
})
@XmlRootElement(name = "item")
public class Item {

    @XmlElement(name = "body_top", required = true)
    protected String bodyTop;
    @XmlElement(name = "ship_it", required = true)
    protected BigInteger shipIt;
    @XmlElement(required = true)
    protected Links links;
    @XmlElement(required = true)
    protected String timestamp;
    @XmlElement(required = true)
    protected BigInteger id;
    @XmlElement(name = "body_bottom", required = true)
    protected BodyBottom bodyBottom;
    @XmlElement(name = "public", required = true)
    protected BigInteger _public;

    /**
     * Gets the value of the bodyTop property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodyTop() {
        return bodyTop;
    }

    /**
     * Sets the value of the bodyTop property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodyTop(String value) {
        this.bodyTop = value;
    }

    /**
     * Gets the value of the shipIt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getShipIt() {
        return shipIt;
    }

    /**
     * Sets the value of the shipIt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setShipIt(BigInteger value) {
        this.shipIt = value;
    }

    /**
     * Gets the value of the links property.
     * 
     * @return
     *     possible object is
     *     {@link Links }
     *     
     */
    public Links getLinks() {
        return links;
    }

    /**
     * Sets the value of the links property.
     * 
     * @param value
     *     allowed object is
     *     {@link Links }
     *     
     */
    public void setLinks(Links value) {
        this.links = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the bodyBottom property.
     * 
     * @return
     *     possible object is
     *     {@link BodyBottom }
     *     
     */
    public BodyBottom getBodyBottom() {
        return bodyBottom;
    }

    /**
     * Sets the value of the bodyBottom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BodyBottom }
     *     
     */
    public void setBodyBottom(BodyBottom value) {
        this.bodyBottom = value;
    }

    /**
     * Gets the value of the public property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPublic() {
        return _public;
    }

    /**
     * Sets the value of the public property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPublic(BigInteger value) {
        this._public = value;
    }

}
