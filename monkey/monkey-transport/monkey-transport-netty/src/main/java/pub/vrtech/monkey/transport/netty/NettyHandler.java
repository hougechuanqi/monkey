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

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.vrtech.common.URL;
import org.vrtech.common.utils.NetUtils;
import org.vrtech.transport.Channel;
import org.vrtech.transport.ChannelHandler;

/**
 *
 * Function description： 1.XXX 2.XXX
 * 
 * @author houge
 */
@Sharable
public class NettyHandler extends ChannelInboundHandlerAdapter {

    private final Map<String, Channel> channels = new ConcurrentHashMap<String, Channel>();// <ip:port,channel>

    private URL url;

    private ChannelHandler handler;

    /**
     * 
     */
    public NettyHandler(URL url, ChannelHandler handler) {
        if (url == null) {
            throw new IllegalArgumentException("url==null");
        } else if (handler == null) {
            throw new IllegalArgumentException("handler == null");
        }
        this.url = url;
        this.handler = handler;
    }

    public Map<String, Channel> getChannles() {
        return channels;
    }

    public URL getUrl() {
        return url;
    }

    public ChannelHandler getHandler() {
        return handler;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final io.netty.channel.Channel channel = ctx.channel();
        NettyChannel ch = NettyChannel.getOrAddChannel(channel, url, handler);
        try {
            if (channel != null) {
                channels.put(NetUtils
                        .toAddressString((InetSocketAddress) channel
                                .remoteAddress()), ch);
            }
            handler.connected(ch);
        } finally {
            NettyChannel.removeChannelIfDisconnected(channel);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(), url,
                handler);
        try {
            channels.remove(NetUtils.toAddressString((InetSocketAddress) ctx
                    .channel().remoteAddress()));
            handler.disconnected(channel);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(), url,
                handler);
        try {
            handler.received(channel, msg);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

//    @Override
//    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
//            throws Exception {
//        super.userEventTriggered(ctx, evt);
//        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(), url,
//                handler);
//        try {
//            handler.sent(channel, evt);
//        } finally {
//            NettyChannel.removeChannelIfDisconnected(ctx.channel());
//        }
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        NettyChannel channel = NettyChannel.getOrAddChannel(ctx.channel(), url,
                handler);
        try {
            handler.caught(channel, cause);
        } finally {
            NettyChannel.removeChannelIfDisconnected(ctx.channel());
        }
    }

}
