package com.ai.prediction.engine;

import java.io.BufferedReader;

import org.springframework.stereotype.Component;

import com.ai.prediction.app.WeatherPredictor;
import com.ai.prediction.model.Claim;

import weka.classifiers.Classifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;

@Component
public class ClaimAnalyzer {

	static Classifier model;
	static {
		try {
			BufferedReader datafile = WeatherPredictor.readDataFile("train-claim.txt");
			Instances train = new Instances(datafile);

			// setting class attribute if the data format does not provide this information
			// For example, the XRFF format saves the class attribute information as well
			if (train.classIndex() == -1)
				train.setClassIndex(train.numAttributes() - 1);

			// model
			model = new PART();
			model.buildClassifier(train);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public Claim analyzeClaim(Claim claim) {

		try {
			System.out.println(claim);
			// testDataCreated Here - Results play "yes"
			// Instances test = createTestData("sunny", 69d, 70d, "FALSE");

			// testDataCreated Here - Results play "yes"
			Instances test = createTestData(claim);

			// test.instance(0).setValue(play, "?");
			if (test.classIndex() == -1)
				test.setClassIndex(test.numAttributes() - 1);

			test.instance(0).setClassMissing();

			// this does the trick
			double label = model.classifyInstance(test.instance(0));
			test.instance(0).setClassValue(label);

			System.out.println(test.instance(0).stringValue(2));

			claim.setProvDetailsReq(Boolean.valueOf(test.instance(0).stringValue(2)));
			return claim;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private Instances createTestData(Claim claim) {
		FastVector providerTypes = new FastVector();

		providerTypes.addElement("IN_NETWORK");
		providerTypes.addElement("OUT_NETWORK");

		Attribute providerType = new Attribute("providerType", providerTypes);
		Attribute claimAmount = new Attribute("claimAmount");

		FastVector provDetailsReqs = new FastVector();

		provDetailsReqs.addElement("TRUE");
		provDetailsReqs.addElement("FALSE");

		Attribute provDetailsReq = new Attribute("provDetailsReq", provDetailsReqs);

		FastVector fvWekaAttributesLinear = new FastVector(3);
		fvWekaAttributesLinear.addElement(providerType);
		fvWekaAttributesLinear.addElement(claimAmount);
		fvWekaAttributesLinear.addElement(provDetailsReq);

		Instances test = new Instances("claims", fvWekaAttributesLinear, 100000);

		test.add(new DenseInstance(3));

		test.instance(0).setValue(providerType, claim.getProviderType().toString());
		test.instance(0).setValue(claimAmount, claim.getClaimAmount());

		return test;
	}
}
