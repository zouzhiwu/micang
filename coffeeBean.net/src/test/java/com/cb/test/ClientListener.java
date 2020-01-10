package com.cb.test;

import com.cb.lisener.AbsLisener;

import io.netty.channel.Channel;

public class ClientListener extends AbsLisener {
	
	public void scan() {
//		navigate.put(0x1000, new ClientAction());
	}
	
//	public boolean testToken(Long accountId, String token){
	public boolean testToken(Long accountId, Integer token){
		return true;
	}

	@Override
	public void channelInactive(Channel channel) {

	}
}
