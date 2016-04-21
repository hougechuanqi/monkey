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
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.vrtech.common.URL;
import org.vrtech.common.logs.Logger;
import org.vrtech.common.logs.LoggerFactory;
import org.vrtech.common.utils.NamedThreadFactory;
import org.vrtech.transport.Channel;
import org.vrtech.transport.ChannelHandler;
import org.vrtech.transport.RemotingException;
import org.vrtech.transport.transportes.AbstractServer;

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
        EventLoopGroup bossGroup = new NioEventLoopGroup(1,
                new NamedThreadFactory("NettyServerBoss", true));
        EventLoopGroup workerGroup = new NioEventLoopGroup(0,
                new NamedThreadFactory("NettyServerBoss", true));// 设置0表示CPU核心数目×2+1
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

    }
    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.Server#isBound()
     */
    public boolean isBound() {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.Server#getChannels()
     */
    public Collection<Channel> getChannels() {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.Server#getChannel(java.net.InetSocketAddress)
     */
    public Channel getChannel(InetSocketAddress remoteAddress) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.Endpoint#send(java.lang.Object, boolean)
     */
    public void send(Object message, boolean sent) throws RemotingException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.vrtech.transport.transportes.AbstractServer#doClose()
     */
    @Override
    protected void doClose() throws Throwable {
        // TODO Auto-generated method stub

    }

}
