/**
 * 
 */
package com.monirul.simplewebcrawler.http;

public enum HttpStatus {


	OK(200, 299),


	BAD_REQUEST(400, 400),
	UNAUTHORIZED(401, 401),
	FORBIDDEN(403, 403),
	NOT_FOUND(404, 404),


	INTERNAL_SERVER_ERROR(500, 500),


	UNSPECIFIED_ERROR(1, 999);

	private final int begin;
	private final int end;

	private HttpStatus(final int begin, final int end) {
		this.begin = begin;
		this.end = end;
	}

	public static HttpStatus fromHttpCode(final int code) {
		for (HttpStatus status : values()) {
			if ((status.begin <= code) && (status.end >= code)) {
				return status;
			}
		}
		return UNSPECIFIED_ERROR;
	}

}
