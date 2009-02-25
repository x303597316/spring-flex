package org.springframework.flex.messaging.security;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.security.AccessDeniedException;
import org.springframework.security.AuthenticationException;
import org.springframework.security.SpringSecurityException;

import flex.messaging.security.SecurityException;

public class SecurityExceptionTranslationAdvice implements ThrowsAdvice {
	
	public void afterThrowing(SpringSecurityException ex) {
		if (ex instanceof AuthenticationException) {
			SecurityException se = new SecurityException();
			se.setCode(SecurityException.CLIENT_AUTHENTICATION_CODE);
			se.setMessage(ex.getLocalizedMessage());
			se.setRootCause(ex);
			throw se;
		} else if (ex instanceof AccessDeniedException) {
			SecurityException se = new SecurityException();
			se.setCode(SecurityException.CLIENT_AUTHORIZATION_CODE);
			se.setMessage(ex.getLocalizedMessage());
			se.setRootCause(ex);
			throw se;
		}
	}
}