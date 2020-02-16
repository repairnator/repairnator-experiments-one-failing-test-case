/*******************************************************************************
 *
 * MIT License
 *
 * Copyright (c) 2016 Tiago de Freitas Lima
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *******************************************************************************/
package com.github.ljtfreitas.restify.http.client.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.HttpRequest;

class NettyChannelFutureListener implements ChannelFutureListener {

	private final HttpRequest nettyHttpRequest;
	private final NettyRequestExecuteHandler nettyRequestExecuteHandler;

	public NettyChannelFutureListener(HttpRequest nettyHttpRequest, NettyRequestExecuteHandler nettyRequestExecuteHandler) {
		this.nettyHttpRequest = nettyHttpRequest;
		this.nettyRequestExecuteHandler = nettyRequestExecuteHandler;
	}

	@Override
	public void operationComplete(ChannelFuture channelFuture) throws Exception {
		if (channelFuture.isSuccess()) {
			Channel channel = channelFuture.channel();
			channel.pipeline().addLast(nettyRequestExecuteHandler);

			channel.writeAndFlush(nettyHttpRequest);

		} else {
			nettyRequestExecuteHandler.exceptionCaught(null, channelFuture.cause());
		}
	}

}
