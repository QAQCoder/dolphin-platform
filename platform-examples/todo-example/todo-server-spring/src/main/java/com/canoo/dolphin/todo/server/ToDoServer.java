/*
 * Copyright 2015-2017 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.canoo.dolphin.todo.server;

import com.canoo.impl.dp.logging.DolphinLoggerFactory;
import com.canoo.platform.logging.DolphinLoggerConfiguration;
import com.canoo.platform.remoting.server.spring.DolphinPlatformRemotingApplication;
import org.slf4j.event.Level;
import org.springframework.boot.SpringApplication;

@DolphinPlatformRemotingApplication
public class ToDoServer {

    public static void main(String... args) {
        DolphinLoggerConfiguration configuration = new DolphinLoggerConfiguration();
        configuration.setGlobalLevel(Level.INFO);
        configuration.setLevel("com.canoo", Level.DEBUG);
        DolphinLoggerFactory.applyConfiguration(configuration);
        SpringApplication.run(ToDoServer.class, args);
    }
}
