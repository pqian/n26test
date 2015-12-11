package com.github.pqian;

import lombok.Data;

@Data(staticConstructor="of")
public class StatusValue {
	
	private final String status;

}