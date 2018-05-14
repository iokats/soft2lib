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

package com.zarkary.designpatterns.eventaggregator.api;

/**
 * Represents an action where its functionality is affected from the passed {@link IMessage}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a> whose
 * functional method is {@link #doAction(IMessage)}.

 * @param <T> the type of {@link IMessage}'s content
 *
 * @author Ioannis Katsatos
 * @since 11.04.2018
 */
@FunctionalInterface
public interface IAction<T> {

    /**
     * Performs an action based on the given {@link IMessage}
     *
     * @param message the {@link IMessage} which carry on valuable
     *                information for the action's execution.
     */
    void doAction(IMessage<T> message);
}
