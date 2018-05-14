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

package com.zarkary.designpatterns.creational.example.chocolateboiler;

import com.zarkary.designpatterns.creational.api.Create;
import com.zarkary.designpatterns.creational.api.Singleton;

public class App {

    private static void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        final Singleton<ChocolateBoiler> singleton = Create.lazily(() -> new ChocolateBoiler());
        final Runnable command = () -> {
            final ChocolateBoiler boiler = singleton.getInstance();
            System.out.println("Boiler instance: " + boiler);
            sleep(1000);

            boiler.fill();
            sleep(1000);

            boiler.boil();
            sleep(1000);

            boiler.drain();
            sleep(1000);
        };

        final Thread thread1 = new Thread(command);
        final Thread thread2 = new Thread(command);

        thread1.start();
        thread2.start();
    }
}
