
package com.docdata.smartdelivery.metapackmock.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "printParcelResponse", namespace = "http://metapackmock.smartdelivery.docdata.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printParcelResponse", namespace = "http://metapackmock.smartdelivery.docdata.com/")
public class PrintParcelResponse {

    @XmlElement(name = "return", namespace = "", nillable = true)
    private com.docdata.smartdelivery.metapackmock.ShippingParameter[] _return;

    /**
     * 
     * @return
     *     returns ShippingParameter[]
     */
    public com.docdata.smartdelivery.metapackmock.ShippingParameter[] getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.docdata.smartdelivery.metapackmock.ShippingParameter[] _return) {
        this._return = _return;
    }

}
