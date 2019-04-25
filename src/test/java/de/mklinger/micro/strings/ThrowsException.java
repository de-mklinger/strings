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

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import de.mklinger.micro.strings.ThrowsException.ThrowingRunnable;

/**
 * Hamcrest matcher that executes a runnable and expects it to throw a given
 * exception type.
 *
 * @author Marc Klinger - mklinger[at]mklinger[dot]de
 */
public class ThrowsException extends TypeSafeMatcher<ThrowingRunnable> {

	@FunctionalInterface
	public interface ThrowingRunnable {
		void run() throws Exception;
	}

	private final Class<? extends Throwable> expectedExceptionType;
	private Throwable actualException;

	public ThrowsException(final Class<? extends Throwable> expectedExceptionType) {
		this.expectedExceptionType = expectedExceptionType;
	}

	public static ThrowsException throwsException(final Class<? extends Throwable> expectedExceptionType) {
		return new ThrowsException(expectedExceptionType);
	}

	@Override
	public void describeTo(final Description description) {
		description.appendText("Exception of type " + expectedExceptionType.getName() + " to be thrown");
	}

	@Override
	protected void describeMismatchSafely(final ThrowingRunnable item, final Description mismatchDescription) {
		if (actualException == null) {
			mismatchDescription.appendText("no exception was thrown");
		} else {
			mismatchDescription.appendText("was exception of type " + actualException.getClass().getName() + " was thrown");
		}
	}

	@Override
	protected boolean matchesSafely(final ThrowingRunnable r) {
		try {
			r.run();
			actualException = null;
		} catch (final Throwable e) {
			actualException = e;
			if (expectedExceptionType.isAssignableFrom(e.getClass())) {
				return true;
			}
		}
		return false;
	}
}