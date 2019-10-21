package com.fit_goal.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

//@NoArgsConstructor
@Getter
@Setter
abstract class BaseEntity  {

    private Long id;

    @NotNull
    private boolean active;
}

