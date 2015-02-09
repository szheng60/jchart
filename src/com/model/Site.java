package com.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author song
 */
public class Site {
    private char code;
    private String description;
    
    public Site()
    {
        code = ' ';
        description = " ";
    }

    /**
     * @return the code
     */
    public char getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(char code) {
        this.code = code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
