package com.test.socket;

import java.util.HashMap;

import frz.ld.socket.server.Landy;
import frz.ld.socket.server.TeddyBear;
import frz.ld.socket.server.TeddyBearCollection;
import frz.ld.socket.server.netty.NettyStringLandy;

public class TestLandy {

	
	/**
	 * 
	 * 
	 * 
	 * 用的时候应该：
	 * 
	 * Landy landy = new Landy(ip, port, connetTimeout);
	 * TeddyBearCollection teddyBearCollection = new TeddyBearCollection(new HashMap<>(), new PackParser());
	 * landy.addTeddyBearCollection(teddyBearCollection);
	 * landy.start();
	 * 
	 * 然后设置一大堆定义了send、receive、receiveAction注解的类，注解要与PakcParser灵活适配
	 * 
	 */
	
	public static void main(String args[]) {
		
	}
	
	public static void test1() {
		Landy landy = new NettyStringLandy(5000, new TestPackParser(""));
		landy.addTeddyBearCollection(new TeddyBearCollection<HashMap<Object,TeddyBear<?>>>(new HashMap<>()));
		landy.start();
	}
	
	public static void test2() {
		
	}
}
