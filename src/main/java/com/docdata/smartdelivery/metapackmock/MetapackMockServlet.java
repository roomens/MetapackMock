package com.docdata.smartdelivery.metapackmock;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.docdata.smartdelivery.metapackmock.jaxws.PrintParcel;
import com.docdata.smartdelivery.metapackmock.jaxws.PrintParcelResponse;

@SuppressWarnings("serial")
public class MetapackMockServlet extends HttpServlet {
	
	private static final String NS = "http://metapackmock.smartdelivery.docdata.com/";
	private static final QName QNAME_PRINT_PARCEL = new QName(NS, "printParcel");
	private static final QName QNAME_OTHER = new QName(NS, "other");
	private static final Logger LOGGER = Logger.getLogger(MetapackMockServlet.class.getName());

	private final MetapackMockService serviceImpl = new MetapackMockService();
	private static final MessageFactory messageFactory;
	
	static {
		try {
			messageFactory = MessageFactory.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			MimeHeaders headers = getHeaders(request);
			InputStream in = request.getInputStream();
			SOAPMessage soapReq = messageFactory.createMessage(headers, in);
			SOAPMessage soapResp = handleSOAPRequest(soapReq);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/xml;charset=\"UTF-8\"");
			OutputStream out = response.getOutputStream();
			soapResp.writeTo(out);
			out.flush();
		} catch (SOAPException e) {
			throw new IOException("exception while creating SOAP message", e);
		}
	}
	
	private MimeHeaders getHeaders(HttpServletRequest request) {
		MimeHeaders retval = new MimeHeaders();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			StringTokenizer values = new StringTokenizer(value, ",");
			while (values.hasMoreTokens()) {
				retval.addHeader(name, values.nextToken().trim());
			}
		}
		return retval;
	}
	
	private SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException {
		Iterator iter = request.getSOAPBody().getChildElements();
		Object respPojo = null;
		while (iter.hasNext()) {
			// find first Element child
			Object child = iter.next();
			if (child instanceof SOAPElement) {
				respPojo = handleSOAPRequestElement((SOAPElement) child);
				break;
			}
		}
		SOAPMessage soapResp = messageFactory.createMessage();
		SOAPBody respBody = soapResp.getSOAPBody();
		if (respPojo != null) {
			JAXB.marshal(respPojo, new SAAJResult(respBody));
		} else {
			SOAPFault fault = respBody.addFault();
			fault.setFaultString("Unknown SOAP request");
		}
		return soapResp;
	}
	
	private Object handleSOAPRequestElement(SOAPElement reqElem) {
		QName reqName = reqElem.getElementQName();
		if (QNAME_PRINT_PARCEL.equals(reqName)) {
			return handlePrintParcel(JAXB.unmarshal(new DOMSource(reqElem), PrintParcel.class));
		} else if (QNAME_OTHER.equals(reqName)) {
			//
		}
		return null;
	}
	
	private PrintParcelResponse handlePrintParcel(PrintParcel request) {
		PrintParcelResponse response = new PrintParcelResponse();
		response.setReturn(serviceImpl.printParcel(request.getShippingParameters()));
		return response;
	}
}
