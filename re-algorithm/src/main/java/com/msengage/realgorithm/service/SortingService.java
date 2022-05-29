package com.msengage.realgorithm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SortingService
{
    // Self Designed Sorting Algorithm
    public List<String> sortStringArray(List<String> inputArray)
    {
        String[] stringArray = new String[inputArray.size()];
        int k = 0;
        for(String name : inputArray)
        {
            stringArray[k] = name;
            k++;
        }
        int size = inputArray.size();
        for (int i = 0; i < size - 1; i++)
        {
            for (int j = i + 1; j < size; j++)
            {
                //compares each elements of the array to all the remaining elements
                if (stringArray[i].compareTo(stringArray[j]) > 0)
                {
                    //swapping array elements
                    String temp = stringArray[i];
                    stringArray[i] = stringArray[j];
                    stringArray[j] = temp;
                }
            }
        }
        return Arrays.asList(stringArray.clone());
    }
}
