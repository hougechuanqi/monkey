/*
 * Copyright 2016  VRTECH.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pub.vrtech.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */

@EnableScheduling
@Configuration
@ComponentScan(basePackages = {"org.vrtech"})
public class Start {

    public ApplicationContext initApplication() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                Start.class);
        return context;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Start s = new Start();
        ApplicationContext ctx = s.initApplication();
    }

}
