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
package org.vrtech.common.extension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */

@Component
public class ExtensionLoader
        implements
            ApplicationListener<ContextRefreshedEvent> {

    /***
     * 
     */
    private Map<String, Class<?>> serviceClazzMap = new ConcurrentHashMap<String, Class<?>>();

    private Map<Class<?>, Object> clazz2InstanceMap = new ConcurrentHashMap<Class<?>, Object>();

    /***
     * 加载标签，Service交给spring来管理
     * 
     * @param ctx
     */
    private void loadService(ApplicationContext ctx) {
        Map<String, Object> beans = ctx
                .getBeansWithAnnotation(org.vrtech.common.annotation.Service.class);
        for (String beanName : beans.keySet()) {
            System.out.println(beanName);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.ApplicationListener#onApplicationEvent(org
     * .springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadService(event.getApplicationContext());
    }

}
