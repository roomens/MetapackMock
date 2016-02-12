package com.docdata.smartdelivery.metapackmock;

import java.util.List;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MetapackMockService {
	
	@WebMethod
	public ShippingParameter[] printParcel(ShippingParameter[] inputParameters) {
		List<ShippingParameter> outputParameters = new ArrayList<>();
		for (ShippingParameter sp : inputParameters) {
			if (sp.getName().equals("System.RequestId")) {
				outputParameters.add(sp);
			}
		}
		return outputParameters.toArray(new ShippingParameter[outputParameters.size()]);
	}

}
