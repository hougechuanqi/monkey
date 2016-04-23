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
package pub.vrtech.monkey.transport.netty;

import io.netty.util.internal.logging.AbstractInternalLogger;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import pub.vrtech.common.logs.FormattingTuple;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.common.logs.MessageFormatter;

/**
 *
 * Function description： 1.托管netty的日志服务
 * 
 * @author houge
 */
public class NettyHelper {

    public static void setNettyLoggerFactory() {
        InternalLoggerFactory factory = InternalLoggerFactory
                .getDefaultFactory();
        if (factory == null || !(factory instanceof MonkeyLoggerFactory)) {

        }
    }

    static class MonkeyLoggerFactory extends InternalLoggerFactory {

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLoggerFactory#newInstance(
         * java.lang.String)
         */
        @Override
        protected InternalLogger newInstance(String name) {
            // TODO Auto-generated method stub
            return new MonkeyLogger("", LoggerFactory.getLogger(name));
        }

    }

    static class MonkeyLogger extends AbstractInternalLogger {

        /**
         * @param name
         */
        protected MonkeyLogger(String name, Logger logger) {
            super(name);
            this.logger = logger;
        }

        private Logger logger;

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        /*
         * (non-Javadoc)
         * 
         * @see io.netty.util.internal.logging.InternalLogger#isTraceEnabled()
         */
        public boolean isTraceEnabled() {
            // TODO Auto-generated method stub
            return logger.isTraceEnabled();
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#trace(java.lang.String)
         */
        public void trace(String msg) {
            logger.trace(msg);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#trace(java.lang.String,
         * java.lang.Object)
         */
        public void trace(String format, Object arg) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.trace(ft.getMessage(), ft.getThrowable());
        }
        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#trace(java.lang.String,
         * java.lang.Object, java.lang.Object)
         */
        public void trace(String format, Object argA, Object argB) {
            FormattingTuple ft = MessageFormatter.format(format, argA, argB);
            logger.trace(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#trace(java.lang.String,
         * java.lang.Object[])
         */
        public void trace(String format, Object... arguments) {
            FormattingTuple ft = MessageFormatter.format(format, arguments);
            logger.trace(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#trace(java.lang.String,
         * java.lang.Throwable)
         */
        public void trace(String msg, Throwable t) {
            logger.trace(msg, t);
        }

        /*
         * (non-Javadoc)
         * 
         * @see io.netty.util.internal.logging.InternalLogger#isDebugEnabled()
         */
        public boolean isDebugEnabled() {
            return logger.isDebugEnabled();
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#debug(java.lang.String)
         */
        public void debug(String msg) {
            logger.debug(msg);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#debug(java.lang.String,
         * java.lang.Object)
         */
        public void debug(String format, Object arg) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.debug(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#debug(java.lang.String,
         * java.lang.Object, java.lang.Object)
         */
        public void debug(String format, Object argA, Object argB) {
            FormattingTuple ft = MessageFormatter.format(format, argA, argB);
            logger.debug(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#debug(java.lang.String,
         * java.lang.Object[])
         */
        public void debug(String format, Object... arguments) {
            FormattingTuple ft = MessageFormatter.format(format, arguments);
            logger.debug(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#debug(java.lang.String,
         * java.lang.Throwable)
         */
        public void debug(String msg, Throwable t) {
            logger.debug(msg, t);
        }

        /*
         * (non-Javadoc)
         * 
         * @see io.netty.util.internal.logging.InternalLogger#isInfoEnabled()
         */
        public boolean isInfoEnabled() {
            return logger.isInfoEnabled();
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#info(java.lang.String)
         */
        public void info(String msg) {
            logger.info(msg);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#info(java.lang.String,
         * java.lang.Object)
         */
        public void info(String format, Object arg) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.info(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#info(java.lang.String,
         * java.lang.Object, java.lang.Object)
         */
        public void info(String format, Object argA, Object argB) {
            FormattingTuple ft = MessageFormatter.format(format, argA, argB);
            logger.info(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#info(java.lang.String,
         * java.lang.Object[])
         */
        public void info(String format, Object... arguments) {
            FormattingTuple ft = MessageFormatter.format(format, arguments);
            logger.info(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#info(java.lang.String,
         * java.lang.Throwable)
         */
        public void info(String msg, Throwable t) {
            logger.info(msg, t);
        }

        /*
         * (non-Javadoc)
         * 
         * @see io.netty.util.internal.logging.InternalLogger#isWarnEnabled()
         */
        public boolean isWarnEnabled() {
            return logger.isWarnEnabled();
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#warn(java.lang.String)
         */
        public void warn(String msg) {
            logger.warn(msg);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#warn(java.lang.String,
         * java.lang.Object)
         */
        public void warn(String format, Object arg) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.warn(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#warn(java.lang.String,
         * java.lang.Object[])
         */
        public void warn(String format, Object... arguments) {
            FormattingTuple ft = MessageFormatter.format(format, arguments);
            logger.warn(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#warn(java.lang.String,
         * java.lang.Object, java.lang.Object)
         */
        public void warn(String format, Object argA, Object argB) {
            FormattingTuple ft = MessageFormatter.format(format, argA, argB);
            logger.warn(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#warn(java.lang.String,
         * java.lang.Throwable)
         */
        public void warn(String msg, Throwable t) {
            logger.warn(msg, t);
        }

        /*
         * (non-Javadoc)
         * 
         * @see io.netty.util.internal.logging.InternalLogger#isErrorEnabled()
         */
        public boolean isErrorEnabled() {
            return logger.isErrorEnabled();
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#error(java.lang.String)
         */
        public void error(String msg) {
            logger.error(msg);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#error(java.lang.String,
         * java.lang.Object)
         */
        public void error(String format, Object arg) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            logger.error(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#error(java.lang.String,
         * java.lang.Object, java.lang.Object)
         */
        public void error(String format, Object argA, Object argB) {
            FormattingTuple ft = MessageFormatter.format(format, argA, argB);
            logger.error(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#error(java.lang.String,
         * java.lang.Object[])
         */
        public void error(String format, Object... arguments) {
            FormattingTuple ft = MessageFormatter.format(format, arguments);
            logger.error(ft.getMessage(), ft.getThrowable());
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * io.netty.util.internal.logging.InternalLogger#error(java.lang.String,
         * java.lang.Throwable)
         */
        public void error(String msg, Throwable t) {
            logger.error(msg, t);
        }

    }

}
