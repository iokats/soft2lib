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

package com.zarkary.designpatterns.creational.example.lazyness;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class App {

    private static final int THREAD_POOL = 10;


    public static void main(String[] args) {

        System.out.println("*************************");

        final LazyHolder holder = new LazyHolder();

        System.out.println("deferring heavy creation...");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREAD_POOL);

        for(int i = 0; i < THREAD_POOL; i++) {
            scheduler.submit(() -> System.out.println(holder.getHeavy()));
        }

        scheduler.shutdown();

        System.out.println("*************************");
    }
}
