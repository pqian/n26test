package com.github.pqian.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@ToString(of = { "id", "amount", "type"})
@JsonIgnoreProperties("parent")
@Entity
public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Column(nullable = false, updatable = false)
	private Double amount;
	
	@Column(nullable = false, updatable = false)
	private String type;
	
	@ManyToOne(optional=true)
	@JoinColumn(name = "parent_id", updatable = false)
	private Transaction parent;

	@Transient
	@JsonProperty("parent_id")
	private Long parentId;
	
	@PostLoad
	public void doAfterLoad() {
		if (parent != null) {
			parentId = parent.getId();
		}
	}
}
