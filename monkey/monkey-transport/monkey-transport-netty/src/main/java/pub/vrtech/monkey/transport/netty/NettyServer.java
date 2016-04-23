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

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import pub.vrtech.common.URL;
import pub.vrtech.common.logs.Logger;
import pub.vrtech.common.logs.LoggerFactory;
import pub.vrtech.common.utils.NamedThreadFactory;
import pub.vrtech.common.utils.NetUtils;
import pub.vrtech.transport.Channel;
import pub.vrtech.transport.ChannelHandler;
import pub.vrtech.transport.RemotingException;
import pub.vrtech.transport.transports.AbstractServer;

/**
 *
 * Function description： 1.netty server
 * 
 * @author houge
 */
public class NettyServer extends AbstractServer {

    private final static Logger logger = LoggerFactory
            .getLogger(NettyServer.class);

    private Map<String, Channel> channels;// ip:port,channel

    private ServerBootstrap bootstrap;

    private io.netty.channel.Channel channel;

    EventLoopGroup workerGroup = null;
    EventLoopGroup bossGroup = null;

    /**
     * @param url
     * @param handler
     * @throws RemotingException
     */
    public NettyServer(URL url, ChannelHandler handler)
            throws RemotingException {
        super(url, handler);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.transportes.AbstractServer#doOpen()
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected void doOpen() throws Throwable {
        NettyHelper.setNettyLoggerFactory();// 设置日志托管到自己的日志系统
        // ExecutorService boss=Executors.newCachedThreadPool(new
        // NamedThreadFactory("NettyServerBoss",true));
        // ExecutorService worker=Executors.newCachedThreadPool(new
        // NamedThreadFactory("NettyServerWorker",true));
        bossGroup = new NioEventLoopGroup(1, new NamedThreadFactory(
                "NettyServerBoss", true));
        workerGroup = new NioEventLoopGroup(0, new NamedThreadFactory(
                "NettyServerBoss", true));// 设置0表示CPU核心数目×2+1
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(
                NioServerSocketChannel.class);
        final NettyHandler nettyHandler = new NettyHandler(getUrl(), this);
        bootstrap.handler(new ChannelInitializer() {
            NettyCodecAdapter adapter = new NettyCodecAdapter(getCodec(),
                    getUrl(), NettyServer.this);
            @Override
            protected void initChannel(io.netty.channel.Channel ch)
                    throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(adapter.getDecoder());
                pipeline.addLast(adapter.getEncoder());
                pipeline.addLast(nettyHandler);
            }
        });
        ChannelFuture channelFuture = bootstrap.bind(getBindAddress());
        this.channel = channelFuture.channel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.Server#isBound()
     */
    public boolean isBound() {
        return this.channel.isOpen();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.Server#getChannels()
     */
    public Collection<Channel> getChannels() {
        Collection<Channel> chs = new HashSet<Channel>();
        for (Channel channel : this.channels.values()) {
            if (channel.isConnected()) {
                chs.add(channel);
            } else {
                channels.remove(NetUtils.toAddressString(channel
                        .getRemoteAddress()));
            }
        }
        return chs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.Server#getChannel(java.net.InetSocketAddress)
     */
    public Channel getChannel(InetSocketAddress remoteAddress) {
        return channels.get(NetUtils.toAddressString(remoteAddress));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.transportes.AbstractServer#doClose()
     */
    @Override
    protected void doClose() throws Throwable {
        try {
            if (channel != null) {
                // unbind.
                channel.close();
            }
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            Collection<Channel> channels = getChannels();
            if (channels != null && channels.size() > 0) {
                for (Channel channel : channels) {
                    try {
                        channel.close();
                    } catch (Throwable e) {
                        logger.warn(e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            if (bootstrap != null) {
                this.channel.close().awaitUninterruptibly();
                this.bossGroup.shutdownGracefully();
                this.workerGroup.shutdownGracefully();
            }
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }
        try {
            if (channels != null) {
                channels.clear();
            }
        } catch (Throwable e) {
            logger.warn(e.getMessage(), e);
        }

    }

}
