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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.mklinger.micro.strings.ThrowsException.ThrowingRunnable;

/**
 * @author Marc Klinger - mklinger[at]mklinger[dot]de
 */
public class StringsTest {
	@Test
	public void hasTextTest() {
		assertThat(Strings.hasText("x"), is(true));
		assertThat(Strings.hasText(" x "), is(true));
		assertThat(Strings.hasText(""), is(false));
		assertThat(Strings.hasText(" "), is(false));
		assertThat(Strings.hasText(" \n\t "), is(false));
		assertThat(Strings.hasText(null), is(false));
	}

	@Test
	public void requireTextTest() {
		assertThat(() -> Strings.requireText(null), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireText(""), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireText(" "), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireText("\n\t"), throwsException(IllegalArgumentException.class));
		assertThat(Strings.requireText("x"), is("x"));
		assertThat(Strings.requireText(" x "), is(" x "));
	}

	@Test
	public void requireTextWithMsgTest() {
		assertThat(() -> Strings.requireText(null, "msg"), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireText("", "msg"), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireText(" ", "msg"), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireText("\n\t", "msg"), throwsException(IllegalArgumentException.class));
		assertThat(Strings.requireText("x", "msg"), is("x"));
		assertThat(Strings.requireText(" x ", "msg"), is(" x "));

		assertThat(getExceptionMessage(() -> Strings.requireText(null, "msg")), is("msg"));
		assertThat(getExceptionMessage(() -> Strings.requireText("", "msg")), is("msg"));
		assertThat(getExceptionMessage(() -> Strings.requireText(" ", "msg")), is("msg"));
		assertThat(getExceptionMessage(() -> Strings.requireText("\n\t", "msg")), is("msg"));
	}

	@Test
	public void requireNonEmptyTest() {
		assertThat(() -> Strings.requireNonEmpty(null), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireNonEmpty(""), throwsException(IllegalArgumentException.class));
		assertThat(Strings.requireNonEmpty(" "), is(" "));
		assertThat(Strings.requireNonEmpty("\n\t"), is("\n\t"));
		assertThat(Strings.requireNonEmpty("x"), is("x"));
		assertThat(Strings.requireNonEmpty(" x "), is(" x "));
	}

	@Test
	public void requireNonEmptyWithMsgTest() {
		assertThat(() -> Strings.requireNonEmpty(null, "msg"), throwsException(IllegalArgumentException.class));
		assertThat(() -> Strings.requireNonEmpty("", "msg"), throwsException(IllegalArgumentException.class));
		assertThat(Strings.requireNonEmpty(" ", "msg"), is(" "));
		assertThat(Strings.requireNonEmpty("\n\t", "msg"), is("\n\t"));
		assertThat(Strings.requireNonEmpty("x", "msg"), is("x"));
		assertThat(Strings.requireNonEmpty(" x ", "msg"), is(" x "));

		assertThat(getExceptionMessage(() -> Strings.requireNonEmpty(null, "msg")), is("msg"));
		assertThat(getExceptionMessage(() -> Strings.requireNonEmpty("", "msg")), is("msg"));
	}

	private static org.hamcrest.Matcher<ThrowingRunnable> throwsException(final Class<? extends Throwable> expectedExceptionType) {
		return ThrowsException.throwsException(expectedExceptionType);
	}

	private static String getExceptionMessage(final ThrowingRunnable r) {
		try {
			r.run();
		} catch (final Throwable e) {
			return e.getMessage();
		}
		return null;
	}
}
