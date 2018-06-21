package com.test.socket;

import frz.ld.socket.server.TeddyBear;
import frz.ld.socket.server.pack.Receive;
import frz.ld.socket.server.pack.ReceiveAction;

@Receive
public class TestReceive {

	private String xxx;

	public String getXxx() {
		return xxx;
	}

	public void setXxx(String xxx) {
		this.xxx = xxx;
	}
	
	@ReceiveAction
	public void receiveAction(TeddyBear<?> teddyBear) {
		System.out.println(xxx);
		teddyBear.sendMessage("xxxxxxxxxxxxxxxxxxxxx");
	}
}
