package com.ai.prediction.app;

import weka.core.Attribute;

public class MyAttribute extends Attribute {
	
	 private /*@ spec_public @*/ int m_Type = Attribute.STRING;

	public MyAttribute(String attributeName) {
		super(attributeName);
		
	}

}
