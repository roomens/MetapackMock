
package com.docdata.smartdelivery.metapackmock.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "printParcel", namespace = "http://metapackmock.smartdelivery.docdata.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printParcel", namespace = "http://metapackmock.smartdelivery.docdata.com/")
public class PrintParcel {

    @XmlElement(name = "shippingParameters", namespace = "", nillable = true)
    private com.docdata.smartdelivery.metapackmock.ShippingParameter[] shippingParameters;

    /**
     * 
     * @return
     *     returns ShippingParameter[]
     */
    public com.docdata.smartdelivery.metapackmock.ShippingParameter[] getShippingParameters() {
        return this.shippingParameters;
    }

    /**
     * 
     * @param shippingParameters
     *     the value for the shippingParameters property
     */
    public void setShippingParameters(com.docdata.smartdelivery.metapackmock.ShippingParameter[] shippingParameters) {
        this.shippingParameters = shippingParameters;
    }

}
