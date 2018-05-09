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
