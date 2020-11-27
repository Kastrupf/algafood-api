package com.kastrupf.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.kastrupf.algafood.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonRootName("cuisine")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded= true)
@Entity
public class Cuisine {
	
	@NotNull(groups = Groups.RegistreRestaurant.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
//  @JsonProperty("title")
	@Column (nullable = false)
	private String nom;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cuisine")
	private List<Restaurant> restaurants = new ArrayList<>();
}
