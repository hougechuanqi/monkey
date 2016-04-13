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
package org.vrtech.common.logs.support;

import org.vrtech.common.Version;
import org.vrtech.common.logs.Logger;
import org.vrtech.common.utils.NetUtils;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
public class FailsafeLogger implements Logger {

    private Logger logger;

    /**
     * 
     */
    public FailsafeLogger(Logger logger) {
        this.logger = logger;
    }

    private String appendContextMessage(String msg) {
        return " [Monkey] " + msg + ", monkey version: " + Version.getVersion()
                + ", current host: " + NetUtils.getLogHost();
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#trace(java.lang.String)
     */
    @Override
    public void trace(String msg) {
        logger.trace(appendContextMessage(msg));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#trace(java.lang.Throwable)
     */
    @Override
    public void trace(Throwable e) {
        try {
            logger.trace(e);
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#trace(java.lang.String,
     * java.lang.Throwable)
     */
    @Override
    public void trace(String msg, Throwable e) {
        try {
            logger.trace(appendContextMessage(msg), e);
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#debug(java.lang.String)
     */
    @Override
    public void debug(String msg) {
        try {
            logger.debug(appendContextMessage(msg));
        } catch (Throwable e2) {
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#debug(java.lang.Throwable)
     */
    @Override
    public void debug(Throwable e) {
        try {
            logger.debug(e);
        } catch (Throwable e2) {
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#debug(java.lang.String,
     * java.lang.Throwable)
     */
    @Override
    public void debug(String msg, Throwable e) {
        try {
            logger.debug(appendContextMessage(msg), e);
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#info(java.lang.String)
     */
    @Override
    public void info(String msg) {
        try {
            logger.info(appendContextMessage(msg));
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#info(java.lang.Throwable)
     */
    @Override
    public void info(Throwable e) {
        try {
            logger.info(e);
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#info(java.lang.String,
     * java.lang.Throwable)
     */
    @Override
    public void info(String msg, Throwable e) {
        try {
            logger.info(appendContextMessage(msg), e);
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#warn(java.lang.String)
     */
    @Override
    public void warn(String msg) {
        try {
            logger.warn(appendContextMessage(msg));
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#warn(java.lang.Throwable)
     */
    @Override
    public void warn(Throwable e) {
        try {
            logger.warn(e);
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#warn(java.lang.String,
     * java.lang.Throwable)
     */
    @Override
    public void warn(String msg, Throwable e) {
        try {
            logger.warn(appendContextMessage(msg), e);
        } catch (Throwable e2) {
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#error(java.lang.String)
     */
    @Override
    public void error(String msg) {
        try {
            logger.error(appendContextMessage(msg));
        } catch (Throwable e2) {
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#error(java.lang.Throwable)
     */
    @Override
    public void error(Throwable e) {
        try {
            logger.error(e);
        } catch (Throwable e2) {
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#error(java.lang.String,
     * java.lang.Throwable)
     */
    @Override
    public void error(String msg, Throwable e) {
        try {
            logger.error(appendContextMessage(msg), e);
        } catch (Throwable e2) {
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#isTraceEnabled()
     */
    @Override
    public boolean isTraceEnabled() {
        try {
            return logger.isTraceEnabled();
        } catch (Throwable e2) {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#isDebugEnabled()
     */
    @Override
    public boolean isDebugEnabled() {
        try {
            return logger.isDebugEnabled();
        } catch (Throwable e2) {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#isInfoEnabled()
     */
    @Override
    public boolean isInfoEnabled() {
        try {
            return logger.isInfoEnabled();
        } catch (Throwable e2) {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#isWarnEnabled()
     */
    @Override
    public boolean isWarnEnabled() {
        try {
            return logger.isWarnEnabled();
        } catch (Throwable e2) {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.common.logs.Logger#isErrorEnabled()
     */
    @Override
    public boolean isErrorEnabled() {
        try {
            return logger.isErrorEnabled();
        } catch (Throwable e2) {
            return false;
        }
    }

}
