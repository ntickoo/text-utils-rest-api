package com.company.textutils.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TextUtilsService {

    /**
     * Checks if the input string contains all the English alphabets ignoring their case. 
     * @param value input string
     * @return true if it contains all English alphabets, false otherwise
     */
    public boolean containsAllEnglishCharactersIgnoreCase(String value) {
	if(StringUtils.isEmpty(value))
	    return false;
	
	final Set<Character> englishLetterSet = new HashSet<>();
	for (char c : value.toLowerCase().toCharArray()) {
	    if(c >= 'a' && c <= 'z') {
		englishLetterSet.add(c);
	    }
	}
	
	return englishLetterSet.size() == 26;
    }
}
