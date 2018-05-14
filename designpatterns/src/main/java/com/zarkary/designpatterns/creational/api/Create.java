/*******************************************************************************
 * Copyright 2018 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.zarkary.designpatterns.creational.api;

import java.util.function.Supplier;

/**
 * Utility class for creational design patterns.
 *
 * @author Ioannis Katsatos
 * @since 25.04.2018
 */
public class Create {

    /**
     * <p>Creates lazily and returns a {@link Singleton} wrapper object of the given instance.</p>
     *
     * <p>The lazy initialization of an object is valuable when parts of an object's internal
     * are heavyweight resources, we'll benefit if we postpone creating them. This can speed
     * up object creation, and the program doesn't expend any effort creating things that may
     * not be used.</p>
     *
     * <p>The Singleton pattern restricts the instantiation of a class to one object. This
     * is useful when exactly one object is needed because the creation of an object is too
     * heavyweight.</p>
     *
     * <p>The method {@link #lazily(Supplier)} it's not thread safe. You should take care
     * synchronization issues when your application runs in a multithreaded environment.</p>
     *
     * @param instanceSupplier the supplier of the passed instance
     * @param <T> the type of passed instance
     * @return the {@link Singleton} wrapper of the passed instance
     */
    public static  <T> Singleton<T> lazily(final Supplier<T> instanceSupplier) {
        return new Singleton<>(instanceSupplier);
    }
}
