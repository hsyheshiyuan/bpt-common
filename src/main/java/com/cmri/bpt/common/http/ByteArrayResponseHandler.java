package com.cmri.bpt.common.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class ByteArrayResponseHandler implements ResponseHandler<byte[]>, HttpErrorStatusHandler {
	protected final String Tag = this.getClass().getSimpleName();

	@Override
	public byte[] handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() >= 300) {
			this.onErrorStatus(statusLine.getStatusCode(), statusLine.getReasonPhrase());
			return null;
		} else {
			return EntityUtils.toByteArray(response.getEntity());
		}
	}

	@Override
	public void onErrorStatus(int statusCode, String reasonPhrase) {
		System.out.println("statusCode : " + statusCode + " , reasonPhrase : " + reasonPhrase);
	}
}
