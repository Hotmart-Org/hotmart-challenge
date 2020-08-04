package com.hotmart.challenge.domain.model.base;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
@SuperBuilder
public abstract class GenericBaseModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

}
