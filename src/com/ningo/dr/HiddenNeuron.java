package com.ningo.dr;

import java.util.ArrayList;

public class HiddenNeuron {
	
	
	private float[] inputWeightList;
	
	private boolean adjustEnd = false;
	
	public HiddenNeuron()
	{
		
		inputWeightList = new float[625];
		
		for(int i = 0;i < 625;i++)
		inputWeightList[i] = 0.5f;
		
	}
	
	public float getOutput(float[] inputXList)
	{
		float sigma = inputWeightList[0];
		
		for(int i = 0;i < inputWeightList.length - 1;i++)
			sigma = sigma + inputWeightList[i] * inputXList[i];
		
		
		
		
		
		
		return sigmoiFunction(sigma);
	}
	
	private float getDelta(float[] inputXList,ArrayList<OutputNeuron> neurons,int index)
	{
		float output = getOutput(inputXList);
		
		float outputLayerSigma = 0;
		
		for(int i = 0;i < neurons.size();i++)
			outputLayerSigma = outputLayerSigma + neurons.get(i).getWeight(index)*neurons.get(i).delta();
		
		
		return output*(1.0f - output)*(outputLayerSigma - output);
	}
	
	private float getWeightChanging(float inputX,float[] inputXList,ArrayList<OutputNeuron> neurons,int index)
	{
		float change = BPNetwork.eta * inputX * getDelta(inputXList,neurons,index);
		
		
			
			return change;
		
	}
	
	public void weightUpdate(float[] inputXList,ArrayList<OutputNeuron> neurons,int index)
	{
		/*
		if(adjustEnd)
			return;
		*/
		for(int i = 0;i < inputWeightList.length;i++)
			inputWeightList[i]= inputWeightList[i] + getWeightChanging(inputXList[i],inputXList,neurons,index);
	}
	
	private float sigmoiFunction(float x)
	{
		float y;
		
		y = 1.0f/(1.0f + (float)Math.pow(Math.E,x));
		
		return y;
	}
	
	

}
