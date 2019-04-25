/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mklinger.micro.strings;

/**
 * Utility class for Strings, checking for emptiness and text.
 *
 * @author Marc Klinger - mklinger[at]mklinger[dot]de
 */
public class Strings {
	/** No instantiation */
	private Strings() {}

	/**
	 * Checks that the specified string contains non-whitespace text. This method is
	 * designed primarily for doing parameter validation in methods and
	 * constructors, as demonstrated below:
	 *
	 * <blockquote><pre>
	 * public Foo(String bar) {
	 * 	this.bar = Strings.requireText(bar);
	 * }
	 * </pre></blockquote>
	 *
	 * @param str the char sequence to check for text
	 * @param <T> the type of the char sequence
	 * @return {@code str} if it contains non-whitespace text
	 * @throws IllegalArgumentException if {@code str} is {@code null} or empty or
	 *         consists of whitespace text only
	 */
	public static <T extends CharSequence> T requireText(final T str) {
		if (!hasText(str)) {
			throw new IllegalArgumentException();
		}
		return str;
	}

	/**
	 * Checks that the specified string contains non-whitespace text and throws a
	 * customized {@link IllegalArgumentException} if it does not. This method is
	 * designed primarily for doing parameter validation in methods and
	 * constructors, as demonstrated below:
	 *
	 * <blockquote><pre>
	 * public Foo(String bar) {
	 * 	this.bar = Strings.requireText(bar, "bar must contain text");
	 * }
	 * </pre></blockquote>
	 *
	 * @param str the char sequence to check for text
	 * @param <T> the type of the char sequence
	 * @return {@code str} if it contains non-whitespace text
	 * @throws IllegalArgumentException if {@code str} is {@code null} or empty or
	 *         consists of whitespace text only
	 */
	public static <T extends CharSequence> T requireText(final T str, final String msg) {
		if (!hasText(str)) {
			throw new IllegalArgumentException(msg);
		}
		return str;
	}

	/**
	 * Check whether the given CharSequence has actual text. More specifically,
	 * returns <code>true</code> if the string is not <code>null</code>, its length
	 * is greater than 0, and it contains at least one non-whitespace character.
	 *
	 * <blockquote><pre>
	 * Strings.hasText(null) = false
	 * Strings.hasText("") = false
	 * Strings.hasText(" ") = false
	 * Strings.hasText("12345") = true
	 * Strings.hasText(" 12345 ") = true
	 * </pre></blockquote>
	 *
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @param <T> the type of the char sequence
	 * @return <code>true</code> if the CharSequence is not <code>null</code>, its
	 *         length is greater than 0, and it does not contain whitespace only
	 * @see java.lang.Character#isWhitespace
	 */
	public static <T extends CharSequence> boolean hasText(final T str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		final int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks that the specified string is not empty. This method is designed
	 * primarily for doing parameter validation in methods and constructors, as
	 * demonstrated below:
	 *
	 * <blockquote><pre>
	 * public Foo(String bar) {
	 * 	this.bar = Strings.requireNonEmpty(bar);
	 * }
	 * </pre></blockquote>
	 *
	 * @param str the char sequence to check
	 * @param <T> the type of the char sequence
	 * @return {@code str} if it is not empty
	 * @throws IllegalArgumentException if {@code str} is {@code null} or empty
	 */
	public static <T extends CharSequence> T requireNonEmpty(final T str) {
		if (isEmpty(str)) {
			throw new IllegalArgumentException();
		}
		return str;
	}

	/**
	 * Checks that the specified string is not empty and throws a customized
	 * {@link IllegalArgumentException} if it is. This method is designed primarily
	 * for doing parameter validation in methods and constructors, as demonstrated
	 * below:
	 *
	 * <blockquote><pre>
	 * public Foo(String bar) {
	 * 	this.bar = Strings.requireNonEmpty(bar, "bar must not be empty");
	 * }
	 * </pre></blockquote>
	 *
	 * @param str the char sequence to check
	 * @param <T> the type of the char sequence
	 * @return {@code str} if it is not empty
	 * @throws IllegalArgumentException if {@code str} is {@code null} or empty
	 */
	public static <T extends CharSequence> T requireNonEmpty(final T str, final String msg) {
		if (isEmpty(str)) {
			throw new IllegalArgumentException(msg);
		}
		return str;
	}

	/**
	 * Check whether the given CharSequence is empty. More specifically, returns
	 * <code>true</code> if the string is not <code>null</code> and its length is
	 * greater than 0.
	 *
	 * <blockquote><pre>
	 * Strings.isEmpty(null) = true
	 * Strings.isEmpty("") = true
	 * Strings.isEmpty(" ") = false
	 * Strings.isEmpty("12345") = false
	 * </pre></blockquote>
	 *
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @param <T> the type of the char sequence
	 * @return <code>true</code> if the CharSequence is not <code>null</code> and
	 *         its length is greater than 0
	 */
	public static <T extends CharSequence> boolean isEmpty(final T str) {
		return str == null || str.length() == 0;
	}
}
