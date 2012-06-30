package com.svgonbatik.forgetcnot;

import java.util.ArrayList;
import java.io.Serializable;

public class SimpleLocation implements Serializable{
	private static final long serialVersionUID = 1;
    int x;
    int y;
    ArrayList<SimpleLocation> visibles = new ArrayList<SimpleLocation>();
}