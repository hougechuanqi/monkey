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
package org.vrtech.common;

import org.vrtech.common.logs.Logger;
import org.vrtech.common.logs.LoggerFactory;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class Version {
    
    private static final Logger logger = LoggerFactory.getLogger(Version.class);

    private static final String VERSION = "1.0.0";

    public static String getVersion() {
        return VERSION;
    }

}
