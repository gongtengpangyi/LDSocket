package com.test.socket;

import frz.ld.socket.server.parser.BaseStringPackParser;

public class TestPackParser extends BaseStringPackParser {

	public TestPackParser(String packageName) {
		super(packageName);
	}

	@Override
	public Class<?> parseClass(String message) {
		return TestReceive.class;
	}

	@Override
	public Object parseObject(Class<?> clazz, String message) {
		System.out.println(clazz.getName());
		TestReceive testReceive = new TestReceive();
		testReceive.setXxx(message);
		return testReceive;
	}

}
