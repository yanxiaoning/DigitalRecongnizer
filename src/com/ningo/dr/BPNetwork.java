package com.ningo.dr;

import java.util.ArrayList;

public class BPNetwork {
	public static final float eta = 0.1f;
	//public static final float epsilon = 0.00001f;
	
	
	private ArrayList<HiddenNeuron> hiddenNeuronList;
	private  ArrayList<OutputNeuron> outputNeuronList;
	
	public BPNetwork(){
		
		hiddenNeuronList = new ArrayList<HiddenNeuron>();
		for(int i = 0;i < 20;i++)
			hiddenNeuronList.add(new HiddenNeuron());
		
		
		outputNeuronList = new ArrayList<OutputNeuron>();
		for(int i = 0;i < 10;i++)
			outputNeuronList.add(new OutputNeuron());
		
		
		
	}
	
	public void singleTraining(float[] inputXList,float[] targetValue)
	{
		System.out.println("begin");
		for(int i = 0;i < 1000;i++)
		{
			for(int j = 0;j < outputNeuronList.size();j++)
				outputNeuronList.get(j).setTargetValue(targetValue[j]);
			//System.out.println("1 end");
			
			
			
			float[] inputXListForOutputNeurons = new float[hiddenNeuronList.size()];
			for(int j = 0;j < hiddenNeuronList.size();j++)
				inputXListForOutputNeurons[j] = hiddenNeuronList.get(j).getOutput(inputXList);
			//System.out.println("2 end");
			
			float[] FinalOutputs =new float[outputNeuronList.size()];
			for(int j = 0;j < outputNeuronList.size();j++)
				FinalOutputs[j] = outputNeuronList.get(j).getOutput(inputXListForOutputNeurons);
			//System.out.println("3 end");
			
			
			
			
			/***backward propagation***/
			for(int j = 0;j < outputNeuronList.size();j++)
				outputNeuronList.get(j).weightUpdate(inputXListForOutputNeurons);
			//System.out.println("4 end");
			
			
			for(int j = 0;j < hiddenNeuronList.size();j++)
				hiddenNeuronList.get(j).weightUpdate(inputXList, outputNeuronList, j);
			//System.out.println("5 end");
				
		}
		System.out.println("All end");
	}
	
	
	public void singleRecognize(float[] inputXList)
	{
		System.out.println("beginRec");
		
			
			
			
			
			float[] inputXListForOutputNeurons = new float[hiddenNeuronList.size()];
			for(int j = 0;j < hiddenNeuronList.size();j++)
				inputXListForOutputNeurons[j] = hiddenNeuronList.get(j).getOutput(inputXList);
			//System.out.println("2 end");
			
			float[] FinalOutputs =new float[outputNeuronList.size()];
			for(int j = 0;j < outputNeuronList.size();j++)
				FinalOutputs[j] = outputNeuronList.get(j).getOutput(inputXListForOutputNeurons);
			//System.out.println("3 end");
			
			
			
			
			/***backward propagation***/
			int count = 0;
			for(int i = 0;i < 10; i++)
				if(FinalOutputs[i] == 0)
					count++;
			
			if(count!=9)
				System.out.println("cannot be reconized");
			else
				for(int i = 0;i < 10;i++)
					if(FinalOutputs[i] == 1)
						System.out.println("it is"+i);
				
		
		System.out.println("end Rec");
	}

}
