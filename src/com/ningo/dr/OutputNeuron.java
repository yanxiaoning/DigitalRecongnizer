package com.ningo.dr;

import java.util.ArrayList;

public class OutputNeuron {
	
	
	private float[] inputWeightList;
	private float targetValue;
	private boolean adjustEnd = false;
	
	private float delta;
	
	public OutputNeuron()
	{
		
       inputWeightList = new float[20];
		
		for(int i = 0;i < 20;i++)
		inputWeightList[i] = 0.5f;
		
	}
	
	public float getOutput(float[] inputXList)
	{
		float sigma = inputWeightList[0];
		
		for(int i = 0;i < inputWeightList.length - 1;i++)
			sigma = sigma + inputWeightList[i] * inputXList[i];
		
		
		
		
		
		
		return sigmoiFunction(sigma);
	}
	
	private float getDelta(float[] inputXList)
	{
		float output = getOutput(inputXList);
		delta = output*(1.0f - output)*(targetValue - output);
		return output*(1.0f - output)*(targetValue - output);
	}
	
	private float getWeightChanging(float inputX,float[] inputXList)
	{
		float change = BPNetwork.eta * inputX * getDelta(inputXList);
		
		return change;
		
	}
	
	public void weightUpdate(float[] inputXList)
	{
		/*
		if(adjustEnd)
			return;
		*/
		for(int i = 0;i < inputWeightList.length;i++)
			inputWeightList[i] = inputWeightList[i] + getWeightChanging(inputXList[i],inputXList);
	}
	
	private float sigmoiFunction(float x)
	{
		float y;
		
		y = 1.0f/(1.0f + (float)Math.pow(Math.E,x));
		
		return y;
	}
	
	public float getWeight(int index)
	{
		return inputWeightList[index];
	}
	
	public float delta()
	{
		return delta;
	}
	
	public void setTargetValue(float targetValue)
	{
		this.targetValue = targetValue;
	}

}
