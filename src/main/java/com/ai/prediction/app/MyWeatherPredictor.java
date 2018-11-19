package com.ai.prediction.app;
/**
 * 
 */

import java.io.BufferedReader;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;

/**
 * @author rbutti
 *
 */
public class MyWeatherPredictor {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		BufferedReader datafile = WeatherPredictor.readDataFile("weather.txt");
		Instances train = new Instances(datafile);

		// setting class attribute if the data format does not provide this information
		// For example, the XRFF format saves the class attribute information as well
		if (train.classIndex() == -1)
			train.setClassIndex(train.numAttributes() - 1);

		// testDataCreated Here - Results play "yes"
		// Instances test = createTestData("sunny", 69d, 70d, "FALSE");

		// testDataCreated Here - Results play "yes"
		Instances test = createTestData("sunny", 85d, 85d, "FALSE");

		// test.instance(0).setValue(play, "?");
		if (test.classIndex() == -1)
			test.setClassIndex(test.numAttributes() - 1);

		test.instance(0).setClassMissing();

		// model

		Classifier model = new J48();
		model.buildClassifier(train);

		// this does the trick
		double label = model.classifyInstance(test.instance(0));
		test.instance(0).setClassValue(label);

		System.out.println(test.instance(0).stringValue(4));

	}

	private static Instances createTestData(String outlookVal, double temperatureVal, double humidityVal,
			String windyVal) {
		FastVector outlookVals = new FastVector();

		outlookVals.addElement("sunny");
		outlookVals.addElement("overcast");
		outlookVals.addElement("rainy");
		Attribute outlook = new Attribute("outlook", outlookVals);
		Attribute temperature = new Attribute("temperature");
		Attribute humidity = new Attribute("humidity");

		FastVector windyVals = new FastVector();

		windyVals.addElement("TRUE");
		windyVals.addElement("FALSE");

		Attribute windy = new Attribute("windy", windyVals);

		FastVector playVals = new FastVector();

		playVals.addElement("yes");
		playVals.addElement("no");

		Attribute play = new Attribute("play", playVals);

		FastVector fvWekaAttributesLinear = new FastVector(5);
		fvWekaAttributesLinear.addElement(outlook);
		fvWekaAttributesLinear.addElement(temperature);
		fvWekaAttributesLinear.addElement(humidity);
		fvWekaAttributesLinear.addElement(windy);
		fvWekaAttributesLinear.addElement(play);

		Instances test = new Instances("weather", fvWekaAttributesLinear, 100000);

		test.add(new DenseInstance(5));

		test.instance(0).setValue(outlook, outlookVal);
		test.instance(0).setValue(temperature, temperatureVal);
		test.instance(0).setValue(humidity, humidityVal);
		test.instance(0).setValue(windy, windyVal);
		return test;
	}

}
